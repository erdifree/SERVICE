package com.example.demo.Entiy;

public enum PercSconto {

    VALUE_100("100"),
;

    private String percentuale;

    private PercSconto(String percentuale) {
        this.percentuale = percentuale;
    }

    public String getPercentuale() {
        return percentuale;
    }
}
