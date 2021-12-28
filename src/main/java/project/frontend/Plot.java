package project.frontend;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Plot {
    private final LineChart lineChart;
    private final XYChart.Series dataSeries;

    public Plot(String namex, String namey)
    {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(namex);
        yAxis.setLabel(namey);
        this.lineChart = new LineChart(xAxis, yAxis);
        lineChart.setCreateSymbols(false);
        this.dataSeries = new XYChart.Series();
        lineChart.getData().add(dataSeries);
    }

    public void updateData(int x, int y)
    {
        this.dataSeries.getData().add(new XYChart.Data(x, y));
    }

    public LineChart getLineChart()
    {
        return lineChart;
    }
}
