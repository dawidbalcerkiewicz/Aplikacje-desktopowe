module com.example.zibzib {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zibzib to javafx.fxml;
    exports com.example.zibzib;
}