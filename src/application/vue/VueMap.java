package application.vue;

import application.modele.Environnement;
import javafx.css.converter.PaintConverter;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class VueMap {

    private Environnement env;

    public VueMap(Environnement env, TilePane tilepane) {
        this.env = env;
        initializeMap(tilepane);
    }

    public void initializeMap(TilePane tilepane) {
        Image tileset = new Image("application/images/Tileset.png");
        Image tileciel = new Image("application/images/ciel.png");

        for (int i=0; i < env.getNbTiles(); i++) {
            ImageView terre = new ImageView(tileset);
            ImageView ciel = new ImageView(tileciel);

            int ligne = env.getTile(i)/16;
            int colonne = (env.getTile(i)%16)-1;
            int y = ligne * 32;
            int x = colonne * 32;
            BackgroundImage background= new BackgroundImage(new Image("application/images/background2.jpg",960,640,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            tilepane.setBackground(new Background(background));

            if (env.getTile(i) != 0) {
                terre.setViewport(new Rectangle2D(x, y, 32, 32));
                tilepane.getChildren().add(terre);
            } else {
                tilepane.getChildren().add(ciel);
            }

        }
    }
}





