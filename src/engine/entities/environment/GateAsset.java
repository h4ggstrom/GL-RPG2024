package engine.entities.environment;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.Entity;

public class GateAsset extends Entity {
    
    public GateAsset(Position position) {
        super(position, GameConfiguration.GATE_ASSET_NAME, GameConfiguration.GATE_ASSET_ENTITYTYPE);
    }

}