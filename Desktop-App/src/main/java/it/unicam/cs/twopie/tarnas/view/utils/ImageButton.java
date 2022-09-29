package it.unicam.cs.twopie.tarnas.view.utils;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButton extends Parent {

    public ImageButton(Image image){
        this.getChildren().add(new ImageView(image));
        this.setCursor(Cursor.HAND);
    }

}
