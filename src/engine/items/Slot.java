package engine.items;

import engine.items.consumables.Consumable;
import engine.items.weapons.Weapon;

public class Slot {
    
    private Item item;

    public Slot(Item item) {
        this.item = item;
    }

    public Weapon getWeapon () {
        return (Weapon)item;
    }

    public Consumable getConsumable(){
        return  (Consumable)item;
    }
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
