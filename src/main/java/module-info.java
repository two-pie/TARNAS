module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;

    opens it.unicam.cs.twopie.tarnas.view to javafx.fxml;
    exports it.unicam.cs.twopie.tarnas.view;

}
