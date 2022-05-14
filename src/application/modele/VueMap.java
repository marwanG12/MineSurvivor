package application.modele;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class VueMap {

    private Environnement env;


    public VueMap(Environnement env) {
        this.env = env;
    }

    public void afficheMap(TilePane tilepane) {
        Image tileset = new Image("application/images/Tileset.png");
        Image tileset2 = new Image("application/images/sky.png");

        for (int i=0; i < env.getNbTiles(); i++) {
            ImageView terre = new ImageView(tileset);
            ImageView ciel = new ImageView(tileset2);

            int ligne = env.getCodeTiles(i)/16;
            int colonne = (env.getCodeTiles(i)%16)-1;
            int y = ligne * 32;
            int x = colonne * 32;

            if (env.getCodeTiles(i) != 0) {
                terre.setViewport(new Rectangle2D(x, y, 32, 32));
                tilepane.getChildren().add(terre);
            } else {
                ciel.setViewport(new Rectangle2D(0, 256, 32, 32));
                tilepane.getChildren().add(ciel);
            }

        }
    }
}





