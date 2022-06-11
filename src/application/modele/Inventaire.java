package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Item> items;
    private ObservableList<Ressource> ressources;
    private int limiteItem = 10;
    private Item currentItem;
    private int ligne = 2;
    private int colonne = 5;

    public Inventaire(){
        items = FXCollections.observableArrayList();
        ressources = FXCollections.observableArrayList();
    }

    public Item getCurrentItem() { return currentItem; }

    public int getLigne() { return ligne; }

    public int getColonne() { return colonne; }

    public ObservableList<Item> getItems() { return items; }

    public ObservableList<Ressource> getRessources() { return ressources; }

    public void initialize() {
        items.setAll(
            new Epee("Epee", 1), 
            new Bloc("Bloc"), 
            new Pioche("Pioche", 1, 1));
        ressources.setAll(
            new Pierre("Pierre", 0),
            new Piece("Pièce", 0),
            new Bois("Bois", 0),
            new Fer("Fer", 0));
    }

    public void checkId() {
        for (Item item : items) {
            if (items.indexOf(item) != item.getId()) {
                item.setId(items.indexOf(item));
                item.initPosition();
            }
        }
    }

    public void addItem(Item item) throws Exception {
        if (items.size() == limiteItem) {
            throw new Exception();
        } else {
            items.add(item);
            checkId();
        }
    }

    public void addRessource(Ressource r) {
        for (Ressource ressource : ressources) {
            if (r == ressource) {
                r.setNombre(r.getNombre()+1);
            }
        }
    }
    
    public void removeItem(){
        int id = currentItem.getId();
        items.remove(currentItem);
        for (Item item : items) {
            if (item.getId() > id) {
                if (item.getId() == id+1) {
                    Item.setCount(Item.getCount()-1);
                }
                item.setId(items.indexOf(item));
                item.initPosition();
            } 
        }
        checkId();
    }

    public void selectItem(int x, int y) {
        boolean vide = false;
        int id;
        if (x >= items.get(0).getX() && x <= (items.get(0).getX()+70*colonne) && y >= items.get(0).getY() && y <= (items.get(0).getY()+70*ligne)) {

            x -= items.get(0).getX();
            y -= items.get(0).getY();

            for (int i=1; i <= colonne; i++) {
                if (x > (64 * i) && x < ((64 * i) + (6*i))) {
                    vide = true;
                }
            }

            for (int i=1; i <= ligne; i++) {
                if (y > (64 * i) && y < ((64 * i) + (6*i))) {
                    vide = true;
                }
            }
            if (!vide) {
                id = (y/70) * colonne + (x /64);
                if (items.size() >= id + 1) {
                    currentItem = items.get(id);
                }
            }
            if (currentItem != null)
                System.out.println(currentItem.toString());
        }
    }
    
}