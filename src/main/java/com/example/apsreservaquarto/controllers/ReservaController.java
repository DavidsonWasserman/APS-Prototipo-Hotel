package com.example.apsreservaquarto.controllers;

import com.example.apsreservaquarto.DTO;
import com.example.apsreservaquarto.Launcher;
import com.example.apsreservaquarto.modelos.Hospede;
import com.example.apsreservaquarto.modelos.Quarto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    @FXML
    private void initialize() {
        quartoList.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> calcularValor());
        dataInicio.valueProperty().addListener((obs, oldV, newV) -> calcularValor());
        dataFim.valueProperty().addListener((obs, oldV, newV) -> calcularValor());
    }

    public void setQuartos(List<Quarto> quartos){
        this.quartos = quartos;
        quartoList.getItems().setAll(quartos);
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    private void calcularValor() {
        Quarto q = quartoList.getSelectionModel().getSelectedItem();
        if (q == null || dataInicio.getValue() == null || dataFim.getValue() == null) {
            valorReserva.setText("");
            return;
        }
        long dias = ChronoUnit.DAYS.between(dataInicio.getValue(), dataFim.getValue());
        if (dias < 1) dias = 1;
        double total = dias * q.getDiaria();
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
            mensagemError.setText("Data de inicio e/ou fim não selecionadas");
            mensagemError.setVisible(true);
            return;
        }

        long dias = ChronoUnit.DAYS.between(dataInicio.getValue(), dataFim.getValue());
        if (dias < 0) {
            mensagemError.setText("A data final deve ser posterior a data inicial");
            mensagemError.setVisible(true);
            return;
        }
        if (dias == 0) dias = 1;

        double total = dias * quartoSelecionado.getDiaria();
        DTO dto = new DTO(quartoSelecionado, dataInicio.getValue(), dataFim.getValue(), dias, total);

        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("reservarConfirmacao.fxml"));
        Parent root = loader.load();

        ConfirmacaoController confirmacaoController = loader.getController();
        confirmacaoController.setDados(dto, hospede, quartos);

        Scene novaCena = new Scene(root);
        Stage stage = (Stage) quartoList.getScene().getWindow();
        stage.setScene(novaCena);

    }
}
