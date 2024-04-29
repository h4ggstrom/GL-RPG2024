package engine.entities.items;

import java.io.Serializable;

import engine.entities.items.consumables.Consumable;
import engine.entities.items.weapons.Weapon;

/**
 * Classe représentant un emplacement d'inventaire
 */
public class Slot implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Item item;

    /**
     * Ce constructeur instancie un Slot vide
     */
    public Slot() {
        this.item = null;
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
