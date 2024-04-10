package engine.entities.items.armor;
import config.GameConfiguration;
import engine.dungeon.Position;

public class Helmet extends Armor {
    
    public Helmet(Position position) {
        super(GameConfiguration.HELMET_EFFECT, GameConfiguration.HELMET_BONUS, GameConfiguration.HELMET_NAME, GameConfiguration.HELMET_ENTITYTYPE, position);
    }
 
}
