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
    //private ObservableList<Fire> listFire;
    private ArrayList<ImageView> listImagesPnj;
    //private ArrayList<ImageView> listImagesFire;
    private ArrayList<ProgressBar> listBarPv;
    private Pane pane;

    public VuePnj(ObservableList<Entite> listPnj, /*ObservableList<Fire> listFire,*/ Pane pane) {
        this.listPnj = listPnj;
        //this.listFire = listFire;
        this.pane = pane;
        listImagesPnj = new ArrayList<ImageView>();
        //listImagesFire = new ArrayList<ImageView>();
        listBarPv = new ArrayList<ProgressBar>();
        initializeEntite();
    }

    public void clearImages() {
        for (ImageView imagePnj : listImagesPnj) {
            pane.getChildren().remove(imagePnj);
        }

        /*for (ImageView imageFire : listImagesFire) {
            pane.getChildren().remove(imageFire);
        }*/

        for (ProgressBar pvBar : listBarPv) {
            pane.getChildren().remove(pvBar);

        }
    }

    public void initializeEntite() {        
        for (Entite pnj : listPnj) { 
            listImagesPnj.add(new ImageView(new Image(pnj.getUrl())));
            ProgressBar bar = new ProgressBar();
            bar.setPrefWidth(pnj.getWidth());
            bar.setPrefHeight(4);
            bar.layoutXProperty().bind(pnj.getXProperty());
            bar.layoutYProperty().bind(pnj.getYProperty().subtract(10));
            bar.getStylesheets().add("application/vue/style.css");
            bar.progressProperty().bind(pnj.getPvProperty().divide(2));
            listBarPv.add(bar);
            pane.getChildren().add(bar);
        }


        /*for (Entite fire : listFire) { 
            listImagesFire.add(new ImageView(new Image(fire.getUrl())));
        }*/

        for (int i=0; i < listImagesPnj.size(); i++) { 
            if (i < listPnj.size()) {
                listImagesPnj.get(i).setFitHeight(listPnj.get(i).getHeight());
                listImagesPnj.get(i).setFitWidth(listPnj.get(i).getWidth());
                listImagesPnj.get(i).xProperty().bind(listPnj.get(i).getXProperty());
                listImagesPnj.get(i).yProperty().bind(listPnj.get(i).getYProperty());
                pane.getChildren().add(listImagesPnj.get(i));
            }
        }

        /*for (int i=0; i < listImagesFire.size(); i++) { 
            if (i < listFire.size()) {
                listImagesFire.get(i).setFitHeight(listFire.get(i).getHeight());
                listImagesFire.get(i).setFitWidth(listFire.get(i).getWidth());
                listImagesFire.get(i).xProperty().bind(listFire.get(i).getXProperty());
                listImagesFire.get(i).yProperty().bind(listFire.get(i).getYProperty());
                pane.getChildren().add(listImagesFire.get(i));
            }
        }*/
    }
}
