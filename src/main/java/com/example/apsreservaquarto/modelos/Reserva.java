package com.example.apsreservaquarto.modelos;

import java.time.LocalDate;

public class Reserva {
    private Quarto quarto;
    private Hospede hospede;
    private LocalDate dataInicio;

    public Reserva(Quarto quarto, Hospede hospede, LocalDate dataInicio) {
        this.quarto = quarto;
        this.hospede = hospede;
        this.dataInicio = dataInicio;
    }

    public Quarto getQuarto() { return quarto; }
    public Hospede getHospede() { return hospede; }
    public LocalDate getDataInicio() { return dataInicio; }
}