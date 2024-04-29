package engine.entities.items.equipment;
import engine.dungeon.Position;

/**
 * Classe permettant de créer des entités de type Pants.
 * 
 * @see Clothe
 */
public class Pants extends Clothe {
    
    /**
     * Constructeur de la classe Pants.
     * 
     * @param bonus
     * @param pantsName
     * @param pantsType
     * @param position
     */
    public Pants(int bonus, String pantsName, String pantsType, Position position) {
        super("Durée d'immobilisation", bonus, pantsName, pantsType, position);
    }

}
