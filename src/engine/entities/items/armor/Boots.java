package engine.entities.items.armor;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Boots extends Armor {
    
    public Boots(Position position) {
        super(GameConfiguration.BOOTS_EFFECT, GameConfiguration.BOOTS_BONUS, GameConfiguration.BOOTS_LABEL, position);
    }

}
