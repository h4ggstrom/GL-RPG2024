package engine.entities.items.armor;
import config.GameConfiguration;
import engine.dungeon.Position;

public class Pants extends Armor {
    
    public Pants(Position position) {
        super(GameConfiguration.PANTS_EFFECT, GameConfiguration.PANTS_BONUS, GameConfiguration.PANTS_NAME, GameConfiguration.PANTS_ENTITYTYPE, position);
    }

}
