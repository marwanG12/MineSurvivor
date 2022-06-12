package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

import javax.lang.model.element.ElementVisitor;

public class Environnement {

    private int[] map = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 5, 2, 2, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 21, 34, 34, 34, 34, 22, 0, 5, 2, 2, 2, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 238, 92, 18, 18, 109, 18, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 254, 34, 34, 34, 34, 34, 22, 0, 10, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 194, 56, 194, 252, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 55, 194, 252, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3,
            17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19,
            17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19,
            33, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 35
    };

    private int width = 960;
    private int height = 640;

    private ObservableList<Entite> pnj;
    private Inventaire inventaire;
    private Joueur joueur;
    private IntegerProperty nbTours;

    public Environnement(Inventaire inventaire) {
        this.nbTours = new SimpleIntegerProperty(0);
        this.pnj = FXCollections.observableArrayList();
        this.inventaire = inventaire;
        initializeEntite();
    }

    public void initializeEntite() {
        pnj.add(new Necromancer(200, 32, this, "necromancier"));
        pnj.add(new Necromancer(400, 32, this, "necromancier"));
        pnj.add(new Necromancer(300, 32, this, "necromancier"));
        joueur = new Joueur(208, 400, this, inventaire);
    }

    public Joueur getJoueur() { return joueur; }

    public IntegerProperty getNbTours() { return this.nbTours; }

    public ObservableList<Entite> getEntites() { return pnj; }

    public  void setNbTours(int n){ this.nbTours.setValue(n); }

    public void addEntite(Entite a){ pnj.add(a); }

    public int getNbTiles() { return map.length; }

    public int[] getTiles() { return map; }

    public int getTile(int code) { return map[code]; }

    public Entite getEntite(int id) {
        for(Entite a : this.pnj) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public void oneRound() {
        for (Entite e : pnj) {
            if (e.isDead()) {
                pnj.remove(e);
                inventaire.getRessources().get(2).setNombre(inventaire.getRessources().get(2).getNombre()+1);;
            } else {
                e.agit();
            }
        }
    }
    
    public void enleveTuile(int codeTuile){
        map[codeTuile] = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}