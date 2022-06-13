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
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
    private ProgressBar progressbar;

    @FXML
    private ImageView image1, image2, image3, image4;
    private ArrayList<ImageView> images = new ArrayList<ImageView>();

    @FXML
    private Label label1, label2, label3, label4;
    private ArrayList<Label> labels = new ArrayList<Label>();

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        images.add(image1); images.add(image2); images.add(image3); images.add(image4);
        labels.add(label1); labels.add(label2); labels.add(label3); labels.add(label4);
        inventaire = new Inventaire();
        env = new Environnement(inventaire);
        vueMap = new VueMap(env, tilepane);
        vueJoueur = new VueJoueur(env.getJoueur(), env, pane, progressbar);
        vuePnj = new VuePnj(env.getEntites(), /*env.getFires(),*/ pane);
        vueInventaire = new VueInventaire(inventaire, pane, title, background, images, labels);

        update();

        env.getEntites().addListener((ListChangeListener<Entite>) c -> {
            while (c.next()) {
                for (Entite e : c.getAddedSubList()) {
                    vuePnj.clearImages();
                    vuePnj.initializeEntite();
                }
                for (Entite e : c.getRemoved()) {
                    vuePnj.clearImages();
                    vuePnj.initializeEntite();            
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

        env.getJoueur().verifGravite();
        for (Entite pnj : env.getEntites()) {
            pnj.verifGravite();
        }
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
                    if (env.getJoueur().isCanJump()) {
                        env.getJoueur().verifGravite();

                    }
                    if (env.getJoueur().isCiel() == true) {
                        env.getJoueur().setY(env.getJoueur().getY()+1);
                        env.getJoueur().verifGravite();
                    }
                    
                    for (Entite pnj : env.getEntites()) {
                        if (pnj.isCiel() == true) {
                            pnj.setY(pnj.getY()+1);
                            pnj.verifGravite();
                        }
                    }

                    if (temps%15 == 0 ) {
                        env.getJoueur().seDeplace();
                    }

                    
                    if (temps%30 == 0) {
                        env.oneRound();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    public void update () {

       
        borderpane.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.LEFT) || e.getCode().equals(KeyCode.Q)) {
                env.getJoueur().setLeft(true);
                vueJoueur.animationMouvement("LEFT");
            } else if (e.getCode().equals(KeyCode.RIGHT) || e.getCode().equals(KeyCode.D)) {
                env.getJoueur().setRight(true);
                vueJoueur.animationMouvement("RIGHT");
            } else if (e.getCode().equals(KeyCode.E)) {
                if (vueInventaire.isOpen()) {
                    vueInventaire.close();
                } else {
                    vueInventaire.open();
                }
            } else if (e.getCode().equals(KeyCode.H)) {
                try {
                    inventaire.addItem(new Epee("Epee"));
                } catch (Exception exception) {
                    System.out.println("Limite d'Item atteinte !");
                }
            }
            /*
            switch(e.getCode()) {
                case LEFT:
                    env.getJoueur().setLeft(true);
                    vueJoueur.animationMouvement("LEFT");
                    break;
                case Q:
                    env.getJoueur().setLeft(true);
                    vueJoueur.animationMouvement("LEFT");
                    break;
                case RIGHT:
                    env.getJoueur().setRight(true);
                    vueJoueur.animationMouvement("RIGHT");
                    break;
                case D:
                    env.getJoueur().setRight(true);
                    vueJoueur.animationMouvement("RIGHT");
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
                        inventaire.addItem(new Epee("Epee"));
                    } catch (Exception exception) {
                        System.out.println("Limite d'Item atteinte !");
                    }
                    break;
                default:
                    break;
            }*/
        });

        borderpane.setOnKeyReleased(e -> {
            if (e.getCode().equals(KeyCode.LEFT) || e.getCode().equals(KeyCode.Q)) {
                env.getJoueur().setLeft(false);
            } else if (e.getCode().equals(KeyCode.RIGHT) || e.getCode().equals(KeyCode.D)) {
                env.getJoueur().setRight(false);
            } else if (e.getCode().equals(KeyCode.UP) || e.getCode().equals(KeyCode.SPACE) || e.getCode().equals(KeyCode.Z)) {
                if (!env.getJoueur().isUp() && env.getJoueur().isCanJump()) {
                    env.getJoueur().setUp(true);
                    vueJoueur.animationMouvement("UP");
                }
            }
            /*
            switch(e.getCode()) {
                case LEFT:
                    env.getJoueur().setLeft(false);
                    break;
                case Q:
                    env.getJoueur().setLeft(false);
                    break;
                case RIGHT:
                    env.getJoueur().setRight(false);
                    break;
                case D:
                    env.getJoueur().setRight(false);
                    break;
                case UP:
                    if (!env.getJoueur().isUp() && env.getJoueur().isCanJump()) {
                        env.getJoueur().setUp(true);
                        vueJoueur.animationMouvement("UP");
                    }
                    break;
                case SPACE:
                    if (!env.getJoueur().isUp() && env.getJoueur().isCanJump()) {
                        env.getJoueur().setUp(true);
                        vueJoueur.animationMouvement("UP");
                    }
                    break;
                default:
                    break;
            }*/
        });

        borderpane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    if (vueInventaire.isOpen()) {
                        inventaire.selectItem((int)event.getX(), (int)event.getY(), false);
                    } else if (inventaire.getCurrentItem() instanceof Epee) {
                        vueJoueur.animationMouvement("HIT");
                        env.getJoueur().agit();
                    }
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    if (vueInventaire.isOpen()) {
                        inventaire.selectItem((int)event.getX(), (int)event.getY(), true);
                        inventaire.removeItem();
                    }
                }
            }
        });
    }


}
