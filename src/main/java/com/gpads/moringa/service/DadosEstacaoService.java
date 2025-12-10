package com.gpads.moringa.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.gpads.moringa.entities.DadosEstacao;
import com.gpads.moringa.repositories.DadosEstacaoRepositoryMongoDB;

@Service
public class DadosEstacaoService {

    private final DadosEstacaoRepositoryMongoDB dadosEstacaoRepository;

    public DadosEstacaoService(DadosEstacaoRepositoryMongoDB dadosEstacaoRepository) {
        this.dadosEstacaoRepository = dadosEstacaoRepository;
    }

    public DadosEstacao save(DadosEstacao dadosEstacao) {
        return dadosEstacaoRepository.save(dadosEstacao);
    }

    public List<DadosEstacao> findAll() {
        return dadosEstacaoRepository.findAll();
    }

    public Optional<DadosEstacao> findById(ObjectId id) {
        return dadosEstacaoRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        dadosEstacaoRepository.deleteById(id);
    }

}
