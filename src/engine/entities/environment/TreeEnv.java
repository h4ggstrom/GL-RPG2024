package engine.entities.environment;

import config.GameConfiguration;
import engine.dungeon.Position;

public class TreeEnv extends Environment {
    public TreeEnv(Position position) {
        super(position, GameConfiguration.TREE_ASSET_NAME, GameConfiguration.TREE_ASSET_ENTITYTYPE);
    }
}

