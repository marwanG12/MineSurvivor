package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Fire {

    private String url = "application/images/fire.png";
    private static int count=0;
    private int id;
    private IntegerProperty x, y;
    private int width=32, height=32;
    private Environnement env;
    private Necromancer necromancer;
    private double degat;
    private boolean right = false, left = false;
    private boolean active = false;
    private static int loop = 0;

    public Fire(Necromancer necromancer, double degat, Environnement env) {
        this.id = count++;
        this.necromancer = necromancer;
        this.env = env;
        this.degat = degat;
        this.x = new SimpleIntegerProperty(necromancer.getX());
        this.y = new SimpleIntegerProperty(necromancer.getY());
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

    public String getUrl() { return url; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public Entite checkZone() {
        if ((this.getX() - 5 <= env.getJoueur().getX()) && (env.getJoueur().getX() <= this.getX() + width)  && (env.getJoueur().getY() == this.getY())) {
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
                env.getJoueur().decrementerPv(degat);
                if (env.getJoueur().getPv() == 0) {
                    env.getJoueur().meurt();
                }
                active = false;
                count--;
                env.getFires().remove(this);
            } else if (e == null) {
                loop++;
                seDeplace();
                System.out.println("x" + x);
                System.out.println("y" + y);
                if (loop != 10) {
                    active = false;
                    loop = 0;
                    count--;
                    env.getFires().remove(this);
                }
            }
        }
    }

    public static int getLoop() {
        return loop;
    }

    
}
