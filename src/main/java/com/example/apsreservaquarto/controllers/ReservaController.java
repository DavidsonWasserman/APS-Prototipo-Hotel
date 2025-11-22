package com.example.apsreservaquarto.controllers;

import com.example.apsreservaquarto.DTO;
import com.example.apsreservaquarto.Launcher;
import com.example.apsreservaquarto.modelos.Hospede;
import com.example.apsreservaquarto.modelos.Quarto;
import com.example.apsreservaquarto.modelos.ReservaFinalizada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReservaController {
    @FXML private ListView<Quarto> quartoList;
    @FXML private DatePicker dataInicio;
    @FXML private DatePicker dataFim;
    @FXML private TextField valorReserva;
    @FXML private Label mensagemError;

    private List<Quarto> quartos;
    private Hospede hospede;
    private List<ReservaFinalizada> reservasFinalizadas;

    @FXML
    private void initialize() {
        // listeners de cálculo
        quartoList.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            calcularValor();
            // força re-render dos calendários quando muda o quarto
            refrescarDatePickers();
        });
        dataInicio.valueProperty().addListener((obs, oldV, newV) -> calcularValor());
        dataFim.valueProperty().addListener((obs, oldV, newV) -> calcularValor());

        // aplica bloqueio visual nos DatePickers
        configurarDatePicker(dataInicio);
        configurarDatePicker(dataFim);
    }

    public void setQuartos(List<Quarto> quartos){
        this.quartos = quartos;
        quartoList.getItems().setAll(quartos);
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public void setReservasFinalizadas(List<ReservaFinalizada> reservasFinalizadas) {
        this.reservasFinalizadas = reservasFinalizadas;
        refrescarDatePickers();
    }

    private void refrescarDatePickers() {
        configurarDatePicker(dataInicio);
        configurarDatePicker(dataFim);
        dataInicio.setValue(dataInicio.getValue());
        dataFim.setValue(dataFim.getValue());
    }

    private void configurarDatePicker(DatePicker datePicker) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (empty) return;

                Quarto quartoSelecionado = quartoList.getSelectionModel().getSelectedItem();
                if (quartoSelecionado == null || reservasFinalizadas == null) {
                    setDisable(false);
                    setStyle(null);
                    setTooltip(null);
                    return;
                }

                boolean ocupado = false;
                for (ReservaFinalizada reservaFinalizada : reservasFinalizadas) {
                    if (reservaFinalizada.getQuarto().equals(quartoSelecionado)) {
                        if (!date.isBefore(reservaFinalizada.getDataInicio()) && !date.isAfter(reservaFinalizada.getDataTermino())) {
                            ocupado = true;
                            break;
                        }
                    }
                }

                if (ocupado) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ff9999;");
                    setTooltip(new Tooltip("Dia já reservado para este quarto"));
                } else {
                    setDisable(false);
                    setStyle(null);
                    setTooltip(null);
                }
            }
        });
    }

    private void calcularValor() {
        Quarto quarto = quartoList.getSelectionModel().getSelectedItem();
        if (quarto == null || dataInicio.getValue() == null || dataFim.getValue() == null) {
            valorReserva.setText("");
            return;
        }
        long dias = ChronoUnit.DAYS.between(dataInicio.getValue(), dataFim.getValue());
        if (dias < 1) dias = 1;
        double total = dias * quarto.getDiaria();
        valorReserva.setText(String.format("R$ %.2f", total));
    }

    @FXML
    private void onReservar(ActionEvent e) throws IOException {
        mensagemError.setVisible(false);
        Quarto quartoSelecionado = quartoList.getSelectionModel().getSelectedItem();
        if(quartoSelecionado == null) {
            mensagemError.setText("Quarto não selecionado");
            mensagemError.setVisible(true);
            return;
        }
        if (dataInicio.getValue() == null || dataFim.getValue() == null) {
            mensagemError.setText("Data de início e/ou fim não selecionadas");
            mensagemError.setVisible(true);
            return;
        }

        long dias = ChronoUnit.DAYS.between(dataInicio.getValue(), dataFim.getValue());
        if (dias < 0) {
            mensagemError.setText("A data final deve ser posterior à data inicial");
            mensagemError.setVisible(true);
            return;
        }
        if (dias == 0) dias = 1;

        double total = dias * quartoSelecionado.getDiaria();
        DTO dto = new DTO(quartoSelecionado, dataInicio.getValue(), dataFim.getValue(), dias, total);

        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("reservarConfirmacao.fxml"));
        Parent root = loader.load();

        ConfirmacaoController confirmacaoController = loader.getController();
        confirmacaoController.setDados(dto, hospede, quartos, reservasFinalizadas);

        Scene novaCena = new Scene(root);
        Stage stage = (Stage) quartoList.getScene().getWindow();
        stage.setScene(novaCena);
    }
}