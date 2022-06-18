package application.vue;

import application.modele.Entite;
import application.modele.Environnement;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueJoueur {
    private Pane pane;
    private Entite joueur;
    private Image perso;
    private ImageView viewperso;
    private int fps = 0;
    private int img = 1;
    private int timerUp = 0;
    private String mouvement;
    private ProgressBar pvBar;

    public VueJoueur(Entite joueur, Environnement env, Pane pane, ProgressBar pvBar) {
        this.joueur = joueur;
        this.pane = pane;
        this.pvBar = pvBar;
        perso = new Image(joueur.getUrl());
        viewperso = new ImageView(perso);
        pvBar.getStylesheets().add("application/vue/style.css");
        initializePerso();
        affichePV();
    }

    public void initializePerso() {
        viewperso.xProperty().bind(joueur.getXProperty());
        viewperso.yProperty().bind(joueur.getYProperty());
        viewperso.setViewport(new Rectangle2D(20, 150, 32, 45));
        viewperso.setFitHeight(joueur.getHeight());
        pane.getChildren().add(1, viewperso);
    }

    public void affichePV() {
        pvBar.progressProperty().bind(joueur.getPvProperty().divide(10));
    }

    public void remove() {
        pane.getChildren().remove(viewperso);
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
                viewperso.setViewport(new Rectangle2D(29, 150, joueur.getWidth(), 45));
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

    public void verifJumpParabole() {
        if ((joueur.isUp() && (joueur.isRight() || joueur.isLeft())) || timerUp > 0) {
            if (timerUp == 0) {
                fps = 0;
                img = 2;
            }
            timerUp++;
            mouvement = "UP";
        }
    } 

    public void unMouvement (String mvt) {
        if (img == 1) {
            mouvement = mvt;
            updatePerso(mouvement);
            img++;
        } else {
            verifJumpParabole();
            if (mouvement.contains("UP")) {
                if (img == 3) {
                    updatePerso("UP");
                    img--;
                } else {
                    updatePerso("UP" + img);
                    img++;
                }
            } else if (mouvement.indexOf("HIT") != -1) {
                updatePerso(mouvement + img);
                if (img == 4) {
                    img = 1;
                } else {
                    img++;
                }
            } else {
                updatePerso("RUN" + img);
                if (img == 4) {
                    img = 1;
                } else {
                    img++;
                }
            }
        }
    }

    public void animationMouvement(String mouvement) {
        if (fps == 0) {
            AnimationTimer timer = new AnimationTimer() {
                private long lastUpdate = 0;
                @Override
                public void handle(long now) {
                    if (now - lastUpdate >= 750_000_00) { // delay
                        joueur.colision();
                        unMouvement(mouvement);
                        verifJumpParabole();
                        lastUpdate = now;
                        fps++;
                    }
                    if (fps == 5) {
                        updatePerso("STATIC");
                        joueur.verifGravite();
                        stop();
                        fps = 0;
                        img = 1;
                        timerUp = 0;
                        joueur.setUp(false);
                    }
                }
            };
            timer.start();
        }
    }


}