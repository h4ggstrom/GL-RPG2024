package engine.entities.items.armor;
import config.GameConfiguration;
import engine.dungeon.Position;

public class Gloves extends Armor {
    
    public Gloves(Position position) {
        super(GameConfiguration.GLOVES_EFFECT, GameConfiguration.GLOVES_BONUS, GameConfiguration.GLOVES_NAME, GameConfiguration.GLOVES_ENTITYTYPE, position);
    }
 
}
