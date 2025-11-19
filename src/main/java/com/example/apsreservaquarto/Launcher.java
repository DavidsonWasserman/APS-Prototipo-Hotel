package com.example.apsreservaquarto;

import com.example.apsreservaquarto.controllers.ReservaController;
import com.example.apsreservaquarto.modelos.DadosBancariosHospede;
import com.example.apsreservaquarto.modelos.Hospede;
import com.example.apsreservaquarto.modelos.Quarto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
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

        DadosBancariosHospede db = new DadosBancariosHospede("000000001234", "VISA");
        Hospede hospede = new Hospede("hospede@email.com","senha","Horacio Hospede", "123.456.789-00",db);

        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("reservarQuarto.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);

        ReservaController controller = fxmlLoader.getController();
        controller.setQuartos(listaQuartos);
        controller.setHospede(hospede);

        primaryStage.setTitle("Simulação HotelApp");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(windowEvent -> {
            System.out.println("Encerrando...");
            System.exit(0);
        });
        primaryStage.show();
    }
}
