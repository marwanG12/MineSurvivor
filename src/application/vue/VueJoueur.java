package application.vue;

import application.modele.Entite;
import application.modele.Environnement;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class VueJoueur {
    private Entite joueur;
    private Image perso;
    private ImageView viewperso;
    private int fps = 0;
    private int img = 1;
    private String mouvement;
    private boolean left = false, right = false, jump = false;



    public VueJoueur(Entite joueur, Environnement env) {
        this.joueur = joueur;
        perso = new Image("application/images/Knights/sprite1.PNG");
        viewperso = new ImageView(perso);
    }

    public void affichePerso(BorderPane borderpane) {
        viewperso.xProperty().bind(joueur.getXProperty());
        viewperso.yProperty().bind(joueur.getYProperty());
        viewperso.setViewport(new Rectangle2D(20, 150, 32, 45));
        borderpane.getChildren().add(viewperso);
    }

    public ImageView viewperso() {
        return viewperso;
    }

    public void updatePerso(String action) {
        switch (action) {
            case "UP":
                viewperso.setViewport(new Rectangle2D(28, 15, joueur.getWidth(), 45));
                break;
            case "UP2":
                viewperso.setViewport(new Rectangle2D(158, 15, joueur.getWidth(), 45));
                break;
            case "LEFT":
                viewperso.setViewport(new Rectangle2D(26, 80, joueur.getWidth(), 45));
                viewperso.setScaleX(-1);
                break;
            case "RIGHT":
                viewperso.setViewport(new Rectangle2D(26, 80, joueur.getWidth(), 45));
                viewperso.setScaleX(1);
                break;
            case "RUN2":
                viewperso.setViewport(new Rectangle2D(155, 80, joueur.getWidth(), 45));
                break;
            case "RUN3":
                viewperso.setViewport(new Rectangle2D(408, 80, joueur.getWidth(), 45));
                break;
            case "RUN4":
                viewperso.setViewport(new Rectangle2D(532, 77, joueur.getWidth(), 45));
                break;
            case "STATIC":
                viewperso.setViewport(new Rectangle2D(20, 150, 32, 45));
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

    public void verifJump(boolean j, boolean r, boolean l) {
        if (j == true) {
            jump = true;
        } else if (r == true) {
            right = true;
        } else if (l == true) {
            left = true;
        }

        if (jump == true && right == true) {
            mouvement = "RIGHT-UP";
            jump = false;
            right = false;
        } else if (jump == true && left == true) {
            mouvement = "LEFT-UP";
            jump = false;
            left = false;
        } else if (jump == true) {
            mouvement = "UP";
            jump = false;
        }
    }

    public void unMouvement (String mvt) {
        if (img == 1) {
            mouvement = mvt;
            updatePerso(mouvement);
            img++;
        } else {
            verifJump(jump, right, left);
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
        verifJump(jump, right, left);
        joueur.seDeplace(mouvement);
    }

    public void animationMouvement(String mouvement) {
        if (fps == 0) {
            AnimationTimer timer = new AnimationTimer() {
                private long lastUpdate = 0;
                @Override
                public void handle(long now) {
                    if (now - lastUpdate >= 500_000_00) { // delay
                        unMouvement(mouvement);
                        lastUpdate = now;
                        fps++;
                    }
                    if (fps == 5) {
                        verifJump(jump, right, left);
                        updatePerso("STATIC");
                        jump = false;
                        left = false;
                        right = false;
                        joueur.verifGravite();
                        stop();
                        fps = 0;
                        img = 1;
                    }
                }
            };
            timer.start();
        }
    }

}