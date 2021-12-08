package project;

import project.elements.Animal;
import project.maps.IWorldMap;
import project.orientation.MapDirection;
import project.orientation.MoveDirection;

import java.util.Arrays;
import java.util.Random;

public class Generate {
    public static int[] shareGens(int[] parentOne, int[] parentTwo, int EnergyParentOne, int EnergyParentTwo) {
        int[] gens = new int[32];

        Arrays.sort(parentOne);
        Arrays.sort(parentTwo);
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
        return gens;
    } //TODO DO POPRAWY ALE MI SIE TO PODOBA :(

    public static int[] gens() {
        int[] gen = new int[32];
        for (int i = 0; i < 32; i += 1) {
            gen[i] = randInt(0, 7);
        }
        return gen;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static Animal createAnimal(IWorldMap map, int energy) {
        return new Animal(map.getEmptyLocation(), map, MapDirection.NORTH, gens(), energy);
    }

    public static MapDirection randomDirection() // Sposob losowania wziety z stack overflow
    {
        return MapDirection.values()[new Random().nextInt(MapDirection.values().length)];
    }

}
