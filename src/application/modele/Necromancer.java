package application.modele;

public class Necromancer extends Entite {

    private static String url = "application/images/Necromancier.png";
    
    public Necromancer(int x, int y, Environnement env, String nom) {
        super(2, x, y, env, nom, url);
    }

    @Override
    public void agit() {
        seDeplaceAlea();
        verifGravite();
        Entite e = this.checkZone(100);
        if (e instanceof Joueur) {
            /*Fire fire = new Fire(this, 1, env);
            env.getFires().add(fire);
            int compteur = 0;
            while (fire != null || compteur != 5) {
                fire.agit();
                compteur++;
            }*/
            if (reussitProba(20)) {
                env.getJoueur().decrementerPv(0.25);
            }
            if (env.getJoueur().getPv() == 0) {
                env.getJoueur().meurt();
            }
        }
        
    }

    public String getUrl() {
        return url;
    }

}
