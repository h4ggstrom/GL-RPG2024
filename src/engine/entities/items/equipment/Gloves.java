package engine.entities.items.equipment;
import engine.dungeon.Position;

/**
 * Classe permettant de créer des entités de type Gloves.
 * 
 * @see Clothe
 */
public class Gloves extends Clothe {
    
    /**
     * Constructeur de la classe Gloves.
     * 
     * @param bonus
     * @param glovesName
     * @param glovesType
     * @param position
     */
    public Gloves(int bonus, String glovesName, String glovesType, Position position) {
        super("Vitesse d'attaque", bonus, glovesName, glovesType, position);
    }
}
