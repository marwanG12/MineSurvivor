package application.modele;

public class Joueur extends Acteur {
    public Joueur(int x, int y, Environnement env) {
        super(x, y, env, "Sucre");
    }

    public void agit() {
        int ax= this.getX();
        int ay= this.getY();
        System.out.println("L " + this.getId()+ " se deplace de " + ax + ","+ ay + "vers " + this.getX()+ ","+ this.getY());
    }

    @Override
    public String toString() {
        return "Joueur" + super.toString() ;
    }

}
