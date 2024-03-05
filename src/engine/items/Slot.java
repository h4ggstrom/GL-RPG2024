package engine.items;

public class Slot {
    
    private Item item;

    public Slot(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
