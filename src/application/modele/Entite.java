package application.modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Entite {
    private String nom;
    private IntegerProperty x,y;
    protected Environnement env;
    public static int compteur=0;
    private String id;
    private boolean ciel = false;
    private boolean canJump = true;
    private int time;
    private Timeline tl;


    public Entite(int x, int y, Environnement env, String nom) {
        this.nom = nom;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env=env;
        this.id="A"+compteur;
        compteur++;
    }


    public Timeline getTimeline() {
        return tl;
    }



    public  int getX() {
        return x.getValue();
    }

    public IntegerProperty getXProperty() {
        return x;
    }

    public  void setX(int n){
        x.setValue(n);
    }

    public  int getY() {
        return y.getValue();
    }

    public IntegerProperty getYProperty() {
        return y;
    }
    public  void setY(int n){
        y.setValue(n);
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void verifGravite() {
        if (env.getCodeTiles((getX()/32) + (getY()/29*30)) == 00) {
            ciel = true;
            canJump = false;
        } else {
            ciel = false;
            canJump = true;
        }
    }

    public void verifColisionD() {
        if (env.getCodeTiles((getY()/44) + (getX()/45*30) - 1) == 00) {
            ciel = true;
            canJump = false;
        } else {
            ciel = false;
            canJump = true;
        }
    }

    public void verifColisionG() {
        if (env.getCodeTiles((getY()/44) + (getX()/45*30) + 1) == 00) {
            ciel = true;
            canJump = false;
        } else {
            ciel = false;
            canJump = true;
        }
    }


    public void graviteAnimation() {
        tl = new Timeline();
        time = 0;
        tl.setCycleCount(tl.INDEFINITE);
        KeyFrame keyframe =new KeyFrame (
            Duration.seconds(0.007),
            (ev -> {
                if (ciel == true) {
                    setY(getY()+1);
                    verifGravite();
                }
                time++;
            })
        );
        tl.getKeyFrames().add(keyframe);
    }



    public boolean isCanJump() {
        return canJump;
    }




    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", id=" + id ;
    }

}
