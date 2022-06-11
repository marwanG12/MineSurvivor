package application.modele;

public class Pierre extends Ressource {

    private static String url = "application/images/Pierre.png";

    public Pierre(String nom, int nombre) {
        super(nom, nombre, url);
    }
    
}
