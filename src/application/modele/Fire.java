package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Fire {

    private static int count = 0;
    private int loop = 0; 
    private String url = "application/images/fire.png";
    private int id;
    private IntegerProperty x, y;
    private int width=8, height=8;
    private Environnement env;
    private Necromancer necromancer;
    private double degat;
    private boolean right = false, left = false;
    private boolean active = true;

    public Fire(Necromancer necromancer, double degat, Environnement env) {
        this.id = count++;
        this.necromancer = necromancer;
        this.env = env;
        this.degat = degat;
        this.x = new SimpleIntegerProperty(necromancer.getX());
        this.y = new SimpleIntegerProperty(necromancer.getY()+12);
    }

    public int getWidth() { return width; }

    public double getDegat() { return degat; }

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

    public String getUrl() { return url; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public void setId(int id) { this.id = id; }

    public Entite checkZone() {
        if ((this.getX() - width <= env.getJoueur().getX() + 20) && (env.getJoueur().getX() <= this.getX() + width)  && (env.getJoueur().getY() == this.getY()-12)) {
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
        if (active) {
            Entite e = this.checkZone();
            if (e instanceof Joueur) {
                env.getJoueur().decrementerPv(getDegat());
                if (env.getJoueur().getPv() == 0) {
                    env.getJoueur().meurt();
                }
                count--;
                necromancer.removeFire();
            } else {
                loop++;
                seDeplace();
                if (loop == 30) {
                    count--;
                    loop = 0;
                    necromancer.removeFire();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Fire{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
