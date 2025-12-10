package com.gpads.moringa.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.SensorDePh;
import com.gpads.moringa.repositories.SensorDePhRepositoryMongoDB;

@Service
public class SensorDePhService {

    private final SensorDePhRepositoryMongoDB sensorDePhRepositoryMongoDB;

    public SensorDePhService(SensorDePhRepositoryMongoDB sensorDePhRepositoryMongoDB) {
        this.sensorDePhRepositoryMongoDB = sensorDePhRepositoryMongoDB;
    }

    public void save(SensorDePh entity) {
        sensorDePhRepositoryMongoDB.save(entity);
    }

    public SensorDePh findById(ObjectId id) {
        return sensorDePhRepositoryMongoDB.findById(id).orElse(null);
    }

    public List<SensorDePh> findAll() {
        return sensorDePhRepositoryMongoDB.findAll();
    }

    public void deleteById(ObjectId id) {
        sensorDePhRepositoryMongoDB.deleteById(id);
    }

}
