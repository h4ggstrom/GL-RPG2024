package engine.entities.environment;

import engine.dungeon.Position;
import engine.entities.Entity;

public abstract class Environment extends Entity {

    public Environment(Position position, String environmentName, String environmentType) {
        super(position, environmentName, environmentType);
    }
    
}
