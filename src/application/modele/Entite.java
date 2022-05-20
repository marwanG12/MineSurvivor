package application.modele;


import javax.swing.Spring;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Entite {
    private String nom;
    private IntegerProperty x,y;
    private int width=32, height=32;
    protected Environnement env;
    public static int compteur=0;
    private String id;
    private boolean right = true; // direction du sprite à droite
    private boolean ciel = false;
    private boolean terreR = false;
    private boolean terreL = false;
    private boolean canJump = true;
    private String limitemap;

    public Entite(int x, int y, Environnement env, String nom) {
        this.nom = nom;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env=env;
        this.id="A"+compteur;
        compteur++;
    }


    public  int getX() { return x.getValue(); }

    public  int getY() { return y.getValue(); }

    public IntegerProperty getXProperty() { return x; }

    public IntegerProperty getYProperty() { return y; }

    public void setX(int n){ x.setValue(n); }

    public void setY(int n){ y.setValue(n); }

    public int getWidth() { return width; }

    public int getHeight() { return height; }
    
    public String getId() { return id; }

    public String getNom() { return nom; }

    public boolean isCiel() { return ciel; }

    public boolean isCanJump() { return canJump; }


    public void seDeplace(String direction) {
        colision();
        switch (direction) {
            case "RIGHT" :
                right = true;
                if (terreR == false) {
                    this.setX(this.getX() + 8);
                }
                break;
            case "RIGHT-UP" :
                right = true;
                this.setY(this.getY() - 32);
                this.setX(this.getX() + 32);
                break;
            case "LEFT" :
                right = false;
                if (terreL == false) {
                    this.setX(this.getX() - 8);
                }
                break;
            case "LEFT-UP" :
                right = false;
                this.setY(this.getY() - 32);
                this.setX(this.getX() - 32);
                break;
            case "UP" :
                this.setY(this.getY() - 16);
                break;
            default :
                break;
         }
    }

    public void verifGravite() {
        int minX = getX();
        int maxX = getX() + 24;

        int spriteX = minX/32;
        int sprite2X = maxX/32;

        int spriteY = getY()/32;

        int tile = (spriteY * 30) + spriteX + 30;
        int tileSuivante = (spriteY * 30) + sprite2X + 30;

        if (right) {
            if (env.getTile(tile) == 00 && env.getTile(tileSuivante) == 00) {
                ciel = true;
                canJump = false;
            } else {
                ciel = false;
                canJump = true;
            }
        } else {
            minX = getX() + 8;
            maxX = getX() + 32;
            spriteX = minX/32;
            sprite2X = maxX/32;

            tile = (spriteY * 30) + spriteX + 30;
            tileSuivante = (spriteY * 30) + sprite2X + 30;

            if (env.getTile(tile) == 00 && env.getTile(tileSuivante) == 00) {
                ciel = true;
                canJump = false;
            } else {
                ciel = false;
                canJump = true;
            }
        }

    }

    public void colision() {
        int spriteX = getX()/32;
        int spriteY = getY()/32;
        int tileR = (spriteY * 30) + spriteX + 1;
        int tileL = (spriteY * 30) + spriteX;

        if (env.getTile(tileR) != 0) {
            terreR = true;
        } else {
            terreR = false;
        }

        if (env.getTile(tileL) != 0) {
            terreL = true;
        } else {
            terreL = false;
        }
    }

    


    public void setLimitemap(String limitemap) {
        this.limitemap = limitemap;
    }
 
    public String isLimitemap() {
        return limitemap;
    }

    public void limiteMap() {
        if(getX() + 32 >= 30*32) {
            limitemap = "RIGHT";
        } else if (getX() - 32 < 0) {
            limitemap = "LEFT";
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", id=" + id ;
    }

}
