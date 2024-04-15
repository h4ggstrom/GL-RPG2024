package engine.entities.environment;

import config.GameConfiguration;
import engine.dungeon.Position;

public class GateEnv extends Environment {
    
    public GateEnv(Position position) {
        super(position, GameConfiguration.GATE_ASSET_NAME, GameConfiguration.GATE_ASSET_ENTITYTYPE);
    }

}