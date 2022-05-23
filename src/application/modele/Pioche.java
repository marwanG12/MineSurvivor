package application.modele;

public class Pioche extends Armes{

    private double efficacité;

    public Pioche(int id, String nom, double degats, double efficacité) {
        super(id, nom, 0.5);
        this.efficacité=efficacité;
    }

    public double getEfficacité() {
        return efficacité;
    }
}
