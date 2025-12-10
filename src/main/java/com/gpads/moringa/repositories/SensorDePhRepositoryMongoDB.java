package com.gpads.moringa.repositories;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gpads.moringa.entities.SensorDePh;

public interface SensorDePhRepositoryMongoDB extends MongoRepository<SensorDePh, ObjectId> {
    

    // Busca por pH
    List<SensorDePh> findByPhGreaterThan(Double ph);
    List<SensorDePh> findByPhLessThan(Double ph);
    List<SensorDePh> findByPh(Double ph);

}
