package engine.entities.environment;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.Entity;

public class TreeAsset extends Entity {
    public TreeAsset(Position position) {
        super(position, GameConfiguration.TREE_ASSET_NAME, GameConfiguration.TREE_ASSET_ENTITYTYPE);
    }
}

