package project.frontend;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import project.elements.Animal;
import project.elements.Grass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MapElement
{
    private final Pane field = new Pane();

    public MapElement(Object object, boolean isJungle) throws FileNotFoundException {
        if (isJungle)
            field.setBackground(new Background(new BackgroundFill(Color.web("8ab32a"), CornerRadii.EMPTY, Insets.EMPTY)));
        else
            field.setBackground(new Background(new BackgroundFill(Color.web("f0e68c"), CornerRadii.EMPTY, Insets.EMPTY)));
        if(object instanceof Grass)
        {
            Image image = new Image(new FileInputStream("src/main/resources/grass.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            Grass grass = (Grass) object;
            field.getChildren().add(imageView);
        }
        else if(object instanceof Animal)
        {
            Image image = new Image(new FileInputStream("src/main/resources/animal.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            Animal animal = (Animal) object;
            field.getChildren().add(imageView);
        }
    }

    public Pane getField() {
        return field;
    }
}
