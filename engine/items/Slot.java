package engine.items;

public class Slot {
    
    private Item slotItem;

    public Slot(Item slotItem) {
        this.slotItem = slotItem;
    }

    public Item getSlotItem() {
        return slotItem;
    }

    public void setSlotItem(Item slotItem) {
        this.slotItem = slotItem;
    }

}
