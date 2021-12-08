package project.elements;

import project.orientation.Vector2d;

public class DeathNote
{
    public final Animal animal;
    public final Vector2d position;

    public DeathNote(Animal animal, Vector2d position)
    {
        this.animal = animal;
        this.position = position;
    }
}
