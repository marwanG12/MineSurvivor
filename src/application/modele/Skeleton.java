package application.modele;

public class Skeleton extends Entite {
    private static String url = "application/images/Skeleton.png";
    private int loop; //vitesse attaque


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
            loop++;
            if (loop == 10) {
                env.getJoueur().decrementerPv(1);
                loop = 0;
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