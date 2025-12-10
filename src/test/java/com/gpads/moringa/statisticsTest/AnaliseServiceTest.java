package com.gpads.moringa.statisticsTest;

import com.gpads.moringa.entities.*;
import com.gpads.moringa.statistics.AnaliseService;
import com.gpads.moringa.statistics.IntervaloTemporalEstatistico;
import com.gpads.moringa.statistics.AnaliseEstatistica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnaliseServiceTest {

    private AnaliseService analiseService;

    @BeforeEach
    public void setup() {
        analiseService = new AnaliseService();
    }

    @Test
    public void testAnaliseComDadosEstacao() {
        DadosEstacao d1 = new DadosEstacao();
        d1.setTemperatura(26.0);
        d1.setUmidade(80.0);
        d1.setPressao(1013.0);
        d1.setLuz(300.0);

        DadosEstacao d2 = new DadosEstacao();
        d2.setTemperatura(28.0);
        d2.setUmidade(82.0);
        d2.setPressao(1012.0);
        d2.setLuz(320.0);

        List<Object> dados = List.of(d1, d2);
        List<IntervaloTemporalEstatistico> resultado = analiseService.analise(dados);

        // ✅ Print detalhado do resultado
        System.out.println("===== Resultado completo de DadosEstacao =====");
        for (IntervaloTemporalEstatistico intervalo : resultado) {
            System.out.println("Intervalo de tempo: " + intervalo.getIntervaloTempo());
            intervalo.getMapaDados().forEach((campo, estat) -> {
                System.out.println("Campo: " + campo + " -> " + estat);
            });
            System.out.println("--------------------------------------------");
        }

        assertFalse(resultado.isEmpty());

        IntervaloTemporalEstatistico intervalo = resultado.get(0);
        Map<String, AnaliseEstatistica> mapa = intervalo.getMapaDados();

        // delta de 0.001 para tolerância de ponto flutuante
        assertEquals(27.0f, mapa.get("temperatura").getMedia(), 0.001f);
        assertEquals(81.0f, mapa.get("umidade").getMedia(), 0.001f);
        assertEquals(1012.5f, mapa.get("pressao").getMedia(), 0.001f);
        assertEquals(310.0f, mapa.get("luminosidade").getMedia(), 0.001f);
    }

    @Test
    public void testAnaliseComPluviometro() {
        Pluviometro p1 = new Pluviometro();
        p1.setData("01/06/2025");
        p1.setHora("12:00");
        p1.setMedidaDeChuvaCalculado("10.0");

        Pluviometro p2 = new Pluviometro();
        p2.setData("01/06/2025");
        p2.setHora("12:00");
        p2.setMedidaDeChuvaCalculado("20.0");

        List<Object> dados = List.of(p1, p2);
        List<IntervaloTemporalEstatistico> resultado = analiseService.analise(dados);
        // ✅ Print detalhado do resultado
        System.out.println("===== Resultado completo de Pluviometro =====");
        for (IntervaloTemporalEstatistico intervalo : resultado) {
            System.out.println("Intervalo de tempo: " + intervalo.getIntervaloTempo());
            intervalo.getMapaDados().forEach((campo, estat) -> {
                System.out.println("Campo: " + campo + " -> " + estat);
            });
            System.out.println("--------------------------------------------");
        }

        assertFalse(resultado.isEmpty());

        AnaliseEstatistica estat = resultado.get(0).getMapaDados().get("pluviometria");

        assertEquals(15.0f, estat.getMedia(), 0.001f);

        // Comparando mediana elemento por elemento
        List<Float> esperado = Arrays.asList(10.0f, 20.0f);
        List<Float> obtido = estat.getMediana();
        for (int i = 0; i < esperado.size(); i++) {
            assertEquals(esperado.get(i), obtido.get(i), 0.001f);
        }
    }

    @Test
    public void testAnaliseComSensorPh() {
        SensorDePh ph1 = new SensorDePh();
        ph1.setData("01/06/2025");
        ph1.setHora("12:00");
        ph1.setPh("6.2");

        SensorDePh ph2 = new SensorDePh();
        ph2.setData("01/06/2025");
        ph2.setHora("12:00");
        ph2.setPh("6.4");

        List<Object> dados = List.of(ph1, ph2);
        List<IntervaloTemporalEstatistico> resultado = analiseService.analise(dados);
        // ✅ Print detalhado do resultado
        System.out.println("===== Resultado completo de SensorDePh=====");
        for (IntervaloTemporalEstatistico intervalo : resultado) {
            System.out.println("Intervalo de tempo: " + intervalo.getIntervaloTempo());
            intervalo.getMapaDados().forEach((campo, estat) -> {
                System.out.println("Campo: " + campo + " -> " + estat);
            });
            System.out.println("--------------------------------------------");
        }

        AnaliseEstatistica estat = resultado.get(0).getMapaDados().get("ph");

        assertEquals(6.3f, estat.getMedia(), 0.001f);

        List<Float> esperado = Arrays.asList(6.2f, 6.4f);
        List<Float> obtido = estat.getMediana();
        for (int i = 0; i < esperado.size(); i++) {
            assertEquals(esperado.get(i), obtido.get(i), 0.001f);
        }
    }

    @Test
    public void testAnaliseComSensorSolo() {
        SensorDeSolo solo = new SensorDeSolo();
        solo.setData("01/06/2025");
        solo.setHora("12:00");
        solo.setTemperatura("25");
        solo.setUmidade("80");
        solo.setPh("6.0");

        List<Object> dados = List.of(solo);

        List<IntervaloTemporalEstatistico> resultado = analiseService.analise(dados);
        Map<String, AnaliseEstatistica> mapa = resultado.get(0).getMapaDados();

        // ✅ Print detalhado do resultado
        System.out.println("===== Resultado completo de Sensor de Solo =====");
        for (IntervaloTemporalEstatistico intervalo : resultado) {
            System.out.println("Intervalo de tempo: " + intervalo.getIntervaloTempo());
            intervalo.getMapaDados().forEach((campo, estat) -> {
                System.out.println("Campo: " + campo + " -> " + estat);
            });
            System.out.println("--------------------------------------------");
        }

        assertEquals(25f, mapa.get("temperaturaSolo").getMedia(), 0.001f);
        assertEquals(80f, mapa.get("umidadeSolo").getMedia(), 0.001f);
        assertEquals(6.0f, mapa.get("phSolo").getMedia(), 0.001f);
    }
}
