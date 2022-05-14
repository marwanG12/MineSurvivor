package application.controleur;

import application.Main;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.VueJoueur;
import application.modele.VueMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;



public class Controleur implements Initializable {
    private Environnement env;
    private Entite joueur;
    private VueMap terrain;
    private VueJoueur perso;

    @FXML
    private TilePane tilepane;

    @FXML
    private BorderPane borderpane;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        env = new Environnement();
        joueur = new Joueur(208, 473, env);
        terrain = new VueMap(env);
        perso = new VueJoueur(joueur);


        terrain.afficheMap(tilepane);
        perso.affichePerso(borderpane);

        borderpane.setOnKeyPressed(ke -> {
            if(ke.getCode() == KeyCode.RIGHT || ke.getCode() == KeyCode.D) {
                joueur.setX(joueur.getX()+16);
                perso.updatePerso("RIGHT");
            } else if (ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.Q) {
                joueur.setX(joueur.getX()-16);
                perso.updatePerso("LEFT");
            } else if (ke.getCode() == KeyCode.UP || ke.getCode() == KeyCode.Z) {
                joueur.setY(joueur.getY()-64);
                perso.updatePerso("UP");
            }
        });
    }

    @FXML
    public void update (KeyEvent event) {
    }



}
