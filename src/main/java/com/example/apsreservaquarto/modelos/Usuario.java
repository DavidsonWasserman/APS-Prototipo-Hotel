package com.example.apsreservaquarto.modelos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Usuario {
    private String email;
    private String senha;
    private String nome;
    private String cpf;

    public Usuario(String email, String senha, String nome, String cpf) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getEmail() {return email;}
    public String getNome() {return nome;}
    public String getCpf() {return cpf;}
}
