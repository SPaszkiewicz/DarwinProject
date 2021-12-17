package project.frontend;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import project.IMapObserver;
import project.maps.IWorldMap;
import project.orientation.Vector2d;
import project.simulation.Statistics;

import java.io.FileNotFoundException;

public class MapGridPane
{
    private final GridPane visualization= new GridPane();
    private final IWorldMap map;
    private final int sizeX;
    private final int sizeY;

    public MapGridPane(IWorldMap map)
    {
        this.map = map;
        this.sizeX = map.getMapRightSize().getX();
        this.sizeY = map.getMapRightSize().getY();
        for (int i = 0; i < sizeX+1; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(50); // width in pixels
            this.visualization.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < sizeY+1; i++) {
            RowConstraints rowConstraints = new RowConstraints(50);
            this.visualization.getRowConstraints().add(rowConstraints);
        }
    }

    public GridPane updateMap() throws FileNotFoundException
    {
        this.visualization.getChildren().clear();
        System.out.println(this.sizeX);
        System.out.println( this.sizeY);
        MapElement element;
        for (int i = 0; i < sizeX+1; i++)
        {
            for (int j = 0; j < sizeY+1; j++)
            {
                element = new MapElement(this.map.objectAt(new Vector2d(i,j)), this.map.isInJungle(new Vector2d(i,j)));
                this.visualization.add(element.getField(),i,j);
            }
        }
        return this.visualization;
    }

}
