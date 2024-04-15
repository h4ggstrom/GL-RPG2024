package engine.entities.environment;

import config.GameConfiguration;
import engine.dungeon.Position;

public class WallEnv extends Environment {
    
    public WallEnv(Position position) {
        super(position, GameConfiguration.WALL_ASSET_NAME, GameConfiguration.WALL_ASSET_ENTITYTYPE);
    }

}
