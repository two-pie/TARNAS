package it.unicam.cs.twopie.tarnas.view.utils;

import it.unicam.cs.twopie.App;
import it.unicam.cs.twopie.tarnas.controller.IOController;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.view.HomeController;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.Optional;

public class DeleteCell extends TableCell<RNAFile, RNAFile> {

    private final ImageButton imageButton;

    private final Alert trashAlert;

    public DeleteCell(Image image, EventHandler<? super MouseEvent> eventHandler) {
        this.imageButton = new ImageButton(image);
        this.trashAlert = new Alert(Alert.AlertType.CONFIRMATION);
        this.initTrashAlert(eventHandler);
    }

    private void initTrashAlert(EventHandler<? super MouseEvent> eventHandler) {
        this.trashAlert.initModality(Modality.APPLICATION_MODAL);
        var stage = (Stage) this.trashAlert.getDialogPane().getScene().getWindow();
        try {
            stage.getIcons().add(new Image(String.valueOf(App.class.getResource("/img/tarnas-icon.png").toURI())));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.setOnMouseClicked(eventHandler);
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
            if (this.confirmAndRemoveFile(rnaFile)) {
                getTableView().getItems().remove(rnaFile);
                IOController.getInstance().deleteFile(rnaFile);
                HomeController.logger.info("File removed succesfully");
            }
            else
                HomeController.logger.info("File not removed");
        });
    }

    private boolean confirmAndRemoveFile(RNAFile rnaFile) {
        this.trashAlert.setTitle("Deleting file confirmation");
        this.trashAlert.setHeaderText("Delete \"" + rnaFile.getFileName() + "\"?");
        this.trashAlert.setContentText("Are you sure you want to delete this file?");
        Optional<ButtonType> result = this.trashAlert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}