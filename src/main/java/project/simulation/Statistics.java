package project.simulation;

public class Statistics
{
    public final int averageEnergyPerAnimal;
    public final int amountOfGrass;
    public final int amountOfAnimals;
    public final int averageKidsPerAnimal;
    public final int lifeExpectancy;

    public Statistics(int averageEnergyPerAnimal, int amountOfGrass, int amountOfAnimals, int averageKidsPerAnimal, int lifeExpectancy)
    {
        this.averageEnergyPerAnimal = averageEnergyPerAnimal;
        this.amountOfGrass = amountOfGrass;
        this.amountOfAnimals = amountOfAnimals;
        this.averageKidsPerAnimal = averageKidsPerAnimal;
        this.lifeExpectancy = lifeExpectancy;
    }
}
