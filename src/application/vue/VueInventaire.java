/*package application.vue;

import java.util.ArrayList;

import application.modele.Inventaire;
import application.modele.Item;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueInventaire {

    private int sizeMini = 4; //Nombre de case pour le mini inventaire
    private Inventaire inventaire;
    private int nLigne = 2;
    private int nColonne = 5;
    private int box_size = 64;
    private ArrayList<ImageView> listItem;
    private ArrayList<ImageView> listBox;

    private Pane pane;
    private boolean isOpen = false;

    private Label title;
    private ImageView background;

    public VueInventaire(Inventaire inventaire, Pane pane, Label title, ImageView background) {

        this.inventaire = inventaire;
        this.pane = pane;
        this.background = background;
        this.title = title;
    
        nLigne = inventaire.getLigne();
        nColonne = inventaire.getColonne();
        listItem = new ArrayList<ImageView>();
        listBox = new ArrayList<ImageView>();
        this.background.setImage(new Image("application/images/background.png"));

        initializeInv();

        this.title.setVisible(false);
        this.background.setVisible(false);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void initBackground(int x, int y, int width, int height) {
        title.setVisible(true);
        background.setVisible(true);
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
        title.setVisible(false);
        background.setVisible(false);
        //A changer creer une nouvelle pane
        for (ImageView img : listBox) {
            removeBox(img);
        }
        for (ImageView img : listItem) {
            removeItem(img);
        }  
        listBox.clear();      
        listItem.clear();
        for (int c = 0; c < sizeMini; c++){
            createBox((10 + (28*c)), 10, 32, 32);
            if (c < inventaire.getItems().size()) {
                createItem(inventaire.getItems().get(c), (12 + (28*c)), 12, 28, 28);
            }
        }
    }

    public void open() {
        initBackground(290, 110, 400, 400);
        for (int l = 0; l < nLigne; l++) {
            for (int c = 0; c < nColonne; c++) {
                createBox((315 + (70*c)), (220 + (70 * l)), box_size, box_size);
                int i = c + (l*inventaire.getColonne());
                if (i < inventaire.getItems().size()) {
                    createItem(inventaire.getItems().get(i), inventaire.getItems().get(i).getX() + 5, inventaire.getItems().get(i).getY() + 5, box_size - 14, box_size - 14);
                    System.out.println("Item ID = " + inventaire.getItems().get(i).getId());
                    System.out.println("x = " + inventaire.getItems().get(i).getX());
                    System.out.println("y = " + inventaire.getItems().get(i).getY());                   
                }
            }
        }
        isOpen = true;
    }
    
    public void close() {
        initializeInv();
        isOpen = false;
    }

}*/

package application.vue;

import java.util.ArrayList;

import application.modele.Inventaire;
import application.modele.Item;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueInventaire {

    private Inventaire inventaire;

    private int sizeMini = 4; //Nombre de case pour le petit inventaire
    private int nLigne = 2, nColonne = 5;
    private int box_size = 64;
    private ArrayList<ImageView> listItemMini, listItemMax; //Image des items du petit inventaire et grand inventaire
    private ArrayList<ImageView> listBox; //Images des cases des inventaires

    private Pane pane;
    private boolean isOpen = false;

    private Label title;
    private ImageView background;

    public VueInventaire(Inventaire inventaire, Pane pane, Label title, ImageView background) {

        this.inventaire = inventaire;
        this.pane = pane;
        this.background = background;
        this.title = title;
    
        nLigne = inventaire.getLigne();
        nColonne = inventaire.getColonne();
        listItemMini = new ArrayList<ImageView>();
        listItemMax = new ArrayList<ImageView>();
        listBox = new ArrayList<ImageView>();
        this.background.setImage(new Image("application/images/background.png"));
        initialize();
        this.title.setVisible(false);
        this.background.setVisible(false);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void initBackground(int x, int y, int width, int height) {
        title.setVisible(true);
        background.setVisible(true);
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

    public void createItem(Item item, int x, int y, int width, int height, ArrayList<ImageView> list) {
        ImageView itemV = new ImageView(new Image(item.getUrl()));
        itemV.setFitWidth(width);
        itemV.setFitHeight(height);
        itemV.setLayoutX(x);
        itemV.setLayoutY(y); 
        this.pane.getChildren().add(itemV);
        list.add(itemV);
    }

    public void removeImage(ImageView image) {
        this.pane.getChildren().remove(image);
    }

    public void clear(ArrayList<ImageView> list) {
        for (ImageView img : list) {
            removeImage(img);
        }
        list.clear();
    }

    public void refresh() { //Ferme les 2 inventaire puis ouvre le mini inventaire
        title.setVisible(false);
        background.setVisible(false);
        clear(listItemMax);
        clear(listItemMini);
        clear(listBox);
        if (listItemMax.size() == 0 && listItemMini.size() == 0 && listBox.size() == 0) {
            System.out.println("clear bien effectué");
        }
        initialize();
    }

    public void initialize(){ //Initiliazation du mini inventaire
        for (int c = 0; c < sizeMini; c++){
            createBox((10 + (28*c)), 10, 32, 32);
            if (c < inventaire.getItems().size()) {
                createItem(inventaire.getItems().get(c), (12 + (28*c)), 12, 28, 28, listItemMini);
            }
        }
    }

    public void open() { //Open le grand inventaire
        initBackground(290, 110, 400, 400);
        for (int l = 0; l < nLigne; l++) {
            for (int c = 0; c < nColonne; c++) {
                createBox((315 + (70*c)), (220 + (70 * l)), box_size, box_size);
                // int i = c + (l*inventaire.getColonne());
                // if (i < inventaire.getItems().size()) {
                //     createItem(inventaire.getItems().get(i), inventaire.getItems().get(i).getX() + 5, inventaire.getItems().get(i).getY() + 5, box_size - 14, box_size - 14, listItemMax);
                //     System.out.println("Item ID = " + inventaire.getItems().get(i).getId());
                //     System.out.println("x = " + inventaire.getItems().get(i).getX());
                //     System.out.println("y = " + inventaire.getItems().get(i).getY());                   
                // }
            }
        }

        for (int c = 0; c < 10; c++){
            if (c < inventaire.getItems().size()) {
                createItem(inventaire.getItems().get(c), inventaire.getItems().get(c).getX() + 5, inventaire.getItems().get(c).getY() + 5, box_size - 14, box_size - 14, listItemMax);
                System.out.println("id = " + inventaire.getItems().get(c).getId());
                System.out.println("x = " + inventaire.getItems().get(c).getX());
                System.out.println("y = " + inventaire.getItems().get(c).getY());
            }
        }
        isOpen = true;
    }
    
    public void close() { //Fermer le grand inventaire
        refresh();
        isOpen = false;
    }

}
