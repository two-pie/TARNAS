module it.unicam.cs.twopie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;

    opens it.unicam.cs.twopie.tarnas.model.rnafile to javafx.base;
    exports it.unicam.cs.twopie.tarnas.model.rnafile;
    opens it.unicam.cs.twopie to javafx.fxml;
    exports it.unicam.cs.twopie;
    opens it.unicam.cs.twopie.tarnas.view to javafx.fxml;
    exports it.unicam.cs.twopie.tarnas.view;
}
