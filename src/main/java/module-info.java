module com.example.covidapiui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.covidapiui to javafx.fxml;
    exports com.example.covidapiui;
}