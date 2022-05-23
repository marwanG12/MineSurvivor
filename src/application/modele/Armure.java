package application.modele;

public class Armure extends Item{

    private double PVdefence;

    public Armure(int id, String nom, double defence) {
        super(id, nom);
        this.PVdefence=defence;
    }

    public double getPVdefence(){
        return PVdefence;
    }

}
