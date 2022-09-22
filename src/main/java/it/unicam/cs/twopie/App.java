package it.unicam.cs.twopie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * TARNAS JavaFX App
 *
 * @author Piero Hierro, Piermichele Rosati
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = new FXMLLoader(App.class.getResource("home.fxml")).load();
        var scene = new Scene(root, 500, 500);
        stage.setTitle("TARNAS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}