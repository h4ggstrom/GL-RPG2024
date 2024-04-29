package engine.entities.items.equipment;
import engine.dungeon.Position;

public class Gloves extends Clothe {
    
    public Gloves(int bonus, String glovesName, String glovesType, Position position) {
        super("Vitesse d'attaque", bonus, glovesName, glovesType, position);
    }
}
