package project.frontend;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageStorage
{
    public Image grassImage;
    public Image animalImage;

    public ImageStorage()
    {
        try {
            grassImage = new Image(new FileInputStream("src/main/resources/grass.png"), 100, 100, true, true);
            animalImage = new Image(new FileInputStream("src/main/resources/animal.png"), 100, 100, true, true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
