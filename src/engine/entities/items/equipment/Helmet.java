package engine.entities.items.equipment;
import engine.dungeon.Position;

/**
 * Classe permettant de créer des entités de type Helmet.
 * 
 * @see Clothe
 */
public class Helmet extends Clothe {
    
    /**
     * Constructeur de la classe Helmet.
     * 
     * @param bonus
     * @param helmetName
     * @param helmetType
     * @param position
     */
    public Helmet(int bonus, String helmetName, String helmetType, Position position) {
        super("Régénération d'abilité", bonus, helmetName, helmetType, position);
    }
 
}
