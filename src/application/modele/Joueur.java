package application.modele;

public class Joueur extends Entite {

    public Joueur(int x, int y, Environnement env) {
        super(x, y, env, "Héro");
    }


    @Override
    public String toString() {
        return "Joueur" + super.toString() ;
    }

}
