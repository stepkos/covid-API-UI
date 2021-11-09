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
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Text dateText = new Text("Podaj date");
        DatePicker datePicker = new DatePicker();

        Text localizationText = new Text("Choose localization");
        ChoiceBox localization = new ChoiceBox();
        localization.getItems().addAll("Caly swiat", "Polska", "USA", "Rosja", "Niemcy");

        Text actionText = new Text("Action");
        CheckBox showInConsole = new CheckBox("Show");
        CheckBox saveToFile = new CheckBox("Save to file");

        Button executeButton = new Button("Execute");

        Text resultText = new Text("");

        executeButton.setStyle(
                executeButton.getStyle()
                        .concat("-fx-padding: 10px 30px;")
                        .concat("-fx-background-color: Orange")
        );

        executeButton.setOnAction(x -> {
//            System.out.println(showInConsole.isSelected());
//            System.out.println(saveToFile.isSelected());

            String restOfUrl;
            String date = datePicker.getValue().toString();
            DayOfCovid cov = null;

            switch (localization.getValue().toString()) {
                case "Polska" -> restOfUrl = "month/?region=poland";
                case "USA" -> restOfUrl = "month/?region=us";
                case "Rosja" -> restOfUrl = "month/?region=russia";
                case "Niemcy" -> restOfUrl = "month/?region=germany";
                default -> restOfUrl = "summary";
            }

            try {
                cov = new DayOfCovid(restOfUrl, date);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            if (showInConsole.isSelected()) {
                resultText.setText(cov.toString());
            }

            if (saveToFile.isSelected()) {
                cov.saveToJSONFile("result/save.json");
            }


        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600,500);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setStyle("-fx-background-color: LightCyan");

        gridPane.add(dateText,0,0);
        gridPane.add(datePicker,1,0);
        gridPane.add(localizationText,0,1);
        gridPane.add(localization,1,1);
        gridPane.add(actionText,0,2);
        gridPane.add(showInConsole,1,2);
        gridPane.add(saveToFile,2,2);
        gridPane.add(executeButton,1,5);
        gridPane.add(resultText,1,7);

        Scene scene = new Scene(gridPane);
        stage.setTitle("covid-API-UI");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}