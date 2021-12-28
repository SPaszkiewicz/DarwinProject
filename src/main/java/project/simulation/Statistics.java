package project.simulation;

public class Statistics
{
    public final int averageEnergyPerAnimal;
    public final int amountOfGrass;
    public final int amountOfAnimals;
    public final int averageKidsPerAnimal;
    public final int lifeExpectancy;
    public final int descendants;
    public final String dominant;

    public Statistics(int averageEnergyPerAnimal, int amountOfGrass, int amountOfAnimals, int averageKidsPerAnimal, int lifeExpectancy, int descendants, String dominant)
    {
        this.descendants = descendants;
        this.averageEnergyPerAnimal = averageEnergyPerAnimal;
        this.amountOfGrass = amountOfGrass;
        this.amountOfAnimals = amountOfAnimals;
        this.averageKidsPerAnimal = averageKidsPerAnimal;
        this.lifeExpectancy = lifeExpectancy;
        this.dominant = dominant;
    }
}
