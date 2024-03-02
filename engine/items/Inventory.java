package engine.items;

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

}
