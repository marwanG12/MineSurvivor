package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Item> listeItems;
    private int x = 32;
    private int y = 32;

    public Inventaire(){
        listeItems = FXCollections.observableArrayList();
    }


    public int getX() { return x; }

    public int getY() { return y; }

    public ObservableList<Item> getListeItems() { return listeItems; }

    public void addItem(Item item){
        listeItems.add(item);
    }

    public void initialize() {
        listeItems.setAll(
            new Epee("Epee", 1), 
            new Bloc("Bloc"), 
            new Pioche("Pioche", 1, 1));
    }
    
    public void removeItem(Item i){
        for (Item item : listeItems) {
            if (item.getId() == i.getId()) {
                listeItems.remove(item);
            }
        }
    }

    public void selectItem(Item item){
        for (int i = 0; i< listeItems.size(); i++){
            if (listeItems.get(i).getId() ==item.getId()) {
                item.setEnMain();
                System.out.println(listeItems.get(i).getNom() + " est en main !");
            }
            /*if(listeItems.get(i)==null) {
                System.out.println("aucun objet");
            }*/
        }
    }
}