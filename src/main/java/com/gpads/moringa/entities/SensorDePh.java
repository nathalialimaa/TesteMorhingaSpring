package com.gpads.moringa.entities;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Document("SensorDePh")
public class SensorDePh {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    @SuppressWarnings("unsed")
    private ObjectId id;

    @Field("Data")
    private String data;
    @Field("Hora")
    private String hora;
    @Field("Ph")
    private String ph;

    public SensorDePh() {}

    public SensorDePh(String data, String hora, String ph) {
        this.data = data;
        this.hora= hora;
        this.ph = ph;
    }

    public String getData() {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getHora()
    {
        return hora;
    }

    public void setHora(String hora)
    {
        this.hora=hora;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }
}
