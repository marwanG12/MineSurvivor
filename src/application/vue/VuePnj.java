package application.vue;

import java.util.ArrayList;

import application.modele.Entite;
import javafx.collections.ObservableList;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VuePnj {
    private ObservableList<Entite> listPnj;
    private ArrayList<ImageView> listImagesPnj;
    private ArrayList<ProgressBar> listBarPv;
    private Pane pane;

    public VuePnj(ObservableList<Entite> listPnj, Pane pane) {
        this.listPnj = listPnj;
        this.pane = pane;
        listImagesPnj = new ArrayList<ImageView>();
        listBarPv = new ArrayList<ProgressBar>();
        initializeEntite();
    }

    public void clearImages() {
        for (ImageView imagePnj : listImagesPnj) {
            pane.getChildren().remove(imagePnj);
        }
        for (ProgressBar pvBar : listBarPv) {
            pane.getChildren().remove(pvBar);

        }
    }

    public void initializeEntite() {        
        for (Entite pnj : listPnj) { 
            listImagesPnj.add(new ImageView(new Image(pnj.getUrl())));
            ProgressBar bar = new ProgressBar();
            bar.setPrefWidth(pnj.getWidth());
            bar.setPrefHeight(10);
            bar.layoutXProperty().bind(pnj.getXProperty());
            bar.layoutYProperty().bind(pnj.getYProperty().subtract(10));
            bar.setStyle("-fx-accent: #FF0000;");
            bar.progressProperty().bind(pnj.getPvProperty().divide(2));
            listBarPv.add(bar);
            pane.getChildren().add(bar);
        }

        for (int i=0; i < listImagesPnj.size(); i++) { 
            if (i < listPnj.size()) {
                listImagesPnj.get(i).setFitHeight(listPnj.get(i).getHeight());
                listImagesPnj.get(i).setFitWidth(listPnj.get(i).getWidth());
                listImagesPnj.get(i).xProperty().bind(listPnj.get(i).getXProperty());
                listImagesPnj.get(i).yProperty().bind(listPnj.get(i).getYProperty());
                pane.getChildren().add(listImagesPnj.get(i));
            }
        }
    }
}
