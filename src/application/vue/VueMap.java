package application.vue;

import application.modele.Environnement;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

            int ligne = env.getTile(i)/16;
            int colonne = (env.getTile(i)%16)-1;
            int y = ligne * 32;
            int x = colonne * 32;
            BackgroundImage background= new BackgroundImage(new Image("application/images/background2.jpg",960,640,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            tilepane.setBackground(new Background(background));

            if (env.getTile(i) != 0) {
                terre.setViewport(new Rectangle2D(x, y, 32, 32));
                terre.setId(String.valueOf(id++));
                terre.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickBlocEvent());
                tilepane.getChildren().add(terre);

            } else {
                ciel.setId(String.valueOf(id++));
                tilepane.getChildren().add(ciel);
            }

        }
    }

    public void deleteBloc(TilePane tilepane, String id){
        tilepane.getChildren().remove(tilepane.lookup("#"+ id));
        ImageView imgView = new ImageView(tileciel);
        imgView.setId(String.valueOf(id));
        tilepane.getChildren().add(Integer.parseInt(id), imgView);
    }
    public void addBloc(TilePane tilepane, String id){
        tilepane.getChildren().remove(tilepane.lookup('#' + id));
        ImageView imgView = new ImageView(tileset);
        imgView.setViewport(new Rectangle2D(352, 128, 32, 32));
        imgView.setId(String.valueOf(id));
        tilepane.getChildren().add(Integer.parseInt(id), imgView);
    }

    private class ClickBlocEvent implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            String idDuBloc = ((ImageView)evt.getSource()).getId();
            System.out.println(((ImageView)evt.getSource()).getId());
            if (env.isAddBloc()) {
                addBloc(tilepane, idDuBloc);
                env.setAddBloc(false);
            } else if (env.isDeleteBloc()) {
                deleteBloc(tilepane, idDuBloc);
                env.setDeleteBloc(false);
            }
        }
    }
}





