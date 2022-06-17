package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private ObservableList<Integer> map = FXCollections.observableArrayList (
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
    );

    private int width = 960;
    private int height = 640;

    private ObservableList<Entite> pnj;
    private ObservableList<Fire> fires;
    private Inventaire inventaire;
    private Joueur joueur;
    private IntegerProperty nbTours;

    public Environnement(Inventaire inventaire) {
        this.nbTours = new SimpleIntegerProperty(0);
        this.pnj = FXCollections.observableArrayList();
        this.fires = FXCollections.observableArrayList();
        this.inventaire = inventaire;
        initializeEntite();
    }

    public void initializeEntite() {
        pnj.add(new Necromancer(200, 32, this, "necromancier"));
        pnj.add(new Necromancer(400, 32, this, "necromancier"));
        pnj.add(new Necromancer(300, 32, this, "necromancier"));
        pnj.add(new Skeleton(208,48,this,"Skeleton"));
        pnj.add(new Skeleton(330,90,this,"Skeleton"));
        pnj.add(new Skeleton(440,60,this,"Skeleton"));
        joueur = new Joueur(208, 400, this, inventaire);
    }

    
    public ObservableList<Fire> getFires() { return fires; }

    public Joueur getJoueur() { return joueur; }

    public IntegerProperty getNbTours() { return this.nbTours; }

    public ObservableList<Entite> getEntites() { return pnj; }

    public  void setNbTours(int n){ this.nbTours.setValue(n); }

    public void addEntite(Entite a){ pnj.add(a); }

    public int getNbTiles() { return map.size(); }

    public ObservableList<Integer> getTiles() { return map; }

    public Integer getTile(int code) { return map.get(code); }

    public Entite getEntite(int id) {
        for(Entite a : this.pnj) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public void oneRound() {
        for (int i=0; i < pnj.size(); i++) {
            if (pnj.get(i).isDead()) {
                pnj.remove(i);
                inventaire.addRessource(2);
            } else {
                pnj.get(i).agit();
            }
        }

        for (int i=0; i < fires.size(); i++) {
            System.out.println(fires.get(i));
            if (!fires.get(i).isActive()) {
                fires.remove(i);
            } else {
                fires.get(i).agit();
            }
        }

    }

    public Integer deleteBloc(int x, int y) {
        int codeTuile = map.get((y / 32) * 30 + (x / 32));
        if (Math.abs(joueur.getY()-y)<2*32 && Math.abs(joueur.getX()-x)<2*32 ) {
            if (codeTuile != 0) {
                map.set((y / 32) * 30 + (x / 32), 0);
                if (codeTuile == 1) { //Bois pas encore integré dans la map
                    inventaire.addRessource(1);
                } else {
                    if (Joueur.reussitProba(20)) {
                        inventaire.addRessource(3);
                    } else {
                        inventaire.addRessource(0);
                    }
                }
                return Integer.valueOf(y / 32) * 30 + (x / 32);
            }
        }
        return null;
    }

    public Integer addBloc(int x, int y){
        int codeTuile = map.get((y / 32) * 30 + (x / 32));
        if (Math.abs(joueur.getY()-y)<2*32 && Math.abs(joueur.getX()-x)<2*32 && (joueur.getX()/32 != x/32 || joueur.getY()/32 != y/32)) {
            if (codeTuile == 0) {
                map.set((y / 32) * 30 + (x / 32), 60);
                return Integer.valueOf(y / 32) * 30 + (x / 32);
            }
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}