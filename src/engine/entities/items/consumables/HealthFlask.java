package engine.entities.items.consumables;

import config.GameConfiguration;
import engine.dungeon.Position;

public class HealthFlask extends Consumable {

    public HealthFlask(Position position) {
        super(GameConfiguration.HEALTHFLASK_HEALPOINTS, "heal", "healthFlask", position);
    }
    
}
