package engine.items;

import engine.items.weapons.Weapon;

public class Slot {
    
    private Item item;

    public Slot(Item item) {
        this.item = item;
    }

    public Weapon getWeapon () {
        return (Weapon)item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
