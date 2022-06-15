package application.vue;

import java.util.ArrayList;

import application.modele.Inventaire;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueInventaire {

    private Inventaire inventaire;

    private int sizeMini = 4; //Nombre de case pour le petit inventaire
    private int nLigne = 2, nColonne = 5;
    private int box_size = 64;
    private ArrayList<ImageView> listItemMini, listItemMax, listRessource; //Image des items, ressources du petit inventaire et grand inventaire
    private ArrayList<ImageView> listItemCraft, listButton;
    private ArrayList<ImageView> listBox; //Images des cases des inventaires
    private ImageView image;
    private ArrayList<Label> listLabel;
    private ArrayList<HBox> boxcraft;

    private Pane pane, paneInventaire, paneCraft;
    private boolean isOpen = false;
    private Label title, title2;

    public VueInventaire(Inventaire inventaire, Pane pane, Label title, Label title2, ArrayList<ImageView> listRessources, ImageView image, ArrayList<Label> listLabel, ArrayList<HBox> boxs, ArrayList<ImageView> listitem, ArrayList<ImageView> listButton, Pane paneInventaire, Pane paneCraft) {

        this.inventaire = inventaire;
        this.pane = pane;
        this.paneCraft = paneCraft;
        this.paneInventaire = paneInventaire;
        this.title = title;
        this.title2 = title2;
        this.listRessource = listRessources;
        this.image = image;
        this.listItemCraft = listitem;
        this.listLabel = listLabel;
        this.boxcraft = boxs;
        this.listButton = listButton;
    
        listItemMini = new ArrayList<ImageView>();
        listItemMax = new ArrayList<ImageView>();
        listBox = new ArrayList<ImageView>();

        nLigne = inventaire.getLigne();
        nColonne = inventaire.getColonne();

        addBox();
        initialize();

        for (ImageView ressource : listRessource) { ressource.setImage(new Image(inventaire.getRessources().get(listRessource.indexOf(ressource)).getUrl())); ressource.setVisible(false); }
        for (Label label : this.listLabel) label.setVisible(false);
        for (HBox box : this.boxcraft) box.setVisible(false);
        title.setVisible(false);
        title2.setVisible(false);
        image.setVisible(false);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void initBackground() {
        paneInventaire.setVisible(true);
        paneCraft.setVisible(true);
        paneInventaire.getStylesheets().add("application/vue/style.css");
        paneCraft.getStylesheets().add("application/vue/style.css");
        title.setVisible(true);
        title2.setVisible(true);
        image.setVisible(true);
        for (ImageView ressource : listRessource) ressource.setVisible(true);
        for (HBox box : boxcraft) box.setVisible(true);
        for (ImageView image : listItemCraft) image.setVisible(true);
        for (Label label : listLabel) {
            label.setVisible(true);
            label.setText(": " + inventaire.getRessources().get(listLabel.indexOf(label)).getNombre());
        }
    }


    public void createImage(int x, int y, int width, int height, ArrayList<ImageView> list, String url, Pane p) {
        ImageView image = new ImageView(new Image(url));
        image.setFitWidth(width);
        image.setFitHeight(height);
        image.setLayoutX(x);
        image.setLayoutY(y); 
        if (list == listItemMax) {
            image.setMouseTransparent(true);
        }
        p.getChildren().add(image);
        list.add(image);
    }

    public void removeImage(ImageView image, Pane p) {
        p.getChildren().remove(image);
    }

    public void clear(ArrayList<ImageView> list, Pane p) {
        for (ImageView img : list) removeImage(img, p);
        list.clear();
    }

    public void refresh() { //Ferme les 2 inventaire puis ouvre le mini inventaire
        title.setVisible(false);
        image.setVisible(false);
        paneInventaire.setVisible(false);
        paneCraft.setVisible(false);
        for (ImageView ressource : listRessource) ressource.setVisible(false);
        for (HBox box : boxcraft) box.setVisible(false);
        for (Label label : listLabel) label.setVisible(false);
        for (ImageView image : listItemCraft) image.setVisible(false);
        for (ImageView box : listBox) box.setVisible(false);
        clear(listItemMax, paneInventaire);
        clear(listItemMini, pane);
        initialize();
    }

    public void addBox() { //Ajoutes les differentes cases des 2 inventaires avec le contour du currentItem
        for (int i = 0; i < sizeMini; i++){
            String url = "application/images/case.png";
            if (i == 0) {
                url = "application/images/currentItem.png";
                createImage((15 + (28*i)), 45, 32, 32, listBox, url, pane);
            } else {
                createImage((15 + (28*i)), 45, 32, 32, listBox, url, pane);
            }
        }
        for (int l = 0; l < nLigne; l++) {
            for (int c = 0; c < nColonne; c++) {
                String url = "application/images/case.png";
                if (l == 0 && c == 0) {
                    url = "application/images/currentItem.png";
                    createImage((30 + (70*c)), (100 + (70 * l)), box_size, box_size, listBox, url, paneInventaire);
                } else {
                    createImage((30 + (70*c)), (100 + (70 * l)), box_size, box_size, listBox, url, paneInventaire);
                }
            }
        }
        for (ImageView box : listBox) box.setVisible(false);
    }


    public void initialize(){ //Initiliazation du mini inventaire
        for (int i = 0; i < sizeMini; i++){
            listBox.get(i).setVisible(true);
            if (i < inventaire.getItems().size()) {
                createImage((21 + (28*i)), 52, 20, 20, listItemMini, inventaire.getItems().get(i).getUrl(), pane);
            }
        }
    }

    public void open() { //Open le grand inventaire
        initBackground();
        for (int i = 0; i < (nColonne*nLigne); i++){
            listBox.get(i+sizeMini).setVisible(true);
            if (i < inventaire.getItems().size()) {
                createImage(inventaire.getItems().get(i).getX() + 5, inventaire.getItems().get(i).getY() + 10, box_size - 22, box_size - 22, listItemMax, inventaire.getItems().get(i).getUrl(), paneInventaire);
            }
        }

        isOpen = true;
    }
    
    public void close() { //Fermer le grand inventaire
        refresh();
        isOpen = false;
    }

    public ArrayList<ImageView> getListItemMax() {
        return listItemMax;
    }

    public int getSizeMini() {
        return sizeMini;
    }

    public ArrayList<ImageView> getListBox() {
        return listBox;
    }

    public ArrayList<ImageView> getListButton() {
        return listButton;
    }
}
