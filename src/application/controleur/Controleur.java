package application.controleur;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.Joueur;
import application.vue.VueJoueur;
import application.vue.VueMap;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;


import java.net.URL;
import java.util.ResourceBundle;


public class Controleur implements Initializable {
    private Environnement env;
    private Entite joueur;
    private VueMap terrain;
    private VueJoueur perso;
    private int r=1, l=1, h=1;
    private int fps = 0;
    private Timeline tl;
    private Timeline gameLoop;
    private int temps;


    @FXML
    private TilePane tilepane;

    @FXML
    private BorderPane borderpane;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        env = new Environnement();
        joueur = new Joueur(208, 468, env);
        terrain = new VueMap(env);
        perso = new VueJoueur(joueur, env);  

        terrain.afficheMap(tilepane);
        perso.affichePerso(borderpane);

        borderpane.setOnKeyPressed(ke -> {
            if(ke.getCode() == KeyCode.RIGHT || ke.getCode() == KeyCode.D) {
                perso.animationMouvement("RIGHT");
                joueur.verifGravite();
            } else if (ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.Q) {
                perso.animationMouvement("LEFT");
                joueur.verifGravite();
            } else if (ke.getCode() == KeyCode.UP || ke.getCode() == KeyCode.Z) {
                if (joueur.isCanJump()) {
                    perso.animationMouvement("UP");
                }
            }
        });

        borderpane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    perso.animationMouvement("HIT");
                }
            }
        });
        joueur.verifGravite();
        initAnimation();
        gameLoop.play();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.007),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if (joueur.isCiel() == true) {
                        joueur.setY(joueur.getY()+1);
                        joueur.verifGravite();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }




    @FXML
    public void update (KeyEvent event) {
    }


}
