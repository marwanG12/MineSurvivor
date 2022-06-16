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
            if (env.getEntites().size() >= env.getFires().size()) {
                Fire fire = new Fire(this, 1, env);
                env.getFires().add(fire);
                int compteur = 0;
                long timer = System.currentTimeMillis();
                int delay = 2000;
                while (compteur != 5 && System.currentTimeMillis() - timer < delay) {
                    fire.agit();
                    compteur++;
                    System.out.println("Fire x = " + fire.getX());
                    System.out.println("Fire y = " + fire.getY());
                }
            } 
            /* 
            if (reussitProba(20)) {
                env.getJoueur().decrementerPv(0.25);
            }
            if (env.getJoueur().getPv() == 0) {
                env.getJoueur().meurt();
            }*/
        }
        
    }

    public String getUrl() {
        return url;
    }

}
