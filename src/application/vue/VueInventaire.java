package application.vue;

import java.util.ArrayList;

import application.modele.Bloc;
import application.modele.Epee;
import application.modele.Inventaire;
import application.modele.Item;
import application.modele.Pioche;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueInventaire {

    private int sizeMini = 4; //Nombre de case pour le mini inventaire
    private int nLigne = 2;
    private int nColonne = 5;
    private int box_size = 64;
    private Inventaire inventaire;
    private ArrayList<ImageView> listItem;
    private ArrayList<ImageView> listBox;
    ImageView background;
    private Pane pane;
    private boolean isOpen = false;

    public VueInventaire(Inventaire inventaire, Pane pane) {
        this.inventaire = inventaire;
        this.pane = pane;
        listItem = new ArrayList<ImageView>();
        listBox = new ArrayList<ImageView>();
        background = new ImageView(new Image("application/images/background.png"));
        initializeInv();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void initBackground(int x, int y, int width, int height) {
        background.setFitWidth(width);
        background.setFitHeight(height);
        background.setLayoutX(x);
        background.setLayoutY(y); 
        this.pane.getChildren().add(background);
    }

    public void createBox(int x, int y, int width, int height) {
        ImageView box = new ImageView(new Image("application/images/case.jpg"));
        box.setFitWidth(width);
        box.setFitHeight(height);
        box.setLayoutX(x);
        box.setLayoutY(y); 
        this.pane.getChildren().add(box);
        listBox.add(box);
    }

    public void createItem(Item item, int x, int y, int width, int height) {
        ImageView itemV = new ImageView(new Image(item.getUrl()));
        itemV.setFitWidth(width);
        itemV.setFitHeight(height);
        itemV.setLayoutX(x);
        itemV.setLayoutY(y); 
        this.pane.getChildren().add(itemV);
        listItem.add(itemV);
    }

    public void removeItem(Item item) {
        this.pane.getChildren().remove(listItem.get(item.getId()));
    }

    public void removeItem(ImageView item) {
        this.pane.getChildren().remove(item);
    }

    public void removeBox(ImageView box) {
        this.pane.getChildren().remove(box);
    }

    public void initializeInv(){
        for (int c = 0; c < sizeMini; c++){
            createBox((10 + (28*c)), 10, 32, 32);
            for (Item item : inventaire.getItems()) {
                if (item.getId() == c) {
                    createItem(item, (12 + (28*c)), 12, 28, 28);
                }
            }
        }
    }

    public void open() {
        initBackground(290, 110, 400, 400);
        for (int l = 0; l < nLigne; l++) {
            for (int c = 0; c < nColonne; c++) {
                createBox((315 + (70*c)), (200 + (70 * l)), box_size, box_size);
                int id = c + (l*nColonne);
                if (id < inventaire.getItems().size()) {
                    if (inventaire.getItems().get(id) != null) {
                        createItem(inventaire.getItems().get(id), (320 + (70*c)), (205 + (70 * l)), 55, 55);
                    }
                }
            }
        }
        isOpen = true;
    }
    
    public void close() {
        this.pane.getChildren().remove(background);
        for (ImageView img : listBox) {
            if(listBox.indexOf(img) >= sizeMini) {
                removeBox(img);
            }
        }
        for (ImageView img : listItem) {
            if (listItem.indexOf(img) >= inventaire.getItems().size()) {
                removeItem(img);
            }
        }
        isOpen = false;
    }

}
