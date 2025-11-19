package com.example.apsreservaquarto;

import com.example.apsreservaquarto.modelos.Quarto;
import java.time.LocalDate;

public class DTO {
    private final Quarto quarto;
    private final LocalDate inicio;
    private final LocalDate fim;
    private final long dias;
    private final double total;

    public DTO(Quarto quarto, LocalDate inicio, LocalDate fim, long dias, double total) {
        this.quarto = quarto;
        this.inicio = inicio;
        this.fim = fim;
        this.dias = dias;
        this.total = total;
    }

    public Quarto getQuarto() {
        return quarto;
    }
    public LocalDate getInicio() {
        return inicio;
    }
    public LocalDate getFim() {
        return fim;
    }
    public long getDias() {
        return dias;
    }
    public double getTotal() {
        return total;
    }
}
