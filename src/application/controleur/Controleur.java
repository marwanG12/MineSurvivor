package application.controleur;

import application.modele.*;
import application.vue.VueInventaire;
import application.vue.VueJoueur;
import application.vue.VueMap;
import application.vue.VuePnj;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controleur implements Initializable {
    private Environnement env;
    private Joueur joueur;
    private Inventaire inventaire;
    private VueMap vueMap;
    private VueJoueur vueJoueur;
    private VueInventaire vueInventaire;
    private VuePnj vuePnj;
    private Timeline gameLoop;
    private int temps;

    @FXML
    private TilePane tilepane;

    @FXML
    private Label title;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Pane pane;

    @FXML 
    private ImageView background;

    @FXML
    private ImageView image1, image2, image3, image4;
    private ArrayList<ImageView> images = new ArrayList<ImageView>();

    @FXML
    private Label label1, label2, label3, label4;
    private ArrayList<Label> labels = new ArrayList<Label>();

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        env = new Environnement();
        inventaire = new Inventaire();
        joueur = new Joueur(208, 0, env, inventaire);
        vueMap = new VueMap(env);
        vueJoueur = new VueJoueur(joueur, env);
        inventaire = new Inventaire();
        inventaire.initialize();
        images.add(image1); images.add(image2); images.add(image3); images.add(image4);
        labels.add(label1); labels.add(label2); labels.add(label3); labels.add(label4);
        vueInventaire = new VueInventaire(inventaire, pane, title, background, images, labels);
        vuePnj = new VuePnj(env.getEntites(), pane);
        vueMap.afficheMap(tilepane);
        getVueJoueur().affichePerso(pane);


        borderpane.setOnKeyPressed(e -> {
            switch(e.getCode()) {
                case LEFT:
                    joueur.setLeft(true);
                    vueJoueur.animationMouvement("LEFT");
                    break;
                case E: 
                    if (vueInventaire.isOpen()) {
                        vueInventaire.close();
                    } else {
                        vueInventaire.open();
                    }
                    break;
                case H:
                    try {
                        inventaire.addItem(new Epee("Epee Diams", 1));
                    } catch (Exception exception) {
                        System.out.println("Limite d'Item atteinte !");
                    }
                    break;
                case RIGHT:
                    joueur.setRight(true);
                    vueJoueur.animationMouvement("RIGHT");
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
                    if (vueInventaire.isOpen()) {
                        inventaire.selectItem((int)event.getX(), (int)event.getY());
                    } else {
                        vueJoueur.animationMouvement("HIT");
                    }
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    if (vueInventaire.isOpen()) {
                        inventaire.selectItem((int)event.getX(), (int)event.getY());
                        inventaire.removeItem();
                    }
                }
            }
        });
        inventaire.getItems().addListener((ListChangeListener<Item>) c -> {
            while (c.next()) {
                for (Item item : c.getAddedSubList()) {
                    if (vueInventaire.isOpen()) {
                        vueInventaire.close();
                    } else {
                        vueInventaire.refresh();
                    }
                }
                for (Item item : c.getRemoved()) {
                    if (vueInventaire.isOpen()) {
                        vueInventaire.close();
                    } else {
                        vueInventaire.refresh();
                    }
                }
            }
        });

        inventaire.getRessources().addListener((ListChangeListener<Ressource>) c -> {
            while (c.next()) {
                for (Ressource item : c.getAddedSubList()) {
                    if (vueInventaire.isOpen()) {
                        vueInventaire.close();
                        vueInventaire.open();
                    } else {
                        vueInventaire.refresh();
                    }
                }
                for (Ressource item : c.getRemoved()) {
                    if (vueInventaire.isOpen()) {
                        vueInventaire.close();
                        vueInventaire.open();
                    } else {
                        vueInventaire.refresh();
                    }
                }
            }
        });

        joueur.verifGravite();
        for (Entite pnj : env.getEntites()) {
            pnj.verifGravite();
        }
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
                    if (joueur.isCanJump()) {
                        joueur.verifGravite();

                    }
                    if (joueur.isCiel() == true) {
                        joueur.setY(joueur.getY()+1);
                        joueur.verifGravite();
                    }
                
                    for (Entite pnj : env.getEntites()) {
                        if (pnj.isCiel() == true) {
                            pnj.setY(joueur.getY()+1);
                            pnj.verifGravite();
                        }
                    }

                    if (temps%15 == 0 ) {
                        joueur.seDeplace();
                    }

                    
                    if (temps%30 == 0) {
                        for (Entite pnj : env.getEntites()) {
                            pnj.agit();
                            pnj.verifGravite();
                        }
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
