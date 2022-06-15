package application.modele;

public class Item {
    private static int count = 0;
    private int id=0;
    private int x, y;
    private String nom;
    private String url;

    public Item(String nom, String url) {
        initialize();
        this.nom = nom;
        this.url = url;
    }

    public void initialize() {
        id = count++;
        initPosition();
    }

    public void initPosition() {
        int ligne = id/5;
        int colonne = id%5;
        x = 35 + (colonne * 70);
        y = 100 + (ligne * 70);
    }

    public void addPv(Joueur joueur) {}

    public double getDegats(){
        return 0;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
