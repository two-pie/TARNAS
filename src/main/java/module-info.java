module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;


    opens cs.example to javafx.fxml;
    exports cs.example;
}
