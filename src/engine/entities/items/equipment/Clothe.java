package engine.entities.items.equipment;

import engine.dungeon.Position;
import engine.entities.items.Item;

/**
 * Classe permettant de créer des entités de type Clothe.
 * 
 * @see Item
 */
public abstract class Clothe extends Item {
    
    String effect;
    int value;
    
    /**
     * Constructeur de la classe Clothe.
     * 
     * @param effect
     * @param value
     * @param armorName
     * @param armorType
     * @param position
     */
    public Clothe(String effect, int value, String armorName, String armorType, Position position) {
        super(position, armorName, armorType);
        this.effect = effect;
        this.value = value;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
