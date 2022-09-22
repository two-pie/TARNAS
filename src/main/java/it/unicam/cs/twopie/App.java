package it.unicam.cs.twopie;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.random;

/**
 * TARNAS JavaFX App
 *
 * @author Piero Hierro, Piermichele Rosati
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //System.out.println(App.class.getResource("/fxml/home.fxml"));
        Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("/fxml/home.fxml")));
        // reference to: https://docs.oracle.com/javafx/2/get_started/animation.htm#BABGIACI
        /*Group root = new Group();
        Scene scene = new Scene(root, 1000, 600, Color.BLACK);
        stage.setScene(scene);*/
        stage.setTitle("TARNAS");
        stage.setScene(new Scene(root, 1000, 600));
        /*Group circles = new Group();
        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(150, Color.web("white", 0.05));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("red", 0.16));
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }
        root.getChildren().add(circles);
        Timeline timeline = new Timeline();
        for (Node circle: circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                            new KeyValue(circle.translateXProperty(), random() * 1000),
                            new KeyValue(circle.translateYProperty(), random() * 600)
                    ),
                    new KeyFrame(new Duration(40000), // set end position at 40s
                            new KeyValue(circle.translateXProperty(), random() * 1000),
                            new KeyValue(circle.translateYProperty(), random() * 600)
                    )
            );
        }*/
// play 40s of animation
        //timeline.play();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}