package engine.entities.items.equipment;

import engine.dungeon.Position;

public class Boots extends Clothe {
    
    public Boots(int bonus, String bootsName, String bootsType, Position position) {
        super("Vitesse de déplacement", bonus, bootsName, bootsType, position);
    }

}
