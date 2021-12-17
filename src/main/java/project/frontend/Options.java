package project.frontend;

public class Options
{
    private final int width;
    private final int heigh;
    private final int startEnergy;
    private final int moveEnergy;
    private final int rotateEnergy;
    private final int plantEnergy;
    private final float jungleRatio;

    public Options(int width, int heigh, int startEnergy, int moveEnergy, int rotateEnergy, int plantEnergy, float jungleRatio) {
        this.width = width;
        this.heigh = heigh;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.rotateEnergy = rotateEnergy;
        this.plantEnergy = plantEnergy;
        this.jungleRatio = jungleRatio;
    }

    @Override
    public String toString() {
        return "Options{" +
                "width=" + width +
                ", heigh=" + heigh +
                ", startEnergy=" + startEnergy +
                ", moveEnergy=" + moveEnergy +
                ", rotateEnergy=" + rotateEnergy +
                ", plantEnergy=" + plantEnergy +
                ", jungleRatio=" + jungleRatio +
                '}';
    }

    public int getWidth() {
        return width;
    }

    public int getHeigh() {
        return heigh;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

    public int getRotateEnergy() {
        return rotateEnergy;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public float getJungleRatio() {
        return jungleRatio;
    }
}
