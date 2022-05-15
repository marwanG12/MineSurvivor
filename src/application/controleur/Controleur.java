package application.controleur;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.VueJoueur;
import application.modele.VueMap;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;


import java.net.URL;
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
        perso = new VueJoueur(joueur, env);        


        terrain.afficheMap(tilepane);
        perso.affichePerso(borderpane);


        borderpane.setOnKeyPressed(ke -> {
            if(ke.getCode() == KeyCode.RIGHT || ke.getCode() == KeyCode.D) {
                if (fps == 0) {
                    AnimationTimer timer = new AnimationTimer() {
                        private long lastUpdate = 0;
                        @Override
                        public void handle(long now) {
                            if (now - lastUpdate >= 750_000_00) { // delay
                                if (r == 1) {
                                    perso.updatePerso("RIGHT");
                                    r++;
                                } else {
                                    perso.updatePerso("RUN" + r);
                                    if (r==4) {
                                        r=1;
                                    } else {
                                        r++;
                                    }
                                }
                                joueur.setX(joueur.getX()+8);
                                lastUpdate = now;
                                fps++;
                            }
                            if (fps == 5) {
                                perso.updatePerso("STATIC");
                                stop();
                                fps = 0;
                                r = 1;   
                            }                    
                        }
                    };
                    timer.start();
                }
            } else if (ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.Q) {
                if (fps == 0) {
                    AnimationTimer timer = new AnimationTimer() {
                        private long lastUpdate = 0;
                        @Override
                        public void handle(long now) {
                            if (now - lastUpdate >= 750_000_00) { // delay
                                if (l == 1) {
                                    perso.updatePerso("LEFT");
                                    l++;
                                } else {
                                    perso.updatePerso("RUN" + l);
                                    if (l==4) {
                                        l=1;
                                    } else {
                                        l++;
                                    }
                                }
                                joueur.setX(joueur.getX()-8);
                                lastUpdate = now;
                                fps++;
                            }
                            if (fps == 5) {
                                perso.updatePerso("STATIC");
                                stop();
                                fps = 0;
                                l = 1;   
                            }                   
                        }
                    };
                    timer.start();
                }


            } else if (ke.getCode() == KeyCode.UP || ke.getCode() == KeyCode.Z) {
                    AnimationTimer timer = new AnimationTimer() {
                        private long lastUpdate = 0;
                        @Override
                        public void handle(long now) {
                            if (now - lastUpdate >= 1000_000_00) { // delay de 1000 ms
                                if (l == 1) {
                                    perso.updatePerso("UP");
                                    l++;
                                } else {
                                    perso.updatePerso("UP2");
                                    l--;
                                }      
                                joueur.setY(joueur.getY()-16);   
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
                
            }
        });

        borderpane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    if (fps == 0) {
                        AnimationTimer timer = new AnimationTimer() {
                            private long lastUpdate = 0;
                            @Override
                            public void handle(long now) {
                                if (now - lastUpdate >= 500_000_00) { // delay
                                    if (h == 1) {
                                        perso.updatePerso("HIT");
                                        h++;
                                    } else {
                                        perso.updatePerso("HIT" + h);
                                        if (h==4) {
                                            h=1;
                                        } else {
                                            h++;
                                        }
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
            }
        });
    }

    public void attendre(int millis) {
        try {
            Thread.sleep(millis);  
        }
        catch (InterruptedException e) {
            System.out.println("thread interrupted");
        }
    }

    @FXML
    public void update (KeyEvent event) {
    }


}
