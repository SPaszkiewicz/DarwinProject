package project.simulation;

import java.io.*;
import java.util.ArrayList;

public class CsvWriter
{
    private final ArrayList<float[]> stats = new ArrayList<>();
    private final String name;
    public CsvWriter(String name)
    {
        this.name = name;
    }


    public void gameStatistics(Statistics st, int day) throws IOException
    {
        float[] data = {day, st.amountOfAnimals, st.amountOfGrass, st.averageEnergyPerAnimal, st.lifeExpectancy, st.averageKidsPerAnimal};
        this.stats.add(data);

    }

    public void makeStatistics() throws IOException {
        float animal = 0;
        float grass = 0;
        float avgEnergy = 0;
        float expLife = 0;
        float avgKids = 0;
        int lines = 0;
        try (FileWriter writer = new FileWriter(this.name))
        {
            writer.write("day,animals,grass,avgEnergy,expLife,avgKids\n");
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        for (float[] data: this.stats)
        {
            try (FileWriter writer = new FileWriter(name, true))
            {
                writer.write(data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "\n");
                animal += (int) data[1];
                grass += (int) data[2];
                avgEnergy += (int) data[3];
                expLife += (int) data[4];
                avgKids += (int) data[5];
                lines += 1;
            }
            catch (FileNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
        }

        if (lines == 0) lines = 1;
        String data = "  " + "," + animal/lines + "," + grass/lines + "," + avgEnergy/lines + "," + expLife/lines + "," + avgKids/lines + "\n";
        try (FileWriter writer = new FileWriter(this.name, true))
        {
            writer.write(data);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

}


