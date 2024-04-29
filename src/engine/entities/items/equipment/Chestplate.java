package engine.entities.items.equipment;
import engine.dungeon.Position;

/**
 * Classe permettant de créer des entités de type Chestplate.
 * 
 * @see Clothe
 */
public class Chestplate extends Clothe {
    
    /**
     * Constructeur de la classe Chestplate.
     * 
     * @param bonus
     * @param chestplateName
     * @param chestplateType
     * @param position
     */
    public Chestplate(int bonus, String chestplateName, String chestplateType, Position position) {
        super("Armure", bonus, chestplateName, chestplateType, position);
    }

}
