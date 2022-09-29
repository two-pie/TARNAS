module it.unicam.cs.twopie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;
    requires java.logging;

    opens it.unicam.cs.twopie.tarnas.model.rnastructure to javafx.base;
    exports it.unicam.cs.twopie.tarnas.model.rnastructure;

    opens it.unicam.cs.twopie.tarnas.model.rnafile to javafx.base;
    exports it.unicam.cs.twopie.tarnas.model.rnafile;

    opens it.unicam.cs.twopie.tarnas.model.utils to javafx.base;
    exports it.unicam.cs.twopie.tarnas.model.utils;

    opens it.unicam.cs.twopie to javafx.fxml;
    exports it.unicam.cs.twopie;

    exports it.unicam.cs.twopie.tarnas.view.utils;
    opens it.unicam.cs.twopie.tarnas.view.utils to javafx.fxml;

    exports it.unicam.cs.twopie.tarnas.view;
    opens it.unicam.cs.twopie.tarnas.view to javafx.fxml;
}
