package engine.entities.items.containers;

import java.util.ArrayList;

import engine.dungeon.Position;
import engine.entities.items.Item;
import engine.entities.items.Slot;

public class Container extends Item {

    private ArrayList<Slot> slots;
    private int numberOfItems;
    private int maxNumberOfItems;

    public Container (Position position, String containerType, int numberOfSlots) {
        super(position, containerType);
        this.slots = new ArrayList<Slot>();
        this.numberOfItems = 0;
        this.maxNumberOfItems = numberOfSlots;
        for(int i = 1 ; i <= numberOfSlots ; i++) {
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
        // Si le conteneur n'est pas rempli et que l'item fourni n'est pas nul
        if( (numberOfItems < maxNumberOfItems) && (item != null)) {
            // On parcourt les Slot du conteneur
            for(Slot slot : slots) {
                // Si le Slot est vide
                if(slot.getItem() == null) {
                    // On ajoute l'Item au Slot
                    slot.setItem(item);
                    // On incrémente notre nombre d'items
                    this.numberOfItems++;
                    // On sort de la boucle
                    break;
                }
            }
        }
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
