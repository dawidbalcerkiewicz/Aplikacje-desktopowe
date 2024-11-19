package org.example.demo1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        Label l1 = new Label("Ile kostek rzucasz (3-10)");
        TextField amount_txt = new TextField();

        Button btn = new Button("Rzuć kostkami");
        HBox hbox = new HBox(15);
        VBox vBox = new VBox(10, l1, amount_txt, btn, hbox);
        AtomicInteger points = new AtomicInteger();

        btn.setOnAction(e -> {

            points.set(0);
            hbox.getChildren().clear();

            String smth = amount_txt.getText();
            try {
                int amount = Integer.parseInt(smth);
                if (amount >= 3 && amount <= 10) {

                    // Rzuty kostkami
                    for (int i = 0; i < amount; i++) {
                        Random random = new Random();
                        int randNumber = random.nextInt(6) + 1;

                        if (randNumber == 1) {
                            InputStream stream = new FileInputStream("C:\\Users\\Uczen\\Desktop\\demo1\\src\\main\\java\\org\\example\\demo1\\1.jpg");
                            Image image = new Image(stream);
                            ImageView imageView = new ImageView();
                            imageView.setImage(image);
                            imageView.setX(3);
                            imageView.setY(3);
                            vBox.getChildren().add(imageView);
                        } if (randNumber == 2) {
                            InputStream stream = new FileInputStream("C:\\Users\\Uczen\\Desktop\\demo1\\src\\main\\java\\org\\example\\demo1\\2.jpg");
                            Image image = new Image(stream);
                            ImageView imageView = new ImageView();
                            imageView.setImage(image);
                            imageView.setX(3);
                            imageView.setY(3);
                            vBox.getChildren().add(imageView);
                        } if (randNumber == 3) {
                            InputStream stream = new FileInputStream("C:\\Users\\Uczen\\Desktop\\demo1\\src\\main\\java\\org\\example\\demo1\\3.jpg");
                            Image image = new Image(stream);
                            ImageView imageView = new ImageView();
                            imageView.setImage(image);
                            imageView.setX(3);
                            imageView.setY(3);
                            vBox.getChildren().add(imageView);
                        } if (randNumber == 4) {
                            InputStream stream = new FileInputStream("C:\\Users\\Uczen\\Desktop\\demo1\\src\\main\\java\\org\\example\\demo1\\4.jpg");
                            Image image = new Image(stream);
                            ImageView imageView = new ImageView();
                            imageView.setImage(image);
                            imageView.setX(3);
                            imageView.setY(3);
                            vBox.getChildren().add(imageView);
                        } if (randNumber == 5) {
                            InputStream stream = new FileInputStream("C:\\Users\\Uczen\\Desktop\\demo1\\src\\main\\java\\org\\example\\demo1\\5.jpg");
                            Image image = new Image(stream);
                            ImageView imageView = new ImageView();
                            imageView.setImage(image);
                            imageView.setX(3);
                            imageView.setY(3);
                            vBox.getChildren().add(imageView);
                        } if (randNumber == 6) {
                            InputStream stream = new FileInputStream("C:\\Users\\Uczen\\Desktop\\demo1\\src\\main\\java\\org\\example\\demo1\\6.jpg");
                            Image image = new Image(stream);
                            ImageView imageView = new ImageView();
                            imageView.setImage(image);
                            imageView.setX(3);
                            imageView.setY(3);
                            vBox.getChildren().add(imageView);
                        }

                        points.addAndGet(randNumber);

                        //String rand = Integer.toString(randNumber);
                        //Label cos = new Label(rand);
                        //hbox.getChildren().add(cos);
                    }

                    // Wyświetlanie sumy punktów
                    String points_str = Integer.toString(points.get());
                    Label point = new Label("Wynik: " + points_str);
                    vBox.getChildren().add(point);

                } else {
                    showAlert("Błąd", "Liczba musi być w zakresie 3-10.");
                }
            } catch (NumberFormatException | FileNotFoundException ex) {
                showAlert("Błąd", "Proszę wprowadzić liczbę.");
            }
        });



        Scene scene = new Scene(vBox, 700, 700);
        stage.setTitle("Aplikacja do rzutu kostkami");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
