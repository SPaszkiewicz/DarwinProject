package project;

import project.elements.Animal;
import project.maps.IWorldMap;
import project.orientation.MapDirection;
import project.orientation.MoveDirection;
import project.orientation.Vector2d;

import java.util.Arrays;
import java.util.Random;

public class Generate {
    public static MoveDirection[] shareGens(MoveDirection[] parentOne, MoveDirection[] parentTwo, int EnergyParentOne, int EnergyParentTwo) {
        MoveDirection[] gens = new MoveDirection[32];
        float ratio = (float) EnergyParentOne / (EnergyParentTwo + EnergyParentOne);
        int numOfGensForOne = (int) (32 * ratio);
        int chooseWhoFirst = randInt(1, 2);
        if (chooseWhoFirst == 1) {
            if (numOfGensForOne >= 0)
                System.arraycopy(parentOne, 0, gens, 0, numOfGensForOne);
            if (32 - numOfGensForOne >= 0)
                System.arraycopy(parentTwo, numOfGensForOne, gens, numOfGensForOne, 32 - numOfGensForOne);
        } else {
            if (32 - numOfGensForOne >= 0)
                System.arraycopy(parentTwo, 0, gens, 0, 32 - numOfGensForOne);
            if (32 - (32 - numOfGensForOne) >= 0)
                System.arraycopy(parentOne, 32 - numOfGensForOne, gens, 32 - numOfGensForOne, 32 - (32 - numOfGensForOne));
        }
        Arrays.sort(gens);
        return gens;
    }

    public static int[] gens() {
        int[] gen = new int[32];
        for (int i = 0; i < 32; i += 1) {
            gen[i] = randInt(0, 7);
        }
        Arrays.sort(gen);
        return gen;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static Animal createAnimal(IWorldMap map, int energy) {
        Vector2d postion = map.getEmptyLocation();
        if (!postion.equals(new Vector2d(-1,-1)))
            return new Animal(map.getEmptyLocation(), map, MapDirection.NORTH, DirectionParser.toDirection(gens()), energy);
        else
            return new Animal(map.getEmptyLocation(), map, MapDirection.NORTH, DirectionParser.toDirection(gens()), -1);
    }

    public static MapDirection randomDirection() // Sposob losowania wziety z stack overflow
    {
        return MapDirection.values()[new Random().nextInt(MapDirection.values().length)];
    }

}
