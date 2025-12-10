package com.gpads.moringa.entities;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Document("SensorDeSolo")
public class SensorDeSolo {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    @SuppressWarnings("unsed")
    private ObjectId id;

    @Field("Data")
    private String data;
    @Field("Hora")
    private String hora;
    @Field("Temperatura(C)")
    private String temperatura;
    @Field("Umidade(%)")
    private String umidade;
    @Field("Ph")
    private String ph;
    @Field("Condutividade(uS/cm)")
    private String condutividade;

    public SensorDeSolo() {}

    public SensorDeSolo(String data, String hora, String temperatura, String umidade, String ph, String condutividade) {
        this.data = data;
        this.hora = hora;
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.ph = ph;
        this.condutividade = condutividade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getUmidade() {
        return umidade;
    }

    public void setUmidade(String umidade) {
        this.umidade = umidade;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getCondutividade() {
        return condutividade;
    }

    public void setCondutividade(String condutividade) {
        this.condutividade = condutividade;
    }

}
