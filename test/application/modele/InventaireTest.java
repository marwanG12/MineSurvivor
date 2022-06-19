package application.modele;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class InventaireTest {

    @org.junit.jupiter.api.Test
    void getCurrentItemTest() throws Exception {
        Inventaire inv = new Inventaire();
        inv.addItem(new Pioche("testPioche", 0.5, 1));
        inv.addItem(new Epee("testEpee"));
        inv.setCurrentItem(inv.getItems().get(2));
        Assertions.assertEquals(inv.getItems().get(2),inv.getCurrentItem());
    }

    @org.junit.jupiter.api.Test
    void getItemsTest() throws Exception {
        Inventaire inv = new Inventaire(); /*Lors de la création de l'inventaire du joueur il y a par defaut une pioche*/
        inv.addItem(new Pioche("testPioche", 0.5, 1));
        inv.addItem(new Epee("testEpee"));
        inv.addItem(new Potion("potionTest"));
        Assertions.assertEquals(4,inv.getItems().size());
    }

    @org.junit.jupiter.api.Test //la méthode addRessource est aussi testée ici
    void getRessourcesTest() {
        Inventaire inv = new Inventaire();
        inv.addRessource(0/*pierre*/);
        Assertions.assertEquals(1,inv.getRessources().get(0).getNombre());

    }

    @org.junit.jupiter.api.Test //la méthode selectItem est aussi testée ici
    void isSelectTest() throws Exception {
        Inventaire inv = new Inventaire();
        inv.addItem(new Pioche("testPioche", 0.5, 1));
        inv.addItem(new Epee("testEpee"));
        inv.selectItem(inv.getItems().get(1),false);
        Assertions.assertEquals(true, inv.isSelect());
    }

    @org.junit.jupiter.api.Test
    void isCraftTest() {
        Inventaire inv = new Inventaire();
        inv.craftItem(new Epee("épéeTest"));
        Assertions.assertEquals(true,inv.isCraft());

    }

    @org.junit.jupiter.api.Test
    void checkIdTest() throws Exception {
        Inventaire inv = new Inventaire();
        inv.addItem(new Epee("épéeTest"));
        inv.addItem(new Potion("potionTest"));
        inv.selectItem(inv.getItems().get(1),false);
        inv.removeItem();
        Assertions.assertEquals(1,inv.getItems().get(1).getId());
    }


    @org.junit.jupiter.api.Test
    void removeItemTest() throws Exception {
        Inventaire inv = new Inventaire();
        inv.addItem(new Epee("épéeTest"));
        inv.addItem(new Pioche("piocheTest",0.5,1));
        inv.selectItem(inv.getItems().get(0),false);
        inv.removeItem();
        Assertions.assertEquals(2,inv.getItems().size());
    }


    @org.junit.jupiter.api.Test
    void craftItemTest() {
        /*on craft plusieurs items et on verifie si la taille de la liste change*/
        Inventaire inv = new Inventaire();
        inv.addRessource(2);
        inv.craftItem(new Potion("potionTest"));
        Assertions.assertEquals(2,inv.getItems().size());
    }
}