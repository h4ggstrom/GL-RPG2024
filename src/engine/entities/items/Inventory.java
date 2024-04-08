package engine.entities.items;

import java.util.ArrayList;

import config.GameConfiguration;

public class Inventory {
    
    private ArrayList<Slot> slots;
    private int numberOfItems;

    public Inventory () {
        this.slots = new ArrayList<Slot>();
        this.numberOfItems = 0;
        for(int i = 1 ; i <= GameConfiguration.INVENTORY_MAX ; i++) {
            slots.add(new Slot());
        }
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<Slot> slots) {
        this.slots = slots;
    }

    /**
     * Cette méthode permet d'ajouter un item dans le prochain slot non rempli
     * @param item
     */
    public void addItem(Item item) {
        slots.get(numberOfItems).setItem(item);
        numberOfItems++;
    }

    /**
     * Cette méthode permet de retirer l'item du slot correspondant
     * @param slotNumber le numéro du Slot à vider
     */
    public void removeItem(int slotNumber) {
        slots.get(slotNumber).setItem(null);;
        numberOfItems--;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

}
