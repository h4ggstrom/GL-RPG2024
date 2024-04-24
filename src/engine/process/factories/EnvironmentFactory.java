package engine.process.factories;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.environment.Environment;

public class EnvironmentFactory {
    
    public static Environment createEnvironment(String entityType, Position position) {
        switch (entityType) {
            case GameConfiguration.TREE_ASSET_ENTITYTYPE :
                return new Environment(position, GameConfiguration.TREE_ASSET_NAME, GameConfiguration.TREE_ASSET_ENTITYTYPE);
            case GameConfiguration.WALL_ASSET_ENTITYTYPE :
                return new Environment(position, GameConfiguration.WALL_ASSET_NAME, GameConfiguration.WALL_ASSET_ENTITYTYPE);
            case GameConfiguration.GATE_ASSET_ENTITYTYPE :
                return new Environment(position, GameConfiguration.GATE_ASSET_NAME, GameConfiguration.GATE_ASSET_ENTITYTYPE);
            default:
                throw new IllegalArgumentException("Entité inconnue : " + entityType);
        }
    }

}
