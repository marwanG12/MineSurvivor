package application.modele;

public class Pioche extends Armes{

    private double efficacité;
    private static String url = "application/images/Pioche.png";

    public Pioche(String nom, double degats, double efficacité) {
        super(nom, 0.5, url);
        this.efficacité=efficacité;
    }

    public double getEfficacité() {
        return efficacité;
    }

}
