package application.modele;
import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Entite {

    private String nom;
    private IntegerProperty x,y;
    private int width=32, height=32;
    protected Environnement env;
    public static int compteur=0;
    private String id;

    private boolean right = false, left = false, up = false;
    private boolean terreR = false, terreL = false, terreU = false;
    private boolean ciel = false;
    private boolean canJump = true;
    private int count;
    private String limitemap;
    private boolean limitemap2;
    private String url;

    public Entite(int x, int y, Environnement env, String nom, String url) {
        this.nom = nom;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env = env;
        this.url = url;
        this.id="A"+compteur;
        compteur++;
    }


    public int getX() { return x.getValue(); }

    public int getY() { return y.getValue(); }

    public IntegerProperty getXProperty() { return x; }

    public IntegerProperty getYProperty() { return y; }

    public void setX(int n) { x.setValue(n); }

    public void setY(int n) { y.setValue(n); }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public String getId() { return id; }

    public String getNom() { return nom; }

    public boolean isCiel() { return ciel; }

    public boolean isCanJump() { return canJump; }

    public boolean isRight() { return right; }

    public void setRight(boolean right) { this.right = right; }

    public boolean isLeft() { return left; }

    public void setLeft(boolean left) { this.left = left; }

    public boolean isUp() { return up; }

    public void setUp(boolean up) { this.up = up; }

    public void setLimitemap(String limitemap) { this.limitemap = limitemap; }
    public void setLimitemap(boolean limitemap) { this.limitemap2 = limitemap; }

    public String isLimitemap() { return limitemap; }
    public boolean isLimitemap2() { return limitemap2; }
    public String getUrl() { return url; }


    public void seDeplace() {
        colision();
        limiteMap();
        if (right) {
            if (!terreR && limitemap != "right") {
                this.setX(this.getX() + 6);
            }
        }

        if (left) {
            if (!terreL  && limitemap != "left") {
                this.setX(this.getX() - 6);
            }
        }

        if (up) {
            if (!terreU && limitemap != "top") {
                this.setY(this.getY() - 18);
                count++;
                if (canJump) {
                    canJump = false;
                }
            }
            if (count == 4) {
                up = false;
                count = 0;
            }
        }
    }

    public void verifGravite() {
        int minX = (getX())/32;
        int maxX = (getX() + 24)/32;

        int posY = getY()/32;

        int tile = (posY * 30) + minX + 30;
        int tileSuivante = (posY * 30) + maxX + 30; //Si le personnage se trouve entre les 2 tuiles

        if (left) { //Selon la direction du joueur sa largeur change car le joueur ne prend pas toute l'image
            minX = (getX() + 8)/32;
            maxX = (getX() + 32)/32;

            tile = (posY * 30) + minX + 30;
            tileSuivante = (posY * 30) + maxX + 30;
        }

        if (env.getTile(tile) == 00 && env.getTile(tileSuivante) == 00) {
            ciel = true;
            canJump = false;
        } else {
            ciel = false;
            canJump = true;
        }
    }

    public void colision() {
        int posX = getX()/32;
        int maxX = (getX() + 24)/32;
        int posY = getY()/32;
        int maxY = (getY() + 32)/32;
        int tileR = (posY * 30) + posX + 1; //Tuile à droite de l'entite
        int tileRB = (maxY * 30) + posX + 1; //Tuile en bas à droite de l'entite
        int tileL = (posY * 30) + posX; //Tuile à gauche de l'entite
        int tileLB = (maxY * 30) + posX; //Tuile en bas à gauche de l'entite
        int tileU = (posY * 30) + posX - 30; //Tuile en haut de l'entite
        int tileUB =  (posY * 30) + maxX - 30;

        int yTileR = (tileR/30) * 32;
        int yTileL = (tileL/30) * 32;


        if (env.getTile(tileR) != 0 || (yTileR < getY()  && env.getTile(tileRB) != 0 && env.getTile(tileR) == 0)) {
            terreR = true;
        } else {
            terreR = false;
        }

        if (env.getTile(tileL) != 0 || (yTileL < getY() && env.getTile(tileLB) != 0 && env.getTile(tileL) == 0)) {
            terreL = true;
        } else {
            terreL = false;
        }

        if (left) {
            posX = (getX() + 8)/32;
            maxX = (getX() + 32)/32;
            tileU = (posY * 30) + posX - 30;
            tileUB = (posY * 30) + maxX - 30;
        }

        if (env.getTile(tileU) != 0 || env.getTile(tileUB) != 0) {
            terreU = true;
        } else {
            terreU = false;
        }
    }


    public void limiteMap() {
        limitemap = "none";
        if(getX() + width >= env.getWidth()) {
            limitemap = "right";
        } else if (getX() < 0) {
            limitemap = "left";
        } else if (getY() < 0) {
            limitemap = "top";
        } else if (getY() + height > env.getHeight()) {
            limitemap = "bottom";
        }
    }

    public static boolean reussitProba(double pourcent){
		double x= Math.random();
		double pp=pourcent/100;
		return (x<=pp);
	}

    public void tirerDirection(){
        double alea = Math.random();
        if (alea < 0.45) {
            right = false;
            left = true;
        } else if (alea > 0.45) {
            left = false;
            right = true;
        } else {
            up = true;
        }
    }

    public void seDeplaceAlea(){
		if(reussitProba(20)){
			tirerDirection();
		}
        seDeplace();
	}

    public void agit() {
        seDeplaceAlea();
    }




    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", id=" + id ;
    }

}
