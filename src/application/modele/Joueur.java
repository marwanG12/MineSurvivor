package application.modele;

public class Joueur extends Entite {

    private Inventaire inventaire;
    private static String url = "application/images/Knights/sprite1.PNG";

    public Joueur(int x, int y, Environnement env, Inventaire inventaire) {
        super(10, x, y, env, "Héro", url);
        this.inventaire = inventaire;
    }

    public Inventaire getInventaire(){
        return this.inventaire;
    }

    @Override
    public void agit() {
        if (inventaire.getCurrentItem() instanceof Armes) {
            Entite e = this.checkZone(64);
            if (e != null) {
                e.decrementerPv(inventaire.getCurrentItem().getDegats());
                if (e.getPv() == 0) {
                    e.meurt();
                }       
            }     
        }
    }

    @Override
    public String toString() {
        return "Joueur" + super.toString() ;
    }

}
