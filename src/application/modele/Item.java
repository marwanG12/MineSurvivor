package application.modele;

public class Item {
    private static int count = 0;
    private static int colonne = 0;
    private static int ligne = 0;
    private int id=0;
    private int x = 315, y = 220;
    private String nom;
    private String url;

    public Item(String nom, String url) {
        id = count++;
        x += (colonne++ * 70);
        y += (ligne * 70);
        while (colonne != 0 && colonne%5== 0) { 
            colonne = 0;
            ligne++;
        }
        this.nom=nom;
        this.url = url;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Item.count = count;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", nom=" + nom + "]";
    }

    
}
