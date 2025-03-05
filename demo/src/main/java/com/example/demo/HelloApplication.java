package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Przyciski
        Button btn = new Button("stworz tabele");
        Button btn2 = new Button("dodaj rekord");
        Button btn3 = new Button("aktualizuj rekord");
        Button btn4 = new Button("usun rekord");
        Button btn5 = new Button("wyszukaj rekord");

        VBox vbox = new VBox(btn, btn2, btn3, btn4, btn5);

        // Łączenie z bazą danych
        JDBCUtil jdbc = new JDBCUtil("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "1234");

        jdbc.getConnection();
        Connection connection = jdbc.connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "1234");

        btn.setOnAction(actionEvent -> {
            System.out.println("sigma");
        });

        Scene scene = new Scene(vbox, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
class JDBCUtil {
    private String className, URL, user, password;
    Connection connection;

    public JDBCUtil(String className, String URL, String user, String password) {
        this.className = className;
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.connection = null;
    }

    public void getConnection() {
        // Załaduj klasę sterownika
        try {
            Class.forName(className);
        } catch (ClassNotFoundException ex) {
            System.out.println("Unable to load the class. Terminating the program");
            System.exit(-1);
        }
        // Uzyskaj połączenie
        try {
            connection = DriverManager.getConnection(URL, user, password);
            System.out.println("Connection successful!");
        } catch (SQLException ex) {
            System.out.println("Error getting connection: " + ex.getMessage());
            System.exit(-1);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            System.exit(-1);
        }
    }
}
