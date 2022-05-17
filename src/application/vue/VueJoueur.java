package application.vue;

import application.modele.Entite;
import application.modele.Environnement;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class VueJoueur {
    private Entite joueur;
    private Image perso;
    private ImageView viewperso;
    private int fps = 0;
    private int img = 1;



    public VueJoueur(Entite joueur, Environnement env) {
        this.joueur = joueur;
        perso = new Image("application/images/Knights/sprite2.PNG");
        viewperso = new ImageView(perso);
    }

    public void affichePerso(BorderPane borderpane) {
        viewperso.xProperty().bind(joueur.getXProperty());
        viewperso.yProperty().bind(joueur.getYProperty());
        viewperso.setViewport(new Rectangle2D(20, 150, 44, 45));
        borderpane.getChildren().add(viewperso);
    }

    public ImageView viewperso() {
        return viewperso;
    }

    public void updatePerso(String action) {
        switch (action) {
            case "UP":
                viewperso.setViewport(new Rectangle2D(20, 15, 44, 45));
                break;
            case "UP2":
                viewperso.setViewport(new Rectangle2D(150, 15, 44, 45));
                break;
            case "LEFT":
                viewperso.setViewport(new Rectangle2D(18, 80, 44, 45));
                viewperso.setScaleX(-1);
                break;
            case "RIGHT":
                viewperso.setViewport(new Rectangle2D(18, 80, 44, 45));
                viewperso.setScaleX(1);
                break;
            case "RUN2":
                viewperso.setViewport(new Rectangle2D(145, 80, 44, 45));
                break;
            case "RUN3":
                viewperso.setViewport(new Rectangle2D(395, 80, 44, 45));
                break;
            case "RUN4":
                viewperso.setViewport(new Rectangle2D(525, 77, 44, 45));
                break;
            case "STATIC":
                viewperso.setViewport(new Rectangle2D(20, 150, 44, 45));
                break;
            case "HIT":
                viewperso.setViewport(new Rectangle2D(275, 10, 44, 45));
                break;
            case "HIT2":
                viewperso.setViewport(new Rectangle2D(400, 10, 70, 45));
                break;
            case "HIT3":
                viewperso.setViewport(new Rectangle2D(517, 10, 70, 45));
                break;
            case "HIT4":
                viewperso.setViewport(new Rectangle2D(635, 10, 70, 45));
                break;
            default :
                break;
        }
    }

    public void unMouvement (String mouvement) {
        if (img == 1) {
            updatePerso(mouvement);
            img++;
        } else {
            if (mouvement != "UP") {
                if (mouvement != "HIT") {
                    updatePerso("RUN" + img);
                } else {
                    updatePerso(mouvement + img);
                }
                if (img == 4) {
                    img = 1;
                } else {
                    img++;
                }
            } else {
                if (img == 1) {
                    updatePerso("UP");
                    img++;
                } else {
                    updatePerso("UP" + img);
                    img--;
                }
            }
        }
        joueur.seDeplace(mouvement);
    }

    public void animationMouvement(String mouvement) {
        if (fps == 0) {
            AnimationTimer timer = new AnimationTimer() {
                private long lastUpdate = 0;
                @Override
                public void handle(long now) {
                    if (now - lastUpdate >= 750_000_00) { // delay
                        unMouvement(mouvement);
                        lastUpdate = now;
                        fps++;
                    }
                    if (fps == 5) {
                        updatePerso("STATIC");
                        joueur.verifGravite();
                        stop();
                        fps = 0;
                        img = 1;
                    }
                }
            };
            timer.start();
            joueur.verifGravite();
        }
    }

}