package application.modele;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class VueJoueur {
    private Entite joueur;


    public VueJoueur(Entite joueur) {
        this.joueur = joueur;
    }

    public void affichePerso(BorderPane borderpane) {
        Image perso = new Image("application/images/Knights/png/_Idle.png");
        ImageView viewperso = new ImageView(perso);
        viewperso.xProperty().bind(joueur.getXProperty());
        viewperso.yProperty().bind(joueur.getYProperty());
        viewperso.setViewport(new Rectangle2D(20, 40, 44, 41));
        borderpane.getChildren().add(viewperso);
    }

}