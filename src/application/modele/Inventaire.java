package application.modele;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    public ObservableList<Item> items;

    public Inventaire(){
        items = FXCollections.observableArrayList();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void initialize() {
        items.setAll(
            new Epee("Epee", 1), 
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