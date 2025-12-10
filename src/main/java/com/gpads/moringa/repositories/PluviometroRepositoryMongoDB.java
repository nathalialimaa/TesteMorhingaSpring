package com.gpads.moringa.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gpads.moringa.entities.Pluviometro;

public interface PluviometroRepositoryMongoDB extends MongoRepository<Pluviometro, ObjectId> {

    // Busca por medida de chuva calculado
    List<Pluviometro> findByMedidaDeChuvaCalculadoGreaterThan(Double medida);

    List<Pluviometro> findByMedidaDeChuvaCalculadoLessThan(Double medida);

    List<Pluviometro> findByMedidaDeChuvaCalculado(Double medida);

    // Busca por medida de chuva contagem
    List<Pluviometro> findByMedidaDeChuvaContagemGreaterThan(Double medida);

    List<Pluviometro> findByMedidaDeChuvaContagemLessThan(Double medida);

    List<Pluviometro> findByMedidaDeChuvaContagem(Double medida);

}