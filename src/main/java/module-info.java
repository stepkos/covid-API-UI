module com.example.covidapiui {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.covidapiui to javafx.fxml;
    exports com.example.covidapiui;
}