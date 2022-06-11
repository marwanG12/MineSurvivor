package application.vue;

import java.util.ArrayList;

import application.modele.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VuePnj {
    private ArrayList<Entite> listPnj;
    private ArrayList<ImageView> listImagesPnj;
    private Pane pane;

    public VuePnj(ArrayList<Entite> listPnj, Pane pane) {
        this.listPnj = listPnj;
        this.pane = pane;
        listImagesPnj = new ArrayList<ImageView>();
        initializeEntite();
    }

    public void initializeEntite() {
        for (Entite pnj : listPnj) {
            listImagesPnj.add(new ImageView(new Image(pnj.getUrl())));
        }
        for (ImageView viewPnj : listImagesPnj) {
            viewPnj.setFitHeight(listPnj.get(listImagesPnj.indexOf(viewPnj)).getHeight());
            viewPnj.setFitWidth(listPnj.get(listImagesPnj.indexOf(viewPnj)).getWidth());
            viewPnj.xProperty().bind(listPnj.get(listImagesPnj.indexOf(viewPnj)).getXProperty());
            viewPnj.yProperty().bind(listPnj.get(listImagesPnj.indexOf(viewPnj)).getYProperty());
            pane.getChildren().add(viewPnj);
        }
    }
}
