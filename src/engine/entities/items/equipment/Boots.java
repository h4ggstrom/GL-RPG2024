package engine.entities.items.equipment;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Boots extends Clothe {
    
    public Boots(Position position) {
        super(GameConfiguration.BOOTS_EFFECT, GameConfiguration.BOOTS_BONUS, GameConfiguration.BOOTS_NAME, GameConfiguration.BOOTS_ENTITYTYPE, position);
    }

}
