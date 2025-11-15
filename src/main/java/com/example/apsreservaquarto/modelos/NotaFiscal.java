package com.example.apsreservaquarto.modelos;

public class NotaFiscal {
    private static int COUNTER = 1;
    private final int id;
    private double valor;

    public NotaFiscal(double valor) {
        this.id = COUNTER++;
        this.valor = valor;
    }

    public int getId() { return id; }
    public double getValor() { return valor; }
}