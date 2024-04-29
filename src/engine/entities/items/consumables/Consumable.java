package engine.entities.items.consumables;

import engine.dungeon.Position;
import engine.entities.items.Item;

/**
 * Classe permettant de créer des entités de type Consumable.
 * 
 * @see Item
 */
public abstract class Consumable extends Item {
    
    String effect;
    int value;
    
    /**
     * Constructeur de la classe Consumable.
     * 
     * @param effect
     * @param value
     * @param consumableName
     * @param consumableType
     * @param position
     */
    public Consumable(String effect, int value, String consumableName, String consumableType, Position position) {
        super(position, consumableName, consumableType);
        this.value = value;
        this.effect = effect;
    }

    public int getConsumableValue() {
        return value;
    }

    public void setConsumableValue(int value) {
        this.value = value;
    }

    public String getConsumableEffect(){
        return effect;
    }

    public  void setConsumableEffect(String effect){
        this.effect = effect;
    }

}
