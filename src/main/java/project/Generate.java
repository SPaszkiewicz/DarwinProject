package project;

import project.elements.Animal;
import project.maps.IWorldMap;
import project.orientation.MapDirection;

import java.util.Random;

public class Generate
{
    public static int[] shareGens(int[] parentOne, int[] parentTwo, int EnergyParentOne,  int EnergyParentTwo)
    {
        int[] gen = new int[32];
        for (int i = 0; i < 32; i += 1)
        {
            if (RandInt(0, EnergyParentOne + EnergyParentTwo) < EnergyParentOne) gen[i] = parentOne[i];
            else gen[i] = parentTwo[i];
        }
        return gen;
    } //TODO DO POPRAWY ALE MI SIE TO PODOBA :(

    public static int[] gens()
    {
        int[] gen = new int[32];
        for (int i = 0; i < 32; i += 1)
        {
            gen[i] = RandInt(0, 7);
        }
        return gen;
    }

    public static int RandInt(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static Animal createAnimal(IWorldMap map, int energy)
    {
        return new Animal(map.getEmptyLocation(), map, MapDirection.NORTH,gens() ,energy);
    }

}
