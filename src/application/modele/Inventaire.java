package application.modele;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Item> items;
    private int x = 32;
    private int y = 32;

    public Inventaire(){
        items = FXCollections.observableArrayList();
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public ObservableList<Item> getItems() { return items; }

    public void addItem(Item item){
        items.add(item);
    }

    public void initialize() {
        items.setAll(
            new Epee("Epee", 1), 
            new Bloc("Bloc"), 
            new Pioche("Pioche", 1, 1));
    }
    
    public void removeItem(Item i){
        for (Item item : items) {
            if (item.getId() == i.getId()) {
                items.remove(item);
            }
        }
    }
}