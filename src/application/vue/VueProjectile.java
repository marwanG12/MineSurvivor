package application.vue;

import java.util.ArrayList;

import application.modele.Fire;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueProjectile {
    private ObservableList<Fire> listFire;
    private ArrayList<ImageView> listImagesFire;
    private Pane pane;

    public VueProjectile(ObservableList<Fire> listFire, Pane pane) {
        this.listFire = listFire;
        this.pane = pane;
        this.listImagesFire = new ArrayList<ImageView>();
    }

    public void addImage(Fire fire) {
        int i = fire.getId();
        if (fire != null) {
            listImagesFire.add(new ImageView(new Image(fire.getUrl())));
            listImagesFire.get(i).setFitHeight(fire.getHeight());
            listImagesFire.get(i).setFitWidth(fire.getWidth());
            listImagesFire.get(i).xProperty().bind(fire.getXProperty());
            listImagesFire.get(i).yProperty().bind(fire.getYProperty());
            pane.getChildren().add(1, listImagesFire.get(i));
        }
    }

    public void removeImage(Fire fire) {
        int i = fire.getId();
        if (fire != null) {
            pane.getChildren().remove(listImagesFire.get(i));
            listImagesFire.remove(listImagesFire.get(i));
        }
    }

}
