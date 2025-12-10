package com.gpads.moringa.repositories;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gpads.moringa.entities.SensorDeSolo;

public interface SensorDeSoloRepositoryMongoDB extends MongoRepository<SensorDeSolo, ObjectId> {


    // Busca por temperatura
    List<SensorDeSolo> findByTemperaturaGreaterThan(Double temperatura);
    List<SensorDeSolo> findByTemperaturaLessThan(Double temperatura);
    List<SensorDeSolo> findByTemperatura(Double temperatura);

    // Busca por umidade
    List<SensorDeSolo> findByUmidadeGreaterThan(Double umidade);
    List<SensorDeSolo> findByUmidadeLessThan(Double umidade);
    List<SensorDeSolo> findByUmidade(Double umidade);

    // Busca por pH
    List<SensorDeSolo> findByPhGreaterThan(Double ph);
    List<SensorDeSolo> findByPhLessThan(Double ph);
    List<SensorDeSolo> findByPh(Double ph);

    // Busca por condutividade
    List<SensorDeSolo> findByCondutividadeGreaterThan(Double condutividade);
    List<SensorDeSolo> findByCondutividadeLessThan(Double condutividade);
    List<SensorDeSolo> findByCondutividade(Double condutividade);

}
