package com.example.apsreservaquarto;

import com.example.apsreservaquarto.controllers.ReservaController;
import com.example.apsreservaquarto.modelos.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Quarto quarto1 = new Quarto(1,101,100);
        Quarto quarto2 = new Quarto(2,201,200);
        Quarto quarto3 = new Quarto(3,301,300);
        Quarto quarto4 = new Quarto(4,401,400);
        List<Quarto> listaQuartos = Arrays.asList(quarto1,quarto2,quarto3,quarto4);

        //Cliente "logado" para exibir informações
        DadosBancariosHospede db = new DadosBancariosHospede("000000001234", "VISA");
        Hospede hospede = new Hospede("hospede@email.com","senha","Horacio Hospede", "123.456.789-00",db);

        //Reservar criada para exibir dias bloqueados
        DadosBancariosHospede db1 = new DadosBancariosHospede("000000005678", "MasterCard");
        Hospede hospede1 = new Hospede("hospede1@email.com", "senha1", "Carlos Cliente", "123.456.789-01", db1);
        NotaFiscal notaFiscal = new NotaFiscal(1000);
        ReservaFinalizada reservaFinalizada = new ReservaFinalizada(quarto2, hospede1, LocalDate.of(2025,11,27), LocalDate.of(2025,12,4), notaFiscal);
        List<ReservaFinalizada> reservaFinalizadas = Arrays.asList(reservaFinalizada);

        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("reservarQuarto.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);

        ReservaController controller = fxmlLoader.getController();
        controller.setQuartos(listaQuartos);
        controller.setHospede(hospede);
        controller.setReservasFinalizadas(reservaFinalizadas);

        primaryStage.setTitle("Simulação HotelApp");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(windowEvent -> {
            System.out.println("Encerrando...");
            System.exit(0);
        });
        primaryStage.show();
    }
}
