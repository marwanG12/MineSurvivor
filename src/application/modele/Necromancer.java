package application.modele;

public class Necromancer extends Entite {

    private static String url = "application/images/Necromancier.png";
    private double pv = 5;
    
    public Necromancer(int x, int y, Environnement env, String nom) {
        super(x, y, env, nom, url);
    }

    public double getPv() {
        return pv;
    }

    public String getUrl() {
        return url;
    }

}
