package com.gpads.moringa.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Pluviometro")
public class Pluviometro {

    @Id
    private ObjectId id;

    @Field("Data")
    private String data;

    @Field("Hora")
    private String hora;

    @Field("Medida de chuva (calculado)")
    private String medidaDeChuvaCalculado;

    @Field("Medida de chuva (contagem)")
    private String medidaDeChuvaContagem;


    public Pluviometro() {}

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getMedidaDeChuvaCalculado() { return medidaDeChuvaCalculado; }
    public void setMedidaDeChuvaCalculado(String medidaDeChuvaCalculado) { this.medidaDeChuvaCalculado = medidaDeChuvaCalculado; }

    public String getMedidaDeChuvaContagem() { return medidaDeChuvaContagem; }
    public void setMedidaDeChuvaContagem(String medidaDeChuvaContagem) { this.medidaDeChuvaContagem = medidaDeChuvaContagem; }
}
