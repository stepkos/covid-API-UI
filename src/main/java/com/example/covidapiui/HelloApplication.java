package com.example.covidapiui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Text dateText = new Text("Podaj date");
        DatePicker datePicker = new DatePicker();

        Text localizationText = new Text("Choose localization");
        ChoiceBox localization = new ChoiceBox();
        localization.getItems().addAll("Caly swiat", "Polska", "USA", "Rosja", "Niemcy");

    }

    public static void main(String[] args) {
        launch();
    }
}