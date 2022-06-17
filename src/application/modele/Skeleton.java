package application.modele;

public class Skeleton extends Entite {
    private static String url = "application/images/Skeleton.png";


    public Skeleton (int x, int y, Environnement env, String nom) {
        super(4, x, y, env, nom, url);
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void agit() {
        Entite e = this.checkZone(16);
        if (e instanceof Joueur) {
            if (reussitProba(20)) {
                env.getJoueur().decrementerPv(0.25);
            }
            if (env.getJoueur().getPv() == 0) {
                env.getJoueur().meurt();
            }
        } else {
            seDeplaceAlea();
            verifGravite();
        }

    }
}