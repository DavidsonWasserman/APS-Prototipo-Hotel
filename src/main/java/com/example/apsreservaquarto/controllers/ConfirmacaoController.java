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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ConfirmacaoController {

    @FXML private Label labelNome;
    @FXML private Label labelEmail;
    @FXML private Label labelCartao;
    @FXML private Label labelMarca;
    @FXML private Label labelQuarto;
    @FXML private Label labelAndar;
    @FXML private Label labelInicio;
    @FXML private Label labelFim;
    @FXML private Label labelValor;
    @FXML private Button btnConfirmar;
    @FXML private Button btnCancelar;

    private DTO dto;
    private Hospede hospede;
    private List<Quarto> quartos;
    private List<ReservaFinalizada> reservasFinalizadas;

    public void setDados(DTO dto, Hospede hospede, List<Quarto> quartos, List<ReservaFinalizada> reservaFinalizadas) {
        this.dto = dto;
        this.hospede = hospede;
        this.quartos = quartos;
        this.reservasFinalizadas = reservaFinalizadas;
        preenchertela();
    }

    private void preenchertela() {
        labelNome.setText(hospede.getNome());
        labelEmail.setText(hospede.getEmail());

        String cartao = hospede.getDadosBancarios().getnumCartao();
        String finalCartao = "**** " + cartao.substring(cartao.length() - 4);
        labelCartao.setText(finalCartao);
        labelMarca.setText(hospede.getDadosBancarios().getTipoCartao());

        labelQuarto.setText(String.valueOf(dto.getQuarto().getNumero()));
        labelAndar.setText(String.valueOf(dto.getQuarto().getAndar()));
        labelInicio.setText(dto.getInicio().toString());
        labelFim.setText(dto.getFim().toString());
        labelValor.setText(String.format("R$ %.2f", dto.getTotal()));
    }
    @FXML
    private void onConfirmar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reserva Confirmada");
        alert.setHeaderText(null);
        alert.setContentText("Reserva confirmada com sucesso!");
        alert.showAndWait();

        Stage stage = (Stage) btnConfirmar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancelar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("reservarQuarto.fxml"));
        Parent root = loader.load();

        ReservaController reservaController = loader.getController();
        reservaController.setHospede(hospede);
        reservaController.setQuartos(quartos);
        reservaController.setReservasFinalizadas(reservasFinalizadas);

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }
}