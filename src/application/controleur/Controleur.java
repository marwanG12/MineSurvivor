package application.controleur;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.Joueur;
import application.vue.VueJoueur;
import application.vue.VueMap;
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
            joueur.limiteMap();
            if(ke.getCode() == KeyCode.RIGHT || ke.getCode() == KeyCode.D) {
                if (joueur.isLimitemap() != "RIGHT") {
                    perso.verifJump(false, true, false);
                    joueur.setLimitemap("NONE");
                    perso.animationMouvement("RIGHT");
                    joueur.verifGravite();
                }
            } else if (ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.Q) {
                if (joueur.isLimitemap() != "LEFT") {
                    perso.verifJump(false, false, true);
                    joueur.setLimitemap("NONE");
                    perso.animationMouvement("LEFT");
                    joueur.verifGravite();
                }
            } else if (ke.getCode() == KeyCode.UP || ke.getCode() == KeyCode.Z) {
                if (joueur.isCanJump()) {
                    perso.verifJump(true, false, false);
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
                Duration.seconds(0.003),
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

/*    borderpane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        int codeTuile = ((int) event.getY()) / 32*32 +((int) event.getX())/16;
        env.enleveTuile(codeTuile);
        joueur.verifGravite();
        vue.modifierTuile(codeTuile);
    });*/



    @FXML
    public void update (KeyEvent event) {
    }


}
