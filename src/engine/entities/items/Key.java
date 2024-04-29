package engine.entities.items;

import config.GameConfiguration;
import engine.dungeon.Position;

/**
 * Classe permettant de créer des entités de type Key.
 * 
 * @see Item
 */
public class Key extends Item {
    
    /**
     * Constructeur de la classe Key.
     * 
     * @param position
     */
    public Key(Position position) {
        super(position, GameConfiguration.KEY_NAME, GameConfiguration.KEY_ENTITYTYPE);
    }

}
