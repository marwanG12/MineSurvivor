package application.modele;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class InventaireTest {

    @org.junit.jupiter.api.Test
    void getCurrentItem() throws Exception {
        Inventaire inv = new Inventaire();
        inv.addItem(new Pioche("testPioche", 0.5, 1));
        inv.addItem(new Epee("testEpee"));
        inv.setCurrentItem(inv.getItems().get(2));
        Assertions.assertEquals(inv.getItems().get(2),inv.getCurrentItem());
    }

    @org.junit.jupiter.api.Test
    void getItems() throws Exception {
        Inventaire inv = new Inventaire(); /*Lors de la création de l'inventaire du joueur il y a par defaut une pioche*/
        inv.addItem(new Pioche("testPioche", 0.5, 1));
        inv.addItem(new Epee("testEpee"));
        inv.addItem(new Potion("potionTest"));
        Assertions.assertEquals(4,inv.getItems().size());
    }

    @org.junit.jupiter.api.Test
    void getRessources() {
        Inventaire inv = new Inventaire();
        inv.addRessource(0/*pierre*/);
        Assertions.assertEquals(1,inv.getRessources().get(0).getNombre());

    }

    @org.junit.jupiter.api.Test
    void isSelect() {
    }

    @org.junit.jupiter.api.Test
    void isCraft() {
    }

    @org.junit.jupiter.api.Test
    void checkId() {
    }

    @org.junit.jupiter.api.Test //ne sert a rien de le tester
    void addRessource() {

    }

    @org.junit.jupiter.api.Test
    void removeItem() throws Exception {
        Inventaire inv = new Inventaire();
        inv.addItem(new Epee("épéeTest"));
        inv.addItem(new Pioche("piocheTest",0.5,1));
        inv.selectItem(inv.getItems().get(0),false);
        inv.removeItem();
        Assertions.assertEquals(2,inv.getItems().size());
    }

    @org.junit.jupiter.api.Test
    void selectItem() {
    }

    @org.junit.jupiter.api.Test
    void craftItem() {
    }
}