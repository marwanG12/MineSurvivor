package application.modele;

import javafx.fxml.FXML;
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
            switch(env.getCodeTiles(i)) {
                case 0:
                    ciel.setViewport(new Rectangle2D(0, 256, 32, 32));
                    tilepane.getChildren().add(ciel);
                    break;
                case 1:
                    terre.setViewport(new Rectangle2D(0, 0, 32, 32));
                    tilepane.getChildren().add(terre);
                    break;
                case 2: 
                    terre.setViewport(new Rectangle2D(32, 0, 32, 32));
                    tilepane.getChildren().add(terre);
                    break;
                case 3:
                    terre.setViewport(new Rectangle2D(64, 0, 32, 32));
                    tilepane.getChildren().add(terre);
                    break;
                case 17:
                    terre.setViewport(new Rectangle2D(0, 32, 32, 32));
                    tilepane.getChildren().add(terre);
                    break;
                case 18:
                    terre.setViewport(new Rectangle2D(32, 32, 32, 32));
                    tilepane.getChildren().add(terre);
                    break;
                case 19:
                    terre.setViewport(new Rectangle2D(64, 32, 32, 32));
                    tilepane.getChildren().add(terre);
                    break;
                case 33:
                    terre.setViewport(new Rectangle2D(0, 64, 32, 32));
                    tilepane.getChildren().add(terre);
                    break;
                case 34:
                    terre.setViewport(new Rectangle2D(32, 128, 32, 32));
                    tilepane.getChildren().add(terre);
                    break;
                case 35: 
                    terre.setViewport(new Rectangle2D(64, 64, 32, 32));
                    tilepane.getChildren().add(terre);
                    break;
                default:
                    break;
            } 
        }
    }






}
