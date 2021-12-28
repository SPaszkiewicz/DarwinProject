package project.frontend;

import javafx.scene.layout.VBox;

public class PlotContainer
{
    private final VBox container;
    private final Plot animalsPlot = new Plot("Days", "Animals");
    private final Plot grassPlot = new Plot("Days", "Grass");
    private final Plot energyPlot = new Plot("Days", "Average energy per animal");
    private final Plot lifePlot = new Plot("Days", "Life expectancy");
    private final Plot kidsPlot = new Plot("Days", "Number of kids per animal");

    public PlotContainer()
    {
        this.container = new VBox(animalsPlot.getLineChart(), grassPlot.getLineChart(), energyPlot.getLineChart(), lifePlot.getLineChart(), kidsPlot.getLineChart());
    }

    public VBox getContainer() {
        return container;
    }

    public Plot getAnimalsPlot() {
        return animalsPlot;
    }

    public Plot getGrassPlot() {
        return grassPlot;
    }

    public Plot getEnergyPlot() {
        return energyPlot;
    }

    public Plot getLifePlot() {
        return lifePlot;
    }

    public Plot getKidsPlot() {
        return kidsPlot;
    }
}
