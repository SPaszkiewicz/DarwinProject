package project.maps;

import project.elements.Animal;
import project.orientation.Vector2d;

import java.util.ArrayList;
import java.util.LinkedList;

public interface IWorldMap {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    boolean canMoveTo(Vector2d position);

    /**
     * Place a animal on the map.
     *
     * @param animal
     *            The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    boolean place(Animal animal);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /**
     * Return an object at a given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    Object objectAt(Vector2d position);


    Vector2d whereToMoveTo(Vector2d position);

    Vector2d getEmptyLocation();

    void grassEatingTime();

    LinkedList<Animal> executeDeath();

    void addGrassToJungle();

    void addGrassToSteppe();

    int getMoveEnergy();

    int getRotateEnergy();

    ArrayList<Animal> breeding();

    int getAmountOfGrass();

    Vector2d getMapRightSize();

    boolean isInJungle(Vector2d position);
}