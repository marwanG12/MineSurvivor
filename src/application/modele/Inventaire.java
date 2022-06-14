package application.modele;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Item> items;
    private ObservableList<Ressource> ressources;
    private int limiteItem = 10;
    private Item currentItem;
    private int ligne = 2;
    private int colonne = 5;
    private boolean select = false;
    private boolean remove = false;

    public Inventaire(){
        items = FXCollections.observableArrayList();
        ressources = FXCollections.observableArrayList();
        initialize();
    }

    public Item getCurrentItem() { return currentItem; }

    public int getLigne() { return ligne; }

    public int getColonne() { return colonne; }

    public ObservableList<Item> getItems() { return items; }

    public ObservableList<Ressource> getRessources() { return ressources; }

    public void initialize() {
        items.setAll(
            new Epee("Epee"), 
            new Bloc("Bloc"), 
            new Pioche("Pioche", 1, 1));
        ressources.setAll(
            new Pierre("Pierre", 0),
            new Bois("Bois", 0),
            new Piece("Pièce", 0),
            new Fer("Fer", 0));
        currentItem = items.get(0);
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

    public void addRessource(int index) {
        for (int i=0; i < ressources.size(); i++) {
            if (i == index) {
                ressources.get(i).setNombre(ressources.get(i).getNombre()+1);
            }
        }
    }
    
    public void removeItem(){
        if (currentItem != null && select) {
            items.remove(currentItem);
            checkId();
            select = false;
            remove = true;
        }
        System.out.println(items.toString());
    }


    public void selectItem(Item i, boolean remove) {
        currentItem = i;
        if (!remove) {
            Collections.swap(items, i.getId(), 0);
            checkId();
        }
        select = true;
    }
    
    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }    
}