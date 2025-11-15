module com.example.apsreservaquarto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.apsreservaquarto to javafx.fxml;
    exports com.example.apsreservaquarto;
    exports com.example.apsreservaquarto.modelos;
    opens com.example.apsreservaquarto.modelos to javafx.fxml;
}