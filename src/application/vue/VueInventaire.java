package application.vue;

import application.modele.Inventaire;
import application.modele.Item;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueInventaire {

    public Item item;
    public Inventaire inventaire;
    public Image img_inventaire;
    public ImageView img_view;
    public Pane tilepane;

    public VueInventaire() {
        this.inventaire = inventaire;
        img_inventaire = new Image("application/images/case.jpg");
        img_view = new ImageView(img_inventaire);
        tilepane = new TilePane();
        initializeInv();
    }

    public void initializeInv(){
        ImageView imgInventaire = null;
        for (int i = 0; i<7;i++){
            imgInventaire = new ImageView(new Image("application/images/case.jpg"));
            this.tilepane.getChildren().add(imgInventaire);
        }
    }

    public void afficherInventaire(){
        this.tilepane.setVisible(true);
    }

}
