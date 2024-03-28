package engine.entities.items;

import java.util.ArrayList;

public class Inventory {
    
    private ArrayList<Slot> slots;

    public Inventory () {
        this.slots = new ArrayList<Slot>();
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<Slot> slots) {
        this.slots = slots;
    }

    public void addItem(Item item) {
        slots.add(new Slot(item));
    }

    public int getNumberOfItems() {
        return slots.size();
    }

}
