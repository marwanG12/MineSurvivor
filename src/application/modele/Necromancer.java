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



    @Override
    public void agit() {
        Entite e = this.checkZone(100);
        if (e instanceof Joueur) {
            if (Fire.getLoop() == 0) {
                fire = new Fire(this, 1, env);
                if (env.getEntites().size() >= env.getFires().size()) {
                    fire.setRight(super.isRight());
                    fire.setLeft(super.isLeft());
                    env.getFires().add(fire);
                    fire.setActive(true);
                } 
                    /* 
                    if (reussitProba(20)) {
                        env.getJoueur().decrementerPv(0.25);
                    }
                    if (env.getJoueur().getPv() == 0) {
                        env.getJoueur().meurt();
                    }*/
                
            }
        } else {
            seDeplaceAlea();
            verifGravite();
        }
        
    }

    public String getUrl() {
        return url;
    }

}
