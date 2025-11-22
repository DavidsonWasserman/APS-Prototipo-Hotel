package com.example.apsreservaquarto.modelos;

public class Funcionario extends Usuario {
    private DadosBancariosFuncionario dadosBancariosFuncionario;

    public Funcionario(String email, String senha, String nome, String cpf, DadosBancariosFuncionario dadosBancariosFuncionario) {
        super(email, senha, nome, cpf);
        this.dadosBancariosFuncionario = dadosBancariosFuncionario;
    }

    public DadosBancariosFuncionario getDadosBancariosFuncionario() {return dadosBancariosFuncionario;}
}
