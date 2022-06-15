package application.modele;

public class Ressource {
    private static int count = 0;
    private int id = 0;
    private String nom;
    private int nombre;
    private String url;

    public Ressource(String nom, int nombre, String url) {
        id = count++;
        this.nom = nom;
        this.url = url;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getUrl() {
        return url;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    
}
