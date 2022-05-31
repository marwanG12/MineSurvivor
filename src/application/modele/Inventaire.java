package application.modele;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Item> items;
    private ArrayList<Ressource> ressources;
    private Ressource pierre;
    private Ressource bois;
    private Ressource fer;
    private Ressource fil;
    private Ressource monnaie;


    public Inventaire(){
        items=new ArrayList<Item>(7);
        ressources=new ArrayList<Ressource>(6);
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void initialize() {
        pierre = new Ressource("Pierre", 0);
        bois = new Ressource("Bois", 0);
        fer = new Ressource("Fer", 0);
        fil = new Ressource("Fil", 0);
        monnaie = new Ressource("Monnaie", 0);
        ressources.add(pierre);
        ressources.add(bois);
        ressources.add(fer);
        ressources.add(fil);
        ressources.add(monnaie);
    }

    public void craft(String item) {
    }

    public void addRessource(Ressource ressouce){
        ressources.add(ressouce);
    }

    public void removeItem(Item item){
        for (int i=0; i<items.size(); i++){
            if(items.get(i).getId()== item.getId())
                items.remove(i);
        }
    }
}