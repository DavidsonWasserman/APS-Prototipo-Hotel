package com.example.apsreservaquarto.modelos;

public class DadosBancariosFuncionario {
    private String banco;
    private String agencia;
    private String conta;

    public DadosBancariosFuncionario(String banco, String agencia, String conta) {
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
    }

    public String getBanco() {return banco;}
    public String getAgencia() {return agencia;}
    public String getConta() {return conta;}
}
