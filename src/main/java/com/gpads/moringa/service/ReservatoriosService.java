package com.gpads.moringa.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.Reservatorios;
import com.gpads.moringa.repositories.ReservatoriosRepositoryMongoDB;

@Service
public class ReservatoriosService {

    private final ReservatoriosRepositoryMongoDB reservatoriosRepositoryMongoDB;

    public ReservatoriosService(ReservatoriosRepositoryMongoDB reservatoriosRepositoryMongoDB) {
        this.reservatoriosRepositoryMongoDB = reservatoriosRepositoryMongoDB;
    }

    public void save(Reservatorios entity) {
        reservatoriosRepositoryMongoDB.save(entity);
    }

    public Reservatorios findById(ObjectId id) {
        return reservatoriosRepositoryMongoDB.findById(id).orElse(null);
    }

    public List<Reservatorios> findAll() {
        return reservatoriosRepositoryMongoDB.findAll();
    }

    public void deleteById(ObjectId id) {
        reservatoriosRepositoryMongoDB.deleteById(id);
    }

}
