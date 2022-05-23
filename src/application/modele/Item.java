package application.modele;

public class Item {
    private int id;
    private String nom;

    public Item(int id,String nom) {
        this.id=id;
        this.nom=nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
