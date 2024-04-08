package engine.entities.items.armor;

import engine.dungeon.Position;
import engine.entities.items.Item;

public abstract class Armor extends Item {
    
    String effect;
    int value;
    
    public Armor(String effect, int value, String armorType, Position position) {
        super(position, armorType);
        this.effect = effect;
        this.value = value;
    }

    public String geteffect() {
        return effect;
    }

    public void seteffect(String effect) {
        this.effect = effect;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
