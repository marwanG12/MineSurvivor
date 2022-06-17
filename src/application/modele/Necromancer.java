package application.modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Necromancer extends Entite {

    private static String url = "application/images/Necromancier.png";
    private Fire fire;
    public Necromancer(int x, int y, Environnement env, String nom) {
        super(2, x, y, env, nom, url);
    }


    public void generateFire() {
        if (fire == null) {
            fire = new Fire(this, 1, env);
            fire.setRight(super.isRight());
            fire.setLeft(super.isLeft());
            env.getFires().add(fire);
        }
    }

    public void removeFire() {
        env.getFires().remove(fire);
        fire = null;
    }

    @Override
    public void agit() {
        Entite e = this.checkZone(100);
        if (e instanceof Joueur) {
            if (fire == null) {
                generateFire();
            }
            /*
            if (reussitProba(20)) {
                env.getJoueur().decrementerPv(0.25);
            }
            if (env.getJoueur().getPv() == 0) {
                env.getJoueur().meurt();
            }*/
        } else {
            seDeplaceAlea();
            verifGravite();
        }
        
    }

    public String getUrl() {
        return url;
    }

}
