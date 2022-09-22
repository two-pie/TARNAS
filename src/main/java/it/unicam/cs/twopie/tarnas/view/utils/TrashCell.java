package it.unicam.cs.twopie.tarnas.view.utils;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;

public class TrashCell extends TableCell<RNAFile,RNAFile> {

    private final TrashButton trashButton;

        public TrashCell(Image image) {
        this.trashButton = new TrashButton(image);
    }

    @Override
    protected void updateItem(RNAFile rnaFile, boolean empty) {
        super.updateItem(rnaFile, empty);
        if (rnaFile == null) {
            setGraphic(null);
            return;
        }
        setGraphic(trashButton);
        this.trashButton.setOnMouseClicked(event -> getTableView().getItems().remove(rnaFile));
    }
}
