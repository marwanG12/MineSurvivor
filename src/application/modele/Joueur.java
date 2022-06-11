package application.modele;

public class Joueur extends Entite {

    private Inventaire inventaire;
    private static String url = "application/images/Knights/sprite1.PNG";

    public Joueur(int x, int y, Environnement env, Inventaire inventaire) {
        super(x, y, env, "Héro", url);
        this.inventaire = inventaire;
    }

    public Inventaire getInventaire(){
        return this.inventaire;
    }



    @Override
    public String toString() {
        return "Joueur" + super.toString() ;
    }

}
