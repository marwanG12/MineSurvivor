package application.modele;

import java.util.ArrayList;

public class Armes extends Item {

    private double degats;
    private ArrayList<Ressource> ressources;

    public Armes(String nom, double degats, String url) {
        super(nom, url);
        this.degats=degats;
    }


    public double getDegats(){
        return degats;
    }

    public boolean getCout() {
        int count = 0;
        for (int i=0; i < ressources.size(); i++) {
            if (ressources.get(i).getNom() == "Bois" && ressources.get(i).getNombre() == 1) {
                count++;
            } else if (ressources.get(i).getNom() == "Pierre" && ressources.get(i).getNombre() == 3) {
                count++;
            }
            if (count == 2) {
                return true;
            }
        }  
        return false;  
    }

}
