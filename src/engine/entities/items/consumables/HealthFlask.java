package engine.entities.items.consumables;

import config.GameConfiguration;
import engine.dungeon.Position;

public class HealthFlask extends Consumable {

    public HealthFlask(Position position) {
        super(GameConfiguration.HEALTH_POINTS, "Health", "health", position);
    }
    
}
