package application.modele;

public class Armes extends Item{

    private double degats;

    public Armes(int id, String nom, double degats) {
        super(id, nom);
        this.degats=degats;
    }

    public double getDegats(){
        return degats;
    }
}
