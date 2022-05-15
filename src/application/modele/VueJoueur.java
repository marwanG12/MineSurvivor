package application.modele;

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

    public VueJoueur(Entite joueur, Environnement env) {
        this.joueur = joueur;
        perso = new Image("application/images/Knights/sprite2.png");
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
        }
    }

}