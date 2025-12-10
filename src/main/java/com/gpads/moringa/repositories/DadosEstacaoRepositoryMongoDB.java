package com.gpads.moringa.repositories;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gpads.moringa.entities.DadosEstacao;

public interface DadosEstacaoRepositoryMongoDB extends MongoRepository<DadosEstacao, ObjectId> {

    // Busca por Temperatura
    List<DadosEstacao> findByTemperaturaGreaterThan(Double temperatura);
    List<DadosEstacao> findByTemperaturaLessThan(Double temperatura);
    List<DadosEstacao> findByTemperatura(Double temperatura);

    // Busca por Umidade
    List<DadosEstacao> findByUmidadeGreaterThan(Double umidade);
    List<DadosEstacao> findByUmidadeLessThan(Double umidade);
    List<DadosEstacao> findByUmidade(Double umidade);

    // Busca por Pressão
    List<DadosEstacao> findByPressaoGreaterThan(Double pressao);
    List<DadosEstacao> findByPressaoLessThan(Double pressao);
    List<DadosEstacao> findByPressao(Double pressao);

    // Busca por Vento
    List<DadosEstacao> findByVento(Double vento);

    // Busca por Luz
    List<DadosEstacao> findByLuz(Integer luz);

    // Busca por RPM
    List<DadosEstacao> findByRpm(Double rpm);

    // Busca por Gás
    List<DadosEstacao> findByGas(Double gas);

    // Busca por Ar
    List<DadosEstacao> findByAr(Double ar);

    // Busca por Volt
    List<DadosEstacao> findByVoltGreaterThan(Double volt);
    List<DadosEstacao> findByVoltLessThan(Double volt);
    List<DadosEstacao> findByVolt(Double volt);
    

}
