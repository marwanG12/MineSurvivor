package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private ObservableList<Integer> map = FXCollections.observableArrayList (
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 400, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 400, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 87, 2, 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 181, 92, 18, 18, 109, 18, 19, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 17, 18, 92, 18, 124, 18, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 193, 194, 195, 0, 0, 254, 34, 34, 34, 34, 34, 22, 0, 0, 0, 193, 194, 195, 0, 0,
            0, 0, 0, 0, 0, 0, 33, 34, 34, 34, 34, 34, 35, 0, 0, 0, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 22, 0, 0, 0, 0, 10, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 223, 6, 0, 0, 0, 0, 0, 0, 0, 10, 11, 0,
            0, 0, 0, 5, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0, 0, 0, 0, 254, 155, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 254, 255, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 193, 194, 195, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 193, 194, 195, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 193, 194, 195, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 193, 194, 194, 194, 195, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3,
            17, 18, 18, 18, 18, 18, 18, 124, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 109, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19,
            17, 18, 18, 92, 18, 18, 18, 18, 18, 18, 18, 18, 18, 124, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 109, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19,
            17, 93, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 92, 18, 19,
            17, 93, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 182, 18, 18, 124, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19,
            17, 93, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 182, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19,
            33, 214, 214, 214, 214, 214, 214, 214, 214, 214, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 248, 35
    );

    private int width = 1440;
    private int height = 896;

    private ObservableList<Entite> pnj;
    private ObservableList<Fire> fires;
    private Inventaire inventaire;
    private Joueur joueur;
    private Boolean gameover = false;
    private IntegerProperty nbTours;

    public Environnement(Inventaire inventaire) {
        this.nbTours = new SimpleIntegerProperty(0);
        this.pnj = FXCollections.observableArrayList();
        this.fires = FXCollections.observableArrayList();
        this.inventaire = inventaire;
        initializeEntite();
    }

    public void initializeEntite() {
        pnj.add(new Necromancer(500, 32, this, "necromancier"));
        pnj.add(new Necromancer(800, 32, this, "necromancier"));
        pnj.add(new Necromancer(1200, 32, this, "necromancier"));
        pnj.add(new Skeleton(308,48,this,"Skeleton"));
        pnj.add(new Skeleton(900,90,this,"Skeleton"));
        pnj.add(new Skeleton(1100,60,this,"Skeleton"));
        joueur = new Joueur(208, 400, this, inventaire);
    }

    
    public ObservableList<Fire> getFires() { return fires; }

    public Boolean getGameover() { return gameover; }

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
            if (!fires.get(i).isActive()) {
                fires.remove(i);
            } else {
                fires.get(i).agit();
            }
        }

        if  (joueur.getPv() == 0) {
            gameover = true;
        }

    }

    public Integer deleteBloc(int x, int y) {
        int codeTuile = map.get((y / 32) * 45 + (x / 32));
        if (Math.abs(joueur.getY()-y)<2*32 && Math.abs(joueur.getX()-x)<2*32 ) {
            if (codeTuile != 0) {
                map.set((y / 32) * 45 + (x / 32), 0);
                if (codeTuile == 300) { //Bois pas encore integré dans la map
                    inventaire.addRessource(1);
                } else if (codeTuile == 400) {
                    for (int i=0; i < 10; i++) inventaire.addRessource(2);
                } else {
                    if (Joueur.reussitProba(20)) {
                        inventaire.addRessource(3);
                    } else {
                        inventaire.addRessource(0);
                    }
                }
                return Integer.valueOf(y / 32) * 45 + (x / 32);
            }
        }
        return null;
    }

    public Integer addBloc(int x, int y){
        int codeTuile = map.get((y / 32) * 45 + (x / 32));
        if (Math.abs(joueur.getY()-y)<2*32 && Math.abs(joueur.getX()-x)<2*32 && (joueur.getX()/32 != x/32 || joueur.getY()/32 != y/32)) {
            if (codeTuile == 0) {
                map.set((y / 32) * 45 + (x / 32), 60);
                return Integer.valueOf(y / 32) * 45 + (x / 32);
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