package com.gpads.moringa.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.Pluviometro;
import com.gpads.moringa.repositories.PluviometroRepositoryMongoDB;

@Service
public class PluviometroService {

    private final PluviometroRepositoryMongoDB pluviometroRepository;

    public PluviometroService(PluviometroRepositoryMongoDB pluviometroRepository) {
        this.pluviometroRepository = pluviometroRepository;
    }

    public void save(Pluviometro pluviometro) {
        pluviometroRepository.save(pluviometro);
    }

    public List<Pluviometro> findAll() {
        return pluviometroRepository.findAll();
    }

    public Optional<Pluviometro> findById(ObjectId id) {
        return pluviometroRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        pluviometroRepository.deleteById(id);
    }

}