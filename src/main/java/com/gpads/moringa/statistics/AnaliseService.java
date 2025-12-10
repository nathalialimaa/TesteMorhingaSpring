package com.gpads.moringa.statistics;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.DadosEstacao;
import com.gpads.moringa.entities.Pluviometro;
import com.gpads.moringa.entities.SensorDePh;
import com.gpads.moringa.entities.SensorDeSolo;

@Service
public class AnaliseService {

    public List<IntervaloTemporalEstatistico> analise(List<Object> dados) {

        System.out.println("===== Dados recebidos =====");
        for (Object obj : dados) {
            System.out.println(obj);
        }

        // Agrupa por hora
        Map<String, List<Object>> dadosAgrupados = dados.stream()
                .collect(Collectors.groupingBy(p -> formatarDataHora(getDataHora(p))));

        List<IntervaloTemporalEstatistico> resultado = new ArrayList<>();

        for (Map.Entry<String, List<Object>> entry : dadosAgrupados.entrySet()) {

            String intervalo = entry.getKey();
            List<Object> dadosIntervalo = entry.getValue();

            Map<String, AnaliseEstatistica> mapaDados = new HashMap<>();

            String[] campos = obterCamposDeAnalise(dadosIntervalo.get(0));

            for (String campo : campos) {

                List<Float> valores = extrairValores(dadosIntervalo, campo);

                AnaliseEstatistica estat = new AnaliseEstatistica();
                estat.setMedia(media(valores));
                estat.setModa(moda(valores));
                estat.setMediana(mediana(valores));
                estat.setQ1(q1(valores));
                estat.setq3(q3(valores));

                mapaDados.put(campo, estat);
            }

            IntervaloTemporalEstatistico intervaloEstatistico = new IntervaloTemporalEstatistico();
            intervaloEstatistico.setIntervaloTempo(intervalo);
            intervaloEstatistico.setMapaDados(mapaDados);

            resultado.add(intervaloEstatistico);
        }

        return resultado;
    }

    // ----------------------------------------------------------------------
    // TRATAMENTO DE DATA
    // ----------------------------------------------------------------------

    private String formatarDataHora(Date dataHora) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00");
        return sdf.format(dataHora);
    }

    private Date getDataHora(Object obj) {

        try {
            // 1. Campo Date dataHora
            try {
                Field f = obj.getClass().getDeclaredField("dataHora");
                f.setAccessible(true);
                Object value = f.get(obj);
                if (value != null)
                    return (Date) value;
            } catch (NoSuchFieldException ignored) {
            }

            // 2. Campos data + hora (String)
            try {
                Field fData = obj.getClass().getDeclaredField("data");
                Field fHora = obj.getClass().getDeclaredField("hora");

                fData.setAccessible(true);
                fHora.setAccessible(true);

                String data = (String) fData.get(obj);
                String hora = (String) fHora.get(obj);

                if (data != null && hora != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    return sdf.parse(data + " " + hora);
                }
            } catch (NoSuchFieldException ignored) {
            }

            // 3. Se tiver ObjectId
            try {
                Field fId = obj.getClass().getDeclaredField("id");
                fId.setAccessible(true);
                ObjectId id = (ObjectId) fId.get(obj);
                if (id != null)
                    return id.getDate();
            } catch (Exception ignored) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Date();
    }

    // ----------------------------------------------------------------------
    // CAMPOS SUPORTADOS POR ENTIDADE
    // ----------------------------------------------------------------------

    private String[] obterCamposDeAnalise(Object objeto) {

        if (objeto instanceof DadosEstacao) {
            return new String[] { "temperatura", "umidade", "pressao", "luminosidade" };
        }

        if (objeto instanceof Pluviometro) {
            return new String[] { "pluviometria" };
        }

        if (objeto instanceof SensorDePh) {
            return new String[] { "ph" };
        }

        if (objeto instanceof SensorDeSolo) {
            return new String[] { "temperaturaSolo", "umidadeSolo", "phSolo" };
        }

        return new String[] {};
    }

    // ----------------------------------------------------------------------
    // EXTRAÇÃO DE VALORES (TRADUZ CAMPOS PARA OS CAMPOS REAIS)
    // ----------------------------------------------------------------------

    public List<Float> extrairValores(List<Object> dados, String campo) {

        return dados.stream()
                .map(obj -> extrairValor(obj, campo))
                .collect(Collectors.toList());
    }

    private Float extrairValor(Object obj, String campo) {
        try {

            // MAPA DE REDIRECIONAMENTO DOS CAMPOS
            switch (campo) {

                case "temperatura":
                    return ((DadosEstacao) obj).getTemperatura().floatValue();

                case "umidade":
                    return ((DadosEstacao) obj).getUmidade().floatValue();

                case "pressao":
                    return ((DadosEstacao) obj).getPressao().floatValue();

                case "luminosidade":
                    return ((DadosEstacao) obj).getLuz().floatValue();

                case "pluviometria":
                    return Float.parseFloat(((Pluviometro) obj).getMedidaDeChuvaCalculado().replace(",", "."));

                case "ph":
                    return new BigDecimal(((SensorDePh) obj).getPh().replace(",", "."))
                            .setScale(2, RoundingMode.HALF_UP)
                            .floatValue();
                case "temperaturaSolo":
                    return Float.parseFloat(((SensorDeSolo) obj).getTemperatura().replace(",", "."));

                case "umidadeSolo":
                    return Float.parseFloat(((SensorDeSolo) obj).getUmidade().replace(",", "."));

                case "phSolo":
                    return Float.parseFloat(((SensorDeSolo) obj).getPh().replace(",", "."));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0f;
    }

    // ----------------------------------------------------------------------
    // ESTATÍSTICAS
    // ----------------------------------------------------------------------

    public float media(List<Float> d) {
        float soma = d.stream().reduce(0.0f, Float::sum);
        return new BigDecimal(soma / d.size())
                .setScale(2, RoundingMode.HALF_UP)
                .floatValue();
    }

    public List<Float> mediana(List<Float> d) {
        Collections.sort(d);
        int n = d.size();
        if (n % 2 == 0)
            return List.of(d.get(n / 2 - 1), d.get(n / 2)); // ordem crescente
        return List.of(d.get(n / 2));
    }

    public List<Float> moda(List<Float> d) {
        Map<Float, Integer> freq = new HashMap<>();
        int maxFreq = 0;

        for (Float x : d) {
            int f = freq.getOrDefault(x, 0) + 1;
            freq.put(x, f);
            maxFreq = Math.max(maxFreq, f);
        }

        List<Float> modas = new ArrayList<>();
        for (var e : freq.entrySet())
            if (e.getValue() == maxFreq)
                modas.add(e.getKey());

        Collections.sort(modas);

        return modas;
    }

    public float q1(List<Float> d) {
        Collections.sort(d);
        return d.get(Math.max(0, (d.size() / 4)));
    }

    public float q3(List<Float> d) {
        Collections.sort(d);
        return d.get(Math.max(0, (3 * d.size()) / 4));
    }
}
