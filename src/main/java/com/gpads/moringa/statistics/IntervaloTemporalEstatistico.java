package com.gpads.moringa.statistics;

import java.time.LocalDateTime;
import java.util.Map;

public class IntervaloTemporalEstatistico {
    private String intervaloTempo;
    private Map<String, AnaliseEstatistica> mapaDados;



    public IntervaloTemporalEstatistico() {
    }

    public IntervaloTemporalEstatistico(LocalDateTime inicioHora, Map<String, AnaliseEstatistica> mapaDados) {
        this.intervaloTempo = inicioHora.toString() + " - " + inicioHora.plusHours(1).toString();
        this.mapaDados = mapaDados;
    }

    public String getIntervaloTempo() {
        return this.intervaloTempo;
    }

    public void setIntervaloTempo(String intervaloTempo) {
        this.intervaloTempo = intervaloTempo;
    }

    public Map<String,AnaliseEstatistica> getMapaDados() {
        return this.mapaDados;
    }

    public void setMapaDados(Map<String,AnaliseEstatistica> mapaDados) {
        this.mapaDados = mapaDados;
    }
    
    
}