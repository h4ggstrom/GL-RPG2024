package engine.entities.items.equipment;
import config.GameConfiguration;
import engine.dungeon.Position;

public class Chestplate extends Clothe {
    
    public Chestplate(Position position) {
        super(GameConfiguration.CHESTPLATE_EFFECT, GameConfiguration.CHESTPLATE_BONUS, GameConfiguration.CHESTPLATE_NAME, GameConfiguration.CHESTPLATE_ENTITYTYPE, position);
    }

}
