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
    private ArrayList<ImageView> listItemMini, listItemMax, listRessource; //Image des items du petit inventaire et grand inventaire
    private ArrayList<ImageView> listBox; //Images des cases des inventaires
    private ArrayList<Label> listLabel;

    private Pane pane;
    private boolean isOpen = false;

    private Label title;
    private ImageView background;

    public VueInventaire(Inventaire inventaire, Pane pane, Label title, ImageView background, ArrayList<ImageView> listRessources, ArrayList<Label> listLabel) {

        this.inventaire = inventaire;
        this.pane = pane;
        this.background = background;
        this.title = title;
    
        nLigne = inventaire.getLigne();
        nColonne = inventaire.getColonne();
        listItemMini = new ArrayList<ImageView>();
        listItemMax = new ArrayList<ImageView>();
        this.listRessource = listRessources;
        this.listLabel = listLabel;
        listBox = new ArrayList<ImageView>();
        this.background.setImage(new Image("application/images/background.png"));
        for (ImageView ressource : listRessource) {
            ressource.setImage(new Image(inventaire.getRessources().get(listRessource.indexOf(ressource)).getUrl()));
            ressource.setVisible(false);
        }
        for (Label label : this.listLabel) label.setVisible(false);
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
        for (ImageView ressource : listRessource) ressource.setVisible(true);
        for (Label label : listLabel) {
            label.setVisible(true);
            label.setText(": " + inventaire.getRessources().get(listLabel.indexOf(label)).getNombre());
        }
    }

    public void createImage(int x, int y, int width, int height, ArrayList<ImageView> list, String url) {
        ImageView box = new ImageView(new Image(url));
        box.setFitWidth(width);
        box.setFitHeight(height);
        box.setLayoutX(x);
        box.setLayoutY(y); 
        this.pane.getChildren().add(box);
        list.add(box);
    }

    public void removeImage(ImageView image) {
        this.pane.getChildren().remove(image);
    }

    public void clear(ArrayList<ImageView> list) {
        for (ImageView img : list) removeImage(img);
        list.clear();
    }

    public void refresh() { //Ferme les 2 inventaire puis ouvre le mini inventaire
        title.setVisible(false);
        background.setVisible(false);
        for (ImageView ressource : listRessource) ressource.setVisible(false);
        for (Label label : listLabel) label.setVisible(false);
        clear(listItemMax);
        clear(listItemMini);
        clear(listBox);
        initialize();
    }

    public void initialize(){ //Initiliazation du mini inventaire
        for (int i = 0; i < sizeMini; i++){
            createImage((10 + (28*i)), 10, 32, 32, listBox, "application/images/case.jpg");
            if (i < inventaire.getItems().size()) {
                createImage((12 + (28*i)), 12, 28, 28, listItemMini, inventaire.getItems().get(i).getUrl());
            }
        }
    }

    public void open() { //Open le grand inventaire
        initBackground(290, 110, 400, 400);
        for (int l = 0; l < nLigne; l++) {
            for (int c = 0; c < nColonne; c++) {
                createImage((315 + (70*c)), (220 + (70 * l)), box_size, box_size, listBox, "application/images/case.jpg");
            }
        }

        for (int i = 0; i < 10; i++){
            if (i < inventaire.getItems().size()) {
                createImage(inventaire.getItems().get(i).getX() + 5, inventaire.getItems().get(i).getY() + 5, box_size - 14, box_size - 14, listItemMax, inventaire.getItems().get(i).getUrl());
            }
        }
        isOpen = true;
    }
    
    public void close() { //Fermer le grand inventaire
        refresh();
        isOpen = false;
    }

}
