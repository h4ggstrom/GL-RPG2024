package engine.entities.items.consumables;

import engine.dungeon.Position;
import engine.entities.items.Item;

public abstract class Consumable extends Item {
    
    int uses;
    String effect;
    
    public Consumable(int uses, String effect, String consumableType, Position position) {
        super(position, consumableType);
        this.uses = uses;
        this.effect = effect;
    }

    public int getConsumableUses() {
        return uses;
    }

    public void setConsumableUse(int uses) {
        this.uses = uses;
    }

    public String getConsumableEffet(){
        return effect;
    }

    public  void setConsumableEffect(String effect){
        this.effect = effect;
    }

}
