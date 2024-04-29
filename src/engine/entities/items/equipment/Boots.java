package engine.entities.items.equipment;

import engine.dungeon.Position;

/**
 * Classe permettant de créer des entités de type Boots.
 * 
 * @see Clothe
 */
public class Boots extends Clothe {
    
    /**
     * Constructeur de la classe Boots.
     * 
     * @param bonus
     * @param bootsName
     * @param bootsType
     * @param position
     */
    public Boots(int bonus, String bootsName, String bootsType, Position position) {
        super("Vitesse de déplacement", bonus, bootsName, bootsType, position);
    }

}
