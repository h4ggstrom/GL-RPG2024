package engine.entities.items.equipment;
import engine.dungeon.Position;

public class Chestplate extends Clothe {
    
    public Chestplate(int bonus, String chestplateName, String chestplateType, Position position) {
        super("Armure", bonus, chestplateName, chestplateType, position);
    }

}
