package application.modele;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Item> items;
    private ArrayList<Ressource> ressources;

    public Inventaire(){
        items=new ArrayList<Item>(7);
        ressources=new ArrayList<Ressource>();
    }

    public void addItem(Item item){
        items.add(item);
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