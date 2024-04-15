package engine.entities.environment;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.Entity;

public class WallAsset extends Entity {
    
    public WallAsset(Position position) {
        super(position, GameConfiguration.WALL_ASSET_NAME, GameConfiguration.WALL_ASSET_ENTITYTYPE);
    }

}
