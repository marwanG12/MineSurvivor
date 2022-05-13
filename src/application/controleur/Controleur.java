package application.controleur;

import application.modele.Environnement;
import application.modele.VueMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    private Environnement env;
    private VueMap terrain;

    @FXML
    private TilePane tilepane;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        env = new Environnement();
        terrain = new VueMap(env);
        terrain.afficheMap(tilepane);

    }

}
