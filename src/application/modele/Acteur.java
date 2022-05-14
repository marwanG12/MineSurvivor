package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Acteur {
    private String nom;
    private IntegerProperty x,y;
    protected Environnement env;
    public static int compteur=0;
    private String id;

    public Acteur(int x, int y, Environnement env, String nom) {
        this.nom = nom;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env=env;
        this.id="A"+compteur;
        compteur++;
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

    public void seDeplace(int direction){

    }


    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", id=" + id ;
    }

}