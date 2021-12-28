package project.maps;

import project.Generate;
import project.elements.Animal;
import project.elements.Grass;
import project.orientation.Vector2d;

import java.util.ArrayList;

public class RightMap extends AbstractWorldMap {

    public RightMap(Vector2d mapLeftSize, Vector2d mapRightSize, int startEnergy, int moveEnergy, int plantEnergy, float jungleRatio, int rotateEnergy) {
        super(mapLeftSize, mapRightSize, startEnergy, moveEnergy, plantEnergy, jungleRatio, rotateEnergy);
        setJungleMap(this);
        setSteppeMap(this);
    }

    @Override
    public Vector2d whereToMoveTo(Vector2d position) {
        return position;
    }


    public void addGrassToJungle() {
        Vector2d position = jungle.getRandomPosition();
        if (!position.equals(new Vector2d(-1, -1))) {
            addElement(new Grass(position, this));
            amountOfGrass += 1;
        }
    }

    public void addGrassToSteppe() {
        Vector2d position = steppe.getRandomPosition();
        if (!position.equals(new Vector2d(-1, -1))) {
            addElement(new Grass(position, this));
            amountOfGrass += 1;
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.follows(super.mapLeftSize) && (position.precedes(super.mapRightSize)) && super.canMoveTo(position));
    }

    public ArrayList<Animal> breeding() {
        ArrayList<Animal> kids = new ArrayList<>();
        Animal[] couple;
        Animal newAnimal;
        for (Field field : fieldPosition.values()) {
            if (field.getElementsOnField().size() > 1) {
                couple = field.getForBreed();
                if (couple[0].getEnergy() > breedEnergy && couple[1].getEnergy() > breedEnergy) {
                    newAnimal = new Animal(
                            field.getPosition(),
                            this,
                            Generate.randomDirection(),
                            Generate.shareGens(couple[0].getGen(), couple[1].getGen(), couple[0].getEnergy(), couple[1].getEnergy()),
                            couple[1].getEnergy() / 4 + couple[0].getEnergy() / 4);
                    addElement(newAnimal);
                    kids.add(newAnimal);
                    couple[0].setEnergy(couple[0].getEnergy() * 3 / 4);
                    couple[1].setEnergy(couple[1].getEnergy() * 3 / 4);
                    couple[0].setBreedAmount(couple[0].getBreedAmount() + 1);
                    couple[1].setBreedAmount(couple[1].getBreedAmount() + 1);
                    if (couple[0].isDescendant() || couple[1].isDescendant()) newAnimal.setDescendant(true);
                }
            }
        }
        return kids;
    }
}
