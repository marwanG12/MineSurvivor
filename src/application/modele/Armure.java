package application.modele;

public class Armure extends Item{

    private double PVdefence;

    public Armure(String nom, double defense, String url) {
        super(nom, url);
        this.PVdefence=defense;
    }

    public double getPVdefence(){
        return PVdefence;
    }

}
