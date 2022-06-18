package application.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Entite {

    private String nom;
    private IntegerProperty x,y;
    private DoubleProperty pv;
    private BooleanProperty pos;
    private int width=32, height=32;
    protected Environnement env;
    public static int count=0;
    private int id;
    private boolean dead = false;

    private boolean right = false, left = false, up = false;
    private boolean posL = false, posR = true;
    private boolean terreR = false, terreL = false, terreU = false;
    private boolean ciel = false;
    private boolean canJump = true;
    private boolean found = false;
    private String limitemap;
    private String url;

    public Entite(int pv, int x, int y, Environnement env, String nom, String url) {
        this.pv = new SimpleDoubleProperty(pv);
        this.nom = nom;
        this.x = new SimpleIntegerProperty(x);
        this.pos = new SimpleBooleanProperty(true);
        this.y = new SimpleIntegerProperty(y);
        this.env = env;
        this.url = url;
        this.id= count++;
    }


    
    public BooleanProperty getPos() { return pos; }

    public double getPv() { return pv.getValue(); }

    public DoubleProperty getPvProperty() { return pv; }

    public void decrementerPv(double n) { pv.setValue(pv.getValue()-n); }

    public boolean isDead() { return dead; }

    public int getX() { return x.getValue(); }

    public int getY() { return y.getValue(); }

    public IntegerProperty getXProperty() { return x; }

    public IntegerProperty getYProperty() { return y; }

    public void setX(int n) { x.setValue(n); }

    public void setY(int n) { y.setValue(n); }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public int getId() { return id; }

    public String getNom() { return nom; }

    public boolean isCiel() { return ciel; }

    public boolean isCanJump() { return canJump; }

    public boolean isRight() { return right; }

    public void setRight(boolean right) { this.right = right; }

    public boolean isLeft() { return left; }

    public void setLeft(boolean left) { this.left = left; }

    public boolean isUp() { return up; }

    public void setUp(boolean up) { this.up = up; }

    public String getUrl() { return url; }


    public void seDeplace() {
        colision();
        limiteMap();
        if (right) {
            if (!terreR && limitemap != "right") {
                this.setX(this.getX() + 6);
            }
            posR = true;
            posL = false;
        }   

        if (left) {
            if (!terreL  && limitemap != "left") {
                this.setX(this.getX() - 6);
            }
            posL = true;
            posR = false;
        }

        if (up) {
            if (limitemap != "top") {
                if (!terreU) {
                    this.setY(this.getY() - 10);
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
    }

    public void verifGravite() {
        int minX = (getX())/32;
        int maxX = (getX() + 24)/32;

        int posY = getY()/32;

        int tile = (posY * 45) + minX + 45;
        int tileSuivante = (posY * 45) + maxX + 45; //Si le personnage se trouve entre les 2 tuiles

        if (left) { //Selon la direction du joueur sa largeur change car le joueur ne prend pas toute l'image
            minX = (getX() + 8)/32;
            maxX = (getX() + 32)/32;

            tile = (posY * 45) + minX + 45;
            tileSuivante = (posY * 45) + maxX + 45;
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
        int tileR = (posY * 45) + posX + 1; //Tuile à droite de l'entite
        int tileRB = (maxY * 45) + posX + 1; //Tuile en bas à droite de l'entite
        int tileL = (posY * 45) + posX; //Tuile à gauche de l'entite
        int tileLB = (maxY * 45) + posX; //Tuile en bas à gauche de l'entite
        int tileU = (posY * 45) + posX - 45; //Tuile en haut de l'entite
        int tileUB =  (posY * 45) + maxX - 45;

        int yTileR = (tileR/45) * 32;
        int yTileL = (tileL/45) * 32;


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
            tileU = (posY * 45) + posX - 45;
            tileUB = (posY * 45) + maxX - 45;
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
        if (alea < 0.5) {
            right = false;
            left = true;
            pos.setValue(false);
        } else {
            left = false;
            right = true;
            pos.setValue(true);
        } 
        /* 
        if (reussitProba(20)) {
            up = true;
        }*/
    }

    public void seDeplaceAlea(){
        if (!found) {
            if (reussitProba(5)) {
                tirerDirection();
            }
            seDeplace();
        }
        found = false;
    }

    public void agit() {
    }

    public Entite checkZone(int distance) {
        if (this instanceof Joueur) {
            for (Entite e : env.getEntites()) {  
                if ((e.getX() - this.getX() <= distance) && (e.getX() - this.getX() >= 0) && posR && (e.getY() == this.getY())) {
                    return e;
                } else if ((e.getX() - (this.getX()+this.width) >= (-distance)) && (e.getX() - (this.getX()+this.width) <= 0) && posL && (e.getY() == this.getY())) {
                    return e;
                }
            }
        } else if (this instanceof Necromancer) {
            if ((env.getJoueur().getX() - this.getX() <= distance) && (env.getJoueur().getX() - this.getX() >= 0) && (env.getJoueur().getY() == this.getY())) {
                this.found = true;
                right = true;
                left = false;
                pos.setValue(true);
                return env.getJoueur();
            } else if ((env.getJoueur().getX() - (this.getX()+this.width) >= (-distance)) && (env.getJoueur().getX() - (this.getX()+this.width) <= 0) && (env.getJoueur().getY() == this.getY())) {
                this.found = true;
                right = false;
                left = true;
                pos.setValue(false);
                return env.getJoueur();
            }
        } else {
            if ((this.getX() - distance <= env.getJoueur().getX()) && (env.getJoueur().getX() <= this.getX() + distance)  && (env.getJoueur().getY() == this.getY())) {
                return env.getJoueur();
            }
        }
        return null;
    }

    public boolean isPosL() {
        return posL;
    }


    public boolean isPosR() {
        return posR;
    }


    public void setPosL(boolean posL) {
        this.posL = posL;
    }


    public void setPosR(boolean posR) {
        this.posR = posR;
    }


    public void meurt() {
        this.dead = true;
    }

    public DoubleProperty pvProperty() { return pv; }

    public void setPv(double pv) { this.pv.set(pv); }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", id=" + id + ", nom=" + nom + ", pv=" + pv;
    }

}
