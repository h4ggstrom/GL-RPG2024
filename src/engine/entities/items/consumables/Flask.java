package engine.entities.items.consumables;

import engine.dungeon.Position;

/**
 * Classe permettant de créer des entités de type Flask.
 * 
 * @see Consumable
 */
public class Flask extends Consumable {

    /**
     * Constructeur de la classe Flask.
     * 
     * @param effet
     * @param bonus
     * @param flaskName
     * @param flaskType
     * @param position
     */
    public Flask(String effet, int bonus, String flaskName, String flaskType, Position position) {
        super(effet, bonus, flaskName, flaskType, position);
    }
    
}
