package com.gpads.moringa.repositories;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gpads.moringa.entities.Reservatorios;

public interface ReservatoriosRepositoryMongoDB extends MongoRepository<Reservatorios, ObjectId> {

    @Query(value = "{}", sort = "{ 'data': -1 }")
    List<Reservatorios> findLatest();

    @Query("{ 'data': { $gte: ?0, $lte: ?1 } }")
    List<Reservatorios> findByDateRange(Date startDate, Date endDate);

    // Buscar por capacidade máxima exata
    @Query("{ 'Capacidade Máxima (m³)': ?0 }")
    List<Reservatorios> findByCapacidadeMaxima(Double capacidadeMaxima);

    // Buscar por intervalo de capacidade máxima
    @Query("{ 'Capacidade Máxima (m³)': { $gte: ?0, $lte: ?1 } }")
    List<Reservatorios> findByCapacidadeMaximaRange(Double min, Double max);

    // Buscar por volume exato
    @Query("{ 'volume': ?0 }")
    List<Reservatorios> findByVolume(Double volume);

    // Buscar por intervalo de volume
    @Query("{ 'volume': { $gte: ?0, $lte: ?1 } }")
    List<Reservatorios> findByVolumeRange(Double min, Double max);

    // Buscar por percentual de volume exato (string)
    @Query("{ '% Volume': ?0 }")
    List<Reservatorios> findByPercentualVolume(String percentualVolume);



}
