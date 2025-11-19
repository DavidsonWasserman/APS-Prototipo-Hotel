package com.example.apsreservaquarto.modelos;

public class Quarto {
    private final int andar;
    private final int numero;
    private final float diaria;
    private boolean estaOcupado;

    public Quarto(int andar, int numero, float diaria) {
        this.andar = andar;
        this.numero = numero;
        this.diaria = diaria;
        this.estaOcupado = false;
    }

    public int getAndar() { return andar; }
    public int getNumero() { return numero; }
    public float getDiaria() { return diaria; }
    public boolean isEstaOcupado() { return estaOcupado; }
    public void setOcupado(boolean ocupado) { this.estaOcupado = ocupado; }

    @Override
    public String toString() {
        return "Quarto:" + numero + "\nAndar:" + andar + "\nDiaria: R$: " + diaria + (estaOcupado ? "\n(OCUPADO)" : "\n(LIVRE)");
    }
}