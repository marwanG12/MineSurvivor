package application.vue;

import application.modele.Environnement;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.TilePane;

public class VueMap {

    private Environnement env;
    private Image tileset = new Image("application/images/Tileset.png");
    private Image tilepierre = new Image("application/images/Tilepierre.png");
    private Image tileciel = new Image("application/images/ciel.png");
    private TilePane tilepane;
    public static int id = 0;

    public VueMap(Environnement env, TilePane tilepane) {
        this.env = env;
        this.tilepane = tilepane;
        initializeMap();
    }

    public void initializeMap() {
        for (int i=0; i < env.getNbTiles(); i++) {
            ImageView terre = new ImageView(tileset);
            ImageView ciel = new ImageView(tileciel);
            ImageView coffre = new ImageView(new Image("application/images/Coffre.png"));
            ImageView bois = new ImageView(new Image("application/images/Tronc.png"));


            int ligne = env.getTile(i)/16;
            int colonne = (env.getTile(i)%16)-1;
            int y = ligne * 32;
            int x = colonne * 32;
            BackgroundImage background= new BackgroundImage(new Image("application/images/background.jpg",env.getWidth(),env.getHeight(),false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            tilepane.setBackground(new Background(background));


            if (env.getTile(i) == 0) {
                tilepane.getChildren().add(ciel);
            } else if (env.getTile(i) == 300) {
                coffre.setFitHeight(32);
                coffre.setFitWidth(32);
                tilepane.getChildren().add(bois);
            } else if (env.getTile(i) == 400) {
                coffre.setFitHeight(32);
                coffre.setFitWidth(32);
                tilepane.getChildren().add(coffre);
            } else {
                terre.setViewport(new Rectangle2D(x, y, 32, 32));
                tilepane.getChildren().add(terre);
            }
        }
    }

    public ImageView gameover() {
        ImageView gameover = new ImageView(new Image("application/images/gameover.jpeg"));
        gameover.setFitHeight(env.getHeight());
        gameover.setFitWidth(env.getWidth());
        return gameover;
    }

    public void editTile(Integer idTile, int idCode){
        ImageView pierre = new ImageView(tilepierre);
        ImageView ciel = new ImageView(tileciel);

        if (idTile != null) {
            if (idCode == 0) {
                tilepane.getChildren().remove(idTile.intValue());
                tilepane.getChildren().add(idTile, pierre);
            } else if (idCode == 60) {
                tilepane.getChildren().remove(idTile.intValue());
                tilepane.getChildren().add(idTile, ciel);
            }
        }
    }

}
