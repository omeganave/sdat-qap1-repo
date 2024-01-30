module org.example.qap1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.qap1 to javafx.fxml;
    exports org.example.qap1;
}