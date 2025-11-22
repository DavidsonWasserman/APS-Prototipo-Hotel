package com.example.apsreservaquarto.modelos;

import java.util.Date;

public class Atividade {
    private String nome;
    private String local;
    private Date data;

    public Atividade(String nome, String local, Date data) {
        this.nome = nome;
        this.local = local;
        this.data = data;
    }

    public String getNome() {return nome;}
    public String getLocal() {return local;}
    public Date getData() {return data;}
}
