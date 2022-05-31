package application.modele;

public class Item {
    private int id = 0;
    private String nom;
    public Item(String nom) {
        id++;
        this.nom=nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
