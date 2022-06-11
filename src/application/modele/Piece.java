package application.modele;

public class Piece extends Ressource {

    private static String url = "application/images/Piece.png";

    public Piece(String nom, int nombre) {
        super(nom, nombre, url);
    }
}
