package application.modele;

public class Potion extends Item{

    private double pv;
    private static String url = "application/images/Potion.png";

    public Potion(String nom) {
        super(nom, url);
        this.pv=4;
    }

    public void addPv(Joueur joueur) {
        if (joueur.getPv() > 0) {
            joueur.setPv(joueur.getPv() + pv);
        }
    }

    public double getPv(){
        return pv;
    }

}
