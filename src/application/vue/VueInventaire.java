package application.vue;

import java.util.ArrayList;

import application.modele.Inventaire;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueInventaire {

    private Inventaire inventaire;

    private int sizeMini = 4; //Nombre de case pour le petit inventaire
    private int nLigne = 2, nColonne = 5;
    private int box_size = 64;
    private ArrayList<ImageView> listItemMini, listItemMax, listRessource; //Image des items du petit inventaire et grand inventaire
    private ArrayList<ImageView> listBox; //Images des cases des inventaires
    private ArrayList<Label> listLabel;
    private Rectangle borderCurrentItem;

    private Pane pane; //Pane d'ensemble
    private Pane paneInventaire; //Pane de l'inventaire
    private boolean isOpen = false;
    private Label title;

    public VueInventaire(Inventaire inventaire, Pane pane, Label title, ArrayList<ImageView> listRessources, ArrayList<Label> listLabel, Pane paneInventaire) {

        this.inventaire = inventaire;
        this.pane = pane;
        this.title = title;
        this.paneInventaire = paneInventaire;
        this.listRessource = listRessources;
        this.listLabel = listLabel;
    
        listItemMini = new ArrayList<ImageView>();
        listItemMax = new ArrayList<ImageView>();
        listBox = new ArrayList<ImageView>();

        nLigne = inventaire.getLigne();
        nColonne = inventaire.getColonne();

        addBox();
        initialize();

        for (ImageView ressource : listRessource) { ressource.setImage(new Image(inventaire.getRessources().get(listRessource.indexOf(ressource)).getUrl())); ressource.setVisible(false); }
        for (Label label : this.listLabel) label.setVisible(false);
        title.setVisible(false);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void initBackground() {
        paneInventaire.setVisible(true);
        paneInventaire.getStylesheets().add("application/vue/style.css");
        title.setVisible(true);
        for (ImageView ressource : listRessource) ressource.setVisible(true);
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
        paneInventaire.setVisible(false);
        borderCurrentItem.setVisible(false);
        for (ImageView ressource : listRessource) ressource.setVisible(false);
        for (Label label : listLabel) label.setVisible(false);
        for (ImageView box : listBox) box.setVisible(false);
        clear(listItemMax, paneInventaire);
        clear(listItemMini, pane);
        initialize();
    }

    public void addBorder() { //Creer 2 bordure pour les 2 inventaires pour le currentItem
        Rectangle borderCurrentItemMini = new Rectangle(10, 11, 30, 30);
        borderCurrentItemMini.setFill(Color.TRANSPARENT);
        borderCurrentItemMini.setStroke(Color.web("B48347"));
        borderCurrentItemMini.setStrokeWidth(3);

        borderCurrentItem = new Rectangle(31, 102, 60, 60);
        borderCurrentItem.setFill(Color.TRANSPARENT);
        borderCurrentItem.setStroke(Color.web("B48347"));
        borderCurrentItem.setStrokeWidth(4);
        this.paneInventaire.getChildren().add(borderCurrentItem);
        this.pane.getChildren().add(borderCurrentItemMini);
        borderCurrentItem.setVisible(false);
        for (ImageView box : listBox) box.setVisible(false);
    }

    public void addBox() { //Ajoutes les differentes cases des 2 inventaires avec le contour du currentItem
        String url = "application/images/case.jpg";
        for (int i = 0; i < sizeMini; i++){
            createImage((10 + (28*i)), 10, 32, 32, listBox, url, pane);
        }
        for (int l = 0; l < nLigne; l++) {
            for (int c = 0; c < nColonne; c++) {
                createImage((30 + (70*c)), (100 + (70 * l)), box_size, box_size, listBox, url, paneInventaire);
            }
        }
        addBorder();
    }


    public void initialize(){ //Initiliazation du mini inventaire
        for (int i = 0; i < sizeMini; i++){
            listBox.get(i).setVisible(true);
            if (i < inventaire.getItems().size()) {
                createImage((12 + (28*i)), 12, 28, 28, listItemMini, inventaire.getItems().get(i).getUrl(), pane);
            }
        }
    }

    public void open() { //Open le grand inventaire
        initBackground();
        borderCurrentItem.setVisible(true);
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

}
