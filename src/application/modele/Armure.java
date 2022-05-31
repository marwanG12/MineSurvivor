package application.modele;

public class Armure extends Item{

    private double PVdefence;

    public Armure(String nom, double defence) {
        super(nom);
        this.PVdefence=defence;
    }

    public double getPVdefence(){
        return PVdefence;
    }

}
