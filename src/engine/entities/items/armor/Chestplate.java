package engine.entities.items.armor;
import config.GameConfiguration;
import engine.dungeon.Position;

public class Chestplate extends Armor {
    
    public Chestplate(Position position) {
        super(GameConfiguration.CHESTPLATE_EFFECT, GameConfiguration.CHESTPLATE_BONUS, GameConfiguration.CHESTPLATE_NAME, GameConfiguration.CHESTPLATE_ENTITYTYPE, position);
    }

}
