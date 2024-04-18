package engine.entities.items;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Key extends Item {
    
    public Key(Position position) {
        super(position, GameConfiguration.KEY_NAME, GameConfiguration.KEY_ENTITYTYPE);
    }

}
