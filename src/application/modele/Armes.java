package application.modele;

import java.util.ArrayList;

public class Armes extends Item {

    private double degats;
    private ArrayList<Ressource> ressources;

    public Armes(int id, String nom, double degats) {
        super(id, nom);
        this.degats=degats;
    }


    public double getDegats(){
        return degats;
    }

}
