package com.example.apsreservaquarto.modelos;

import java.time.LocalDate;

public class ReservaFinalizada extends Reserva {
    private LocalDate dataTermino;
    private NotaFiscal notaFiscal;

    public ReservaFinalizada(Quarto quarto, Hospede hospede, LocalDate dataInicio, LocalDate dataTermino, NotaFiscal notaFiscal) {
        super(quarto, hospede, dataInicio);
        this.dataTermino = dataTermino;
        this.notaFiscal = notaFiscal;
    }

    public LocalDate getDataTermino() { return dataTermino; }
    public NotaFiscal getNotaFiscal() { return notaFiscal; }
}