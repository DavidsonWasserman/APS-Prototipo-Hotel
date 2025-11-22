module com.example.apsreservaquarto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.example.apsreservaquarto to javafx.fxml;
    exports com.example.apsreservaquarto;
    exports com.example.apsreservaquarto.modelos;
    opens com.example.apsreservaquarto.modelos to javafx.fxml;
    exports com.example.apsreservaquarto.controllers;
    opens com.example.apsreservaquarto.controllers to javafx.fxml;
}