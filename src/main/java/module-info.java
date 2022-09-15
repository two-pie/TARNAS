module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens cs.example to javafx.fxml;
    exports cs.example;
}
