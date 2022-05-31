package application.modele;

public class Ressource {
    private int id = 0;
    private String nom;
    private int nombre;

    public Ressource(String nomn, int nombre) {
        id++;
        this.nom = nom;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNombre() {
        return nombre;
    }
}
