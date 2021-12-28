package project.frontend;

import javafx.geometry.Insets;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import project.elements.Animal;
import project.elements.Grass;
import java.io.FileNotFoundException;

public class MapElement
{
    private final StackPane field = new StackPane();
    private IMapObserver observer;
    private Object element = null;

    public MapElement(Object object, boolean isJungle, ImageStorage images, int baseEnergy, int plantEnergy, int size) throws FileNotFoundException {
        if (isJungle)
            this.field.setBackground(new Background(new BackgroundFill(Color.web("8ab32a"), CornerRadii.EMPTY, Insets.EMPTY)));
        else
            this.field.setBackground(new Background(new BackgroundFill(Color.web("f0e68c"), CornerRadii.EMPTY, Insets.EMPTY)));
        if(object instanceof Grass)
        {
            ImageView imageView = new ImageView(images.grassImage);
            imageView.setFitWidth(size);
            imageView.setFitHeight(size);
            Grass grass = (Grass) object;
            this.field.getChildren().add(imageView);
        }
        else if(object instanceof Animal)
        {
            ImageView imageView = new ImageView(images.animalImage);
            imageView.setFitWidth(size * 9 / 10);
            imageView.setFitHeight(size * 9 / 10);
            Animal animal = (Animal) object;
            double contrast = ((double) animal.getEnergy())/((double) (baseEnergy+10*plantEnergy));
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setContrast(contrast - 0.5);
            imageView.setEffect(colorAdjust);
            this.field.getChildren().add(imageView);
            this.element = object;
            makeClickable();
        }
    }

    public void addObserver(IMapObserver mapObserver)
    {
        observer = mapObserver;
    }

    public void makeClickable()
    {
        this.field.setOnMouseClicked(action ->
        {
            observer.setTraction((Animal) element);
        });
    }

    public Pane getField() {
        return field;
    }
}
