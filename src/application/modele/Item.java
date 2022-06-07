package application.modele;

public class Item {
    private static int count = 0;
    private int id=0;
    private String nom;
    private String url;
    private boolean enMain = false;

    public Item(String nom, String url) {
        id=count++;
        this.nom=nom;
        this.url = url;
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

    public boolean setEnMain(){
        return this.enMain = true;
    }


}
