package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Kalkulator VAT netto-brutto");

        ToggleGroup methodGroup = new ToggleGroup();

        RadioButton nettoToBrutto = new RadioButton("Od netto do brutto");
        nettoToBrutto.setToggleGroup(methodGroup);
        nettoToBrutto.setSelected(true);

        RadioButton bruttoToNetto = new RadioButton("Od brutto do netto");
        bruttoToNetto.setToggleGroup(methodGroup);

        RadioButton adjustVAT = new RadioButton("Dopasuj do kwoty VAT");
        adjustVAT.setToggleGroup(methodGroup);

        VBox methodBox = new VBox(10, nettoToBrutto, bruttoToNetto, adjustVAT);

        Label baseValueLabel = new Label("Wartość bazowa:");
        TextField baseValueField = new TextField("2000,00");

        Label vatRateLabel = new Label("Stawka VAT:");
        ComboBox<String> vatRateBox = new ComboBox<>();
        vatRateBox.getItems().addAll("23%", "8%", "5%");
        vatRateBox.setValue("23%");

        GridPane dataPane = new GridPane();
        dataPane.setHgap(10);
        dataPane.setVgap(10);
        dataPane.add(baseValueLabel, 0, 0);
        dataPane.add(baseValueField, 1, 0);
        dataPane.add(vatRateLabel, 0, 1);
        dataPane.add(vatRateBox, 1, 1);

        Button calculateButton = new Button("OBLICZ");
        Button closeButton = new Button("Zamknij");

        HBox buttonBox = new HBox(10, calculateButton, closeButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        Label nettoLabel = new Label("Netto:");
        Label nettoValue = new Label("2.000,00");

        Label vatLabel = new Label("VAT:");
        Label vatValue = new Label("460,00 @ 23%");

        Label bruttoLabel = new Label("Brutto:");
        Label bruttoValue = new Label("2.460,00");

        GridPane resultPane = new GridPane();
        resultPane.setHgap(10);
        resultPane.setVgap(10);
        resultPane.add(nettoLabel, 0, 0);
        resultPane.add(nettoValue, 1, 0);
        resultPane.add(vatLabel, 0, 1);
        resultPane.add(vatValue, 1, 1);
        resultPane.add(bruttoLabel, 0, 2);
        resultPane.add(bruttoValue, 1, 2);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(methodBox, dataPane, calculateButton, resultPane, buttonBox);

        calculateButton.setOnAction(e -> {
            try {
                double baseValue = Double.parseDouble(baseValueField.getText().replace(",", "."));
                double vatRate = Double.parseDouble(vatRateBox.getValue().replace("%", "")) / 100;

                if (nettoToBrutto.isSelected()) {
                    double vatAmount = baseValue * vatRate;
                    double bruttoValueResult = baseValue + vatAmount;

                    nettoValue.setText(String.format("%.2f", baseValue).replace(".", ","));
                    vatValue.setText(String.format("%.2f @ %s", vatAmount, vatRateBox.getValue()).replace(".", ","));
                    bruttoValue.setText(String.format("%.2f", bruttoValueResult).replace(".", ","));
                } else if (bruttoToNetto.isSelected()) {
                    double nettoValueResult = baseValue / (1 + vatRate);
                    double vatAmount = baseValue - nettoValueResult;

                    nettoValue.setText(String.format("%.2f", nettoValueResult).replace(".", ","));
                    vatValue.setText(String.format("%.2f @ %s", vatAmount, vatRateBox.getValue()).replace(".", ","));
                    bruttoValue.setText(String.format("%.2f", baseValue).replace(".", ","));
                }

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wprowadź poprawne dane liczbowe!");
                alert.showAndWait();
            }
        });

        closeButton.setOnAction(e -> primaryStage.close());

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
