package com.example.rejestr;

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
        Text name = new Text("Imię i nazwisko");
        TextField imieNazwisko = new TextField();
        Text dataUr = new Text("Data urodzenia");
        DatePicker dataUrodzenia = new DatePicker();

        Text plec = new Text("Płeć");
        ToggleGroup tgPlec = new ToggleGroup();
        RadioButton kobieta = new RadioButton("Kobieta");
        RadioButton mezczyzna = new RadioButton("Mężczyzna");
        kobieta.setToggleGroup(tgPlec);
        mezczyzna.setToggleGroup(tgPlec);
        kobieta.setUserData("Kobieta");
        mezczyzna.setUserData("Mężczyzna");

        Text jezyki = new Text("Języki programowania");
        CheckBox javaChk = new CheckBox("Java");
        CheckBox pythonChk = new CheckBox("Python");
        CheckBox cppChk = new CheckBox("C++");

        Text lokalizacjaBiura = new Text("Gdzie chciałbyś/chciałabyś pracować?");
        ChoiceBox lokalizacja = new ChoiceBox();
        lokalizacja.getItems().addAll("Warszawa", "Gdańsk", "Wrocław", "Kraków", "Katowice");

        Text txtWykszt = new Text("Wykształcenie");
        ObservableList<String> nazwyWykszt = FXCollections.observableArrayList("Podstawowe",
                "Średnie", "Licencjackie", "Inżynierskie", "Magisterskie");
        ListView<String> wyksztalcenieLista = new ListView<>(nazwyWykszt);

        Button zarejestruj = new Button("Zarejestruj");
        zarejestruj.setOnAction(x ->
                System.out.println(imieNazwisko.getText() + " "
                        + dataUrodzenia.getValue() + " "
                + tgPlec.getSelectedToggle().getUserData().toString() + " "
        + "Java: " + javaChk.isSelected() + " "
        + "Python: " + pythonChk.isSelected() + " "
        + "C++: " + cppChk.isSelected() + " "
        + lokalizacja.getValue() + " "
        + wyksztalcenieLista.getSelectionModel().getSelectedItem()));

        name.setStyle("-fx-font: normal bold 15px 'serif'");
        zarejestruj.setStyle("-fx-background-color: Beige; -fx-textfill: Blue;");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600,500);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setStyle("-fx-background-color: LightCyan");
        gridPane.add(name,0,0);
        gridPane.add(imieNazwisko,1,0);
        gridPane.add(dataUr,0,1);
        gridPane.add(dataUrodzenia,1,1);
        gridPane.add(plec,0,2);
        gridPane.add(kobieta,1,2);
        gridPane.add(mezczyzna,2,2);
        gridPane.add(jezyki,0,3);
        gridPane.add(javaChk,1,3);
        gridPane.add(pythonChk,2,3);
        gridPane.add(cppChk,3,3);
        gridPane.add(lokalizacjaBiura,0,4);
        gridPane.add(lokalizacja,1,4);
        gridPane.add(txtWykszt,0,5);
        gridPane.add(wyksztalcenieLista,1,5);
        gridPane.add(zarejestruj,3,6);

        Scene scene = new Scene(gridPane);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}