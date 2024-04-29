package engine.entities.items.equipment;
import engine.dungeon.Position;

public class Helmet extends Clothe {
    
    public Helmet(int bonus, String helmetName, String helmetType, Position position) {
        super("Régénération d'abilité", bonus, helmetName, helmetType, position);
    }
 
}
