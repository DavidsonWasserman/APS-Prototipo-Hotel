package com.example.apsreservaquarto.modelos;

public class Quarto {
    private final int andar;
    private final int numero;
    private boolean estaOcupado;

    public Quarto(int andar, int numero) {
        this.andar = andar;
        this.numero = numero;
        this.estaOcupado = false;
    }

    public int getAndar() { return andar; }
    public int getNumero() { return numero; }
    public boolean isEstaOcupado() { return estaOcupado; }
    public void setOcupado(boolean ocupado) { this.estaOcupado = ocupado; }

    @Override
    public String toString() {
        return "Quarto:" + numero + "\nAndar:" + andar + (estaOcupado ? "\n(OCUPADO)" : "\n(LIVRE)");
    }
}