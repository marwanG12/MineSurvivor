package application.modele;

public class Necromancer extends Entite {

    private static String url = "application/images/Necromancier.png";
    private Fire fire;

    public Necromancer(int x, int y, Environnement env, String nom) {
        super(2, x, y, env, nom, url);
    }

    public void generateFire() {
        if (fire == null) {
            fire = new Fire(this, 1, env);
            fire.setRight(super.isRight());
            fire.setLeft(super.isLeft());
            env.getFires().add(fire);
        }
    }

    public void checkId() {
        for (Fire fire : env.getFires()) {
            if (env.getFires().indexOf(fire) != fire.getId()) {
                fire.setId(env.getFires().indexOf(fire));
            }
        }
    }

    public void removeFire() {
        env.getFires().remove(fire);
        checkId();
        fire = null;
    }

    @Override
    public void agit() {
        Entite e = this.checkZone(100);
        if (e instanceof Joueur) {
            if (fire == null) {
                generateFire();
            }
        } else {
            if (fire == null) {
                seDeplaceAlea();
                verifGravite();
            }
        }
        
    }

    public String getUrl() {
        return url;
    }

}
