package it.unicam.cs.twopie.tarnas.view.utils;

import it.unicam.cs.twopie.App;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LenCell extends TableCell<RNAFile, RNAFile> {

    private final ImageButton imageButton;

    public LenCell(Image image) {
        this.imageButton = new ImageButton(image);
    }

    @Override
    protected void updateItem(RNAFile rnaFile, boolean empty) {
        super.updateItem(rnaFile, empty);
        if (rnaFile == null) {
            setGraphic(null);
            return;
        }
        setGraphic(imageButton);
        this.imageButton.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/len.fxml"));
                Parent root = loader.load();
                var stage = new Stage();
                var textArea = (TextArea) loader.getNamespace().get("rnaFileContent");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Preview");
                stage.setScene(new Scene(root, 600, 300));
                rnaFile.getContent().forEach(l -> textArea.appendText(l + "\n"));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
