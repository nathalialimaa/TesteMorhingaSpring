package com.gpads.moringa.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.SensorDeSolo;
import com.gpads.moringa.repositories.SensorDeSoloRepositoryMongoDB;

@Service
public class SensorDeSoloService {

    private final SensorDeSoloRepositoryMongoDB sensorDeSoloRepository;

    public SensorDeSoloService(SensorDeSoloRepositoryMongoDB sensorDeSoloRepository) {
        this.sensorDeSoloRepository = sensorDeSoloRepository;
    }

    public void save(SensorDeSolo sensorDeSolo) {
        sensorDeSoloRepository.save(sensorDeSolo);
    }

    public List<SensorDeSolo> findAll() {
        return sensorDeSoloRepository.findAll();
    }

    public Optional<SensorDeSolo> findById(ObjectId id) {
        return sensorDeSoloRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        sensorDeSoloRepository.deleteById(id);
    }

}
