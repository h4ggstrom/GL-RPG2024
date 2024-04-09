package engine.entities.items.equipment;
import config.GameConfiguration;
import engine.dungeon.Position;

public class Helmet extends Clothe {
    
    public Helmet(Position position) {
        super(GameConfiguration.HELMET_EFFECT, GameConfiguration.HELMET_BONUS, GameConfiguration.HELMET_LABEL, position);
    }
 
}
