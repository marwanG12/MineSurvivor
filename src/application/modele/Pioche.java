package application.modele;

public class Pioche extends Armes{

    private double efficacité;

    public Pioche(String nom, double degats, double efficacité) {
        super(nom, 0.5);
        this.efficacité=efficacité;
    }

    public double getEfficacité() {
        return efficacité;
    }
}
