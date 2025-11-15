package com.example.apsreservaquarto.modelos;

public class Hospede extends Usuario {
    private DadosBancariosHospede dadosBancarios;

    public Hospede(String email, String senha, String nome, String cpf, DadosBancariosHospede dadosBancarios) {
        super(email, senha, nome, cpf);
        this.dadosBancarios = dadosBancarios;
    }

    public DadosBancariosHospede getDadosBancarios() { return dadosBancarios; }
}