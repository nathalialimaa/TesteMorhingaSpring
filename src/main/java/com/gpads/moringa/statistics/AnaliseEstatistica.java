package com.gpads.moringa.statistics;

import java.util.List;

public class AnaliseEstatistica {
    private float media;
    private List<Float> moda;
    private List<Float> mediana;
    private float q1;
    private float q3;

    public AnaliseEstatistica() {
    }

    public AnaliseEstatistica(float media, List<Float> moda, List<Float> mediana, float q1, float q3) {
        this.media = media;
        this.moda = moda;
        this.mediana = mediana;
        this.q1 = q1;
        this.q3 = q3;
    }

    public float getMedia() {
        return this.media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public List<Float> getModa() {
        return this.moda;
    }

    public void setModa(List<Float> moda) {
        this.moda = moda;
    }

    public List<Float> getMediana() {
        return this.mediana;
    }

    public void setMediana(List<Float> mediana) {
        this.mediana = mediana;
    }

    public float getQ1() {
        return this.q1;
    }

    public void setQ1(float q1) {
        this.q1 = q1;
    }

    public float getq3() {
        return this.q3;
    }

    public void setq3(float q3) {
        this.q3 = q3;
    }

    @Override
    public String toString() {
        return "AnaliseEstatistica{" +
                "media=" + media +
                ", moda=" + moda +
                ", mediana=" + mediana +
                ", q1=" + q1 +
                ", q3=" + q3 +
                '}';
    }

}