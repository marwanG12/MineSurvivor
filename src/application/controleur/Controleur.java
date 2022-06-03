package application.controleur;

import application.modele.*;
import application.vue.VueJoueur;
import application.vue.VueMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;


import java.net.URL;
import java.util.ResourceBundle;


public class Controleur implements Initializable {
    private Environnement env;
    private Entite joueur;
    private Inventaire inventaire;
    private VueMap vueMap;
    private VueJoueur vueJoueur;
    private Timeline gameLoop;
    private int temps;


    @FXML
    private TilePane tilepane;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Pane pane;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        env = new Environnement();
        inventaire = new Inventaire();
        joueur = new Joueur(208, 468, env, inventaire);
        vueMap = new VueMap(env);
        vueJoueur = new VueJoueur(joueur, env);
        inventaire = new Inventaire();
        vueMap.afficheMap(tilepane);
        getVueJoueur().affichePerso(pane);

        borderpane.setOnKeyPressed(e -> {
            joueur.limiteMap();
            switch(e.getCode()) {
                case LEFT:
                    joueur.setLeft(true);
                    joueur.setLimitemap("NONE");
                    vueJoueur.animationMouvement("LEFT");

                    break;
                case RIGHT:
                    joueur.setRight(true);
                    joueur.setLimitemap("NONE");
                    vueJoueur.animationMouvement("RIGHT");
                    break;
                default:
                    break;
            }
        });

        borderpane.setOnKeyReleased(e -> {
            switch(e.getCode()) {
                case LEFT:
                    joueur.setLeft(false);
                    break;
                case RIGHT:
                    joueur.setRight(false);
                    break;
                case UP:
                    if (!joueur.isUp() && joueur.isCanJump()) {
                        joueur.setUp(true);
                        vueJoueur.animationMouvement("UP");
                    }
                    break;
                default:
                    break;
            }
        });

        borderpane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    vueJoueur.animationMouvement("HIT");
                }
            }
        });
        inventaire.items.addListener((ListChangeListener<Item>) c -> {
            while (c.next()) {
                for (Item item : c.getAddedSubList()) {
                    System.out.println("Add Item");
                }
                for (Item item : c.getRemoved()) {
                    System.out.println("Del Item");
                }
            }
        });
        joueur.verifGravite();
        initAnimation();
        gameLoop.play();
    }

    private VueJoueur getVueJoueur() {
        return vueJoueur;
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




    @FXML
    public void update (KeyEvent event) {
    }


}
