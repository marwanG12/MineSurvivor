package application.vue;

import java.util.ArrayList;

import application.modele.Entite;
import application.modele.Fire;
import javafx.collections.ObservableList;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VuePnj {
    private ObservableList<Entite> listPnj;
    private ArrayList<ImageView> listImagesPnj;
    private ArrayList<ProgressBar> listBarPv;
    private ProgressBar bar;
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

    public void removeImage(Entite pnj) {
        for (int i=0; i < listImagesPnj.size(); i++) {
            if (i == pnj.getId()) {
                pane.getChildren().remove(listImagesPnj.get(i));
                pane.getChildren().remove(listBarPv.get(i));
            }
        }
    }

    public void scaleImage(Entite pnj, boolean pos) {
        int i = pnj.getId();
        if (pos) {
            listImagesPnj.get(i).setScaleX(-1);
        } else {
            listImagesPnj.get(i).setScaleX(1);
        }
    }

    public void addImage(Entite pnj) {
        for (int i=0; i < listImagesPnj.size(); i++) {
            if (i == pnj.getId()) {
                listImagesPnj.add(new ImageView(new Image(pnj.getUrl())));
                listImagesPnj.get(i).setFitHeight(pnj.getHeight());
                listImagesPnj.get(i).setFitWidth(pnj.getWidth());
                listImagesPnj.get(i).xProperty().bind(pnj.getXProperty());
                listImagesPnj.get(i).yProperty().bind(pnj.getYProperty());

                bar = new ProgressBar();
                bar.setPrefWidth(pnj.getWidth());
                bar.setPrefHeight(4);
                bar.layoutXProperty().bind(pnj.getXProperty());
                bar.layoutYProperty().bind(pnj.getYProperty().subtract(10));
                bar.getStylesheets().add("application/vue/style.css");
                bar.progressProperty().bind(pnj.getPvProperty().divide(2));
                listBarPv.add(bar);
            
                pane.getChildren().add(1, listImagesPnj.get(i));
                pane.getChildren().add(1, listBarPv.get(i));
            }
        }
    }

    public void initializeEntite() {        
        for (Entite pnj : listPnj) { 
            listImagesPnj.add(new ImageView(new Image(pnj.getUrl())));
            bar = new ProgressBar();
            bar.setPrefWidth(pnj.getWidth());
            bar.setPrefHeight(4);
            bar.layoutXProperty().bind(pnj.getXProperty());
            bar.layoutYProperty().bind(pnj.getYProperty().subtract(10));
            bar.getStylesheets().add("application/vue/style.css");
            bar.progressProperty().bind(pnj.getPvProperty().divide(2));
            listBarPv.add(bar);
            pane.getChildren().add(1, bar);
        }

        for (int i=0; i < listImagesPnj.size(); i++) { 
            if (i < listPnj.size()) {
                listImagesPnj.get(i).setFitHeight(listPnj.get(i).getHeight());
                listImagesPnj.get(i).setFitWidth(listPnj.get(i).getWidth());
                listImagesPnj.get(i).xProperty().bind(listPnj.get(i).getXProperty());
                listImagesPnj.get(i).yProperty().bind(listPnj.get(i).getYProperty());
                pane.getChildren().add(1, listImagesPnj.get(i));
            }
        }
    }
}
