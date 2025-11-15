package com.example.apsreservaquarto.modelos;
import java.time.LocalDate;

public class ReservaAtiva extends Reserva {
    public ReservaAtiva(Quarto quarto, Hospede hospede, LocalDate dataInicio) {
        super(quarto, hospede, dataInicio);
    }
}