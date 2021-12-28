package project.frontend;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import project.elements.Animal;
import project.orientation.MoveDirection;

public class SelectedAnimalGui
{
    private final Label gens = new Label("Gene: ");
    private final Label kids = new Label("Number of kids: ");
    private final Label descendants = new Label("Number of descendants: ");
    private final Label deathDay = new Label("Day of death: ");
    private final VBox information;
    public Animal animal = null;
    public boolean deadReported = false;
    public boolean isDead = false;
    private int previousKids;

    public SelectedAnimalGui()
    {
        Label name = new Label("Selected animal:");
        name.setStyle("-fx-font-weight: bold");
        this.information = new VBox(name,this.gens, this.kids, this.descendants, this.deathDay);
    }

    public void updateDeath(int day)
    {
        this.isDead = true;
        this.deathDay.setText("Day of death: " + day);
    }

    public void updateInformations(int descendants)
    {
        this.kids.setText("Number of kids: " + (animal.getBreedAmount() - this.previousKids));
        this.descendants.setText("Number of descendants: " + descendants);
    }

    public VBox getInformation() {
        return information;
    }

    public void newAnimal(Animal animal)
    {
        this.animal = animal;
        this.animal.setDescendant(true);
        this.isDead = false;
        this.deadReported = false;
        previousKids = animal.getBreedAmount();
        this.gens.setText("Gene: " + gensToString());
        this.kids.setText("Number of kids: 0");
        this.descendants.setText("Number of descendants: 0");
        this.deathDay.setText("Day of death: ");
    }

    public String gensToString()
    {
        StringBuilder gens = new StringBuilder();
        for (MoveDirection gen : this.animal.getGen())
        {
            switch (gen)
            {
                case FORWARD: gens.append("0"); break;
                case LIT_RIGHT: gens.append("1"); break;
                case RIGHT: gens.append("2"); break;
                case MOR_RIGHT: gens.append("3"); break;
                case BACKWARD: gens.append("4"); break;
                case MOR_LEFT: gens.append("5"); break;
                case LEFT: gens.append("6"); break;
                case LIT_LEFT: gens.append("7"); break;
            }
        }
        return gens.toString();
    }
}
