package com.example.apsreservaquarto.modelos;

public class DadosBancariosHospede {
    private String numCartao;
    private String tipoCartao;

    public DadosBancariosHospede(String numCartao, String tipoCartao) {
        this.numCartao = numCartao;
        this.tipoCartao = tipoCartao;
    }

    public String getnCartao() { return numCartao; }
    public String getTipoCartao() { return tipoCartao; }
}