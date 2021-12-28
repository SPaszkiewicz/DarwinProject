package project.frontend;

import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import project.elements.Animal;
import project.maps.IWorldMap;
import project.orientation.Vector2d;

import java.io.FileNotFoundException;

public class MapGridPane extends Parent {
    private final GridPane visualization= new GridPane();
    private final IWorldMap map;
    private final int sizeX;
    private final int sizeY;
    private final GameScene gameScene;
    public final ImageStorage images;
    public final int size;
    public MapGridPane(IWorldMap map, GameScene gameScene)
    {
        this.gameScene = gameScene;
        this.map = map;
        this.sizeX = map.getMapRightSize().getX();
        this.sizeY = map.getMapRightSize().getY();
        if (sizeX > sizeY) this.size = 420/(this.sizeX+1);
        else this.size = 420/(this.sizeY+1);
        this.images = new ImageStorage();
        for (int i = 0; i < sizeX+1; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(this.size); // width in pixels
            this.visualization.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < sizeY+1; i++) {
            RowConstraints rowConstraints = new RowConstraints(this.size);
            this.visualization.getRowConstraints().add(rowConstraints);
        }
    }

    public GridPane updateMap() throws FileNotFoundException
    {
        MapElement element;
        for (int i = 0; i < sizeX+1; i++)
        {
            for (int j = 0; j < sizeY+1; j++)
            {
                element = new MapElement(this.map.objectAt(new Vector2d(i,j)), this.map.isInJungle(new Vector2d(i,j)), this.images, map.getStartEnergy(), map.getPlantEnergy(), this.size);
                if (this.map.objectAt(new Vector2d(i,j)) instanceof Animal) element.addObserver(gameScene);
                this.visualization.add(element.getField(),i,j);
            }
        }
        return this.visualization;
    }



    public void clearMap()
    {
        this.visualization.getChildren().clear();
    }

}
