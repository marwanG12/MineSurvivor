package application.controleur;

import application.Main;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.VueJoueur;
import application.modele.VueMap;
import javafx.animation.KeyValue;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;



public class Controleur implements Initializable {
    private Environnement env;
    private Entite joueur;
    private VueMap terrain;
    private VueJoueur perso;
    private int r=1, l=1, h=1;
    private int fps = 0;

    

    @FXML
    private TilePane tilepane;

    @FXML
    private BorderPane borderpane;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        env = new Environnement();
        joueur = new Joueur(208, 468, env);
        terrain = new VueMap(env);
        perso = new VueJoueur(joueur);        


        terrain.afficheMap(tilepane);
        perso.affichePerso(borderpane);

        borderpane.setOnKeyPressed(ke -> {
            if(ke.getCode() == KeyCode.RIGHT || ke.getCode() == KeyCode.D) {
                l = 1;
                AnimationTimer timer = new AnimationTimer() {
                    private long lastUpdate = 0;
                    @Override
                    public void handle(long now) {
                        if (now - lastUpdate >= 500_000_00) { // delay de 1000 ms
                            if (r == 1) {
                                perso.updatePerso("RIGHT");
                                r++;
                            } else {
                                perso.updatePerso("RIGHT2");
                                r--;
                            }         
                            joueur.setX(joueur.getX()+8);
                            lastUpdate = now;
                            fps++;
                        }
                        if (fps == 3) {
                            perso.updatePerso("STATIC");
                            stop();
                            fps = 0;
                        }                    
                    }
                };
                timer.start();
            } else if (ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.Q) {
                r = 1;
                AnimationTimer timer = new AnimationTimer() {
                    private long lastUpdate = 0;
                    @Override
                    public void handle(long now) {
                        if (now - lastUpdate >= 500_000_00) { // delay de 1000 ms
                            if (l == 1) {
                                perso.updatePerso("LEFT");
                                l++;
                            } else {
                                perso.updatePerso("LEFT2");
                                l--;
                            }         
                            joueur.setX(joueur.getX()-8);
                            lastUpdate = now;
                            fps++;
                        }
                        if (fps == 3) {
                            perso.updatePerso("STATIC");
                            stop();
                            fps = 0;
                        }                    
                    }
                };
                timer.start();
            } else if (ke.getCode() == KeyCode.UP || ke.getCode() == KeyCode.Z) {
                joueur.setY(joueur.getY()-64);
                perso.updatePerso("UP");
            }
        });

        borderpane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    AnimationTimer timer = new AnimationTimer() {
                        private long lastUpdate = 0;
                        @Override
                        public void handle(long now) {
                            if (now - lastUpdate >= 500_000_00) {
                                if (h == 1) {
                                    perso.updatePerso("HIT");
                                    h = 2;
                                } else if (h == 2) {
                                    perso.updatePerso("HIT2");
                                    h = 3;
                                } else if (h == 3) {
                                    perso.updatePerso("HIT3");
                                    h = 4;
                                } else {
                                    perso.updatePerso("HIT4");
                                    h = 1;
                                }
                                lastUpdate = now;
                                fps++;
                            }
                            if (fps == 5) {
                                perso.updatePerso("STATIC");
                                stop();
                                fps = 0;
                                h = 1;
                            }                    
                        }
                    };
                    timer.start();                
                }
            }
        });
    }

    @FXML
    public void update (KeyEvent event) {
    }

    @FXML
    public void click (MouseEvent event) {
    }


}
