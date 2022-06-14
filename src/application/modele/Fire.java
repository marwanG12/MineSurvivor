package application.modele;

import javafx.beans.property.IntegerProperty;

public class Fire extends Entite {

    private static String url = "application/images/fire.png";
    private IntegerProperty x,y;
    private int width=8, height=8;
    private Necromancer necromancer;
    private double degat;
    private boolean touch;

    public Fire(Necromancer necromancer, double degat, Environnement env) {
        super(1, necromancer.getX(), necromancer.getY()+12, env, "fire", url);
        this.necromancer = necromancer;
        this.degat = degat;
    }

    public void checkDirection() {
        if (necromancer.isPosL()) {
            super.setLeft(true);
            super.setRight(false);
        } else if (necromancer.isPosR()) {
            super.setRight(false);
            super.setRight(true);
        }  
    }

    

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void agit() {
        this.seDeplace();
        Entite e = this.checkZone(1);
        if (e instanceof Joueur) {
            this.meurt();
            env.getJoueur().decrementerPv(1);
            if (env.getJoueur().getPv() == 0) {
                env.getJoueur().meurt();
            }
        }
    }
}
