package it.unicam.cs.twopie.tarnas.view.utils;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrashButton extends Parent {

    public TrashButton(Image image){
        this.getChildren().add(new ImageView(image));
        this.setCursor(Cursor.HAND);
    }

}
