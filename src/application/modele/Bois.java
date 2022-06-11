package application.modele;

public class Bois extends Ressource {

    private static String url = "application/images/Bois.png";

    public Bois(String nom, int nombre) {
        super(nom, nombre, url);
    }
}
