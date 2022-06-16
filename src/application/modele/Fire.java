package application.modele;

import javafx.beans.property.IntegerProperty;

public class Fire {

    private static String url = "application/images/fire.png";
    private static int count=0;
    private int id;
    private IntegerProperty x,y;
    private int width=32, height=32;
    private Environnement env;
    private Necromancer necromancer;
    private double degat;
    private boolean right = false, left = false;
    private boolean touch;

    public Fire(Necromancer necromancer, double degat, Environnement env) {
        this.id = count++;
        this.necromancer = necromancer;
        this.env = env;
        this.degat = degat;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public void setX(int x) { this.x.setValue(x); }

    public void setY(int y) { this.y.setValue(y); }

    public void setRight(boolean right) { this.right = right; }

    public void setLeft(boolean left) { this.left = left; }

    public int getX() { return x.getValue(); }

    public int getY() { return y.getValue(); }

    public IntegerProperty getXProperty() { return x; }

    public IntegerProperty getYProperty() { return y; }

    public int getId() { return id; }

    public static String getUrl() { return url; }

    public Entite checkZone() {
        if ((this.getX() - 12 <= env.getJoueur().getX()) && (env.getJoueur().getX() <= this.getX() + 12)  && (env.getJoueur().getY() == this.getY())) {
            return env.getJoueur();
        }
        return null;
    }

    public void seDeplace() {
        if (right) {
            this.setX(this.getX() + 6);
        }

        if (left) {
            this.setX(this.getX() - 6);
        }
    }

    public void agit() {
        x = necromancer.getXProperty();
        y = necromancer.getYProperty();
        Entite e = this.checkZone();
        if (e instanceof Joueur) {
            env.getFires().remove(this);
            env.getJoueur().decrementerPv(degat);
            if (env.getJoueur().getPv() == 0) {
                env.getJoueur().meurt();
            }
        } else if (e == null) {
            seDeplace();
        }
    }
}
