package engine.entities.items.equipment;
import engine.dungeon.Position;

public class Pants extends Clothe {
    
    public Pants(int bonus, String pantsName, String pantsType, Position position) {
        super("Durée d'immobilisation", bonus, pantsName, pantsType, position);
    }

}
