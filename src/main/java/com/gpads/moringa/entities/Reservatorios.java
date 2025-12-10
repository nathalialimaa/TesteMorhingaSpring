package com.gpads.moringa.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Document("Reservatorios")
public class Reservatorios {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    @SuppressWarnings("unsed")
    private ObjectId id;

    @Field("Capacidade Máxima (m³)")
    private Double capacidadeMaxima;
    @Field("% Volume")
    private String percentualVolume;
    @Field("cota")
    private Double cota;
    @Field("data")
    private Date data;
    @Field("volume")
    private Double volume;

    public Reservatorios() {}

    // CORREÇÃO AQUI: "String" com 'S' maiúsculo no parâmetro do construtor
    public Reservatorios(Double capacidadeMaxima, String percentualVolume, Double cota, Date data, Double volume) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.percentualVolume = percentualVolume;
        this.cota = cota;
        this.data = data;
        this.volume = volume;
    }


    public Double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getPercentualVolume() {
        return percentualVolume;
    }

    public void setPercentualVolume(String percentualVolume) {
        this.percentualVolume = percentualVolume;
    }

    public Double getCota() {
        return cota;
    }

    public void setCota(Double cota) {
        this.cota = cota;
    }

    public Date getData() {
        return data;
    }

    public String getDataString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}