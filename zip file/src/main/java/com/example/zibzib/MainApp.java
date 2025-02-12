package com.example.zibzib;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        // Przyciski do kompresji i dekompresji
        Button compressButton = new Button("Kompresuj plik");
        Button decompressButton = new Button("Dekompresuj plik");


        Label statusLabel = new Label("Wybierz opcję:");

        // Obsługa kompresji
        compressButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                try {
                    fileToZip(selectedFile);
                    statusLabel.setText("Plik skompresowany");
                } catch (IOException ioException) {
                    statusLabel.setText("nah");
                }
            }
        });

        // Obsługa dekompresji
        decompressButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ZIP Files", "*.zip"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                try {
                    zipDecompressor(selectedFile);
                    statusLabel.setText("Plik zdekompresowany");
                } catch (IOException ioException) {
                    statusLabel.setText("nah");
                }
            }
        });

        // Obsługa wyjścia


        VBox vBox = new VBox(10, statusLabel, compressButton, decompressButton);
        Scene scene = new Scene(vBox, 400, 200);

        stage.setTitle("Kompresja i Dekompresja");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void zipDecompressor(File fileZip) throws IOException {
        String destDir = "output";

        File dir = new File(destDir);
        if (!dir.exists()) {
            dir.mkdirs();  // Tworzy katalogi, jeśli nie istnieją
        }

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            File newFile = new File(destDir + File.separator + zipEntry.getName());
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public void fileToZip(File sourceFile) throws IOException {
        String zipFileName = sourceFile.getName().replace(".txt", ".zip");

        FileOutputStream fos = new FileOutputStream(zipFileName);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        FileInputStream fis = new FileInputStream(sourceFile);
        ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
        zipOut.close();
        fos.close();
    }
}
