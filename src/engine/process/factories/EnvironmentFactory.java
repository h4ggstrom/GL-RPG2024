package engine.process.factories;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.environment.Environment;

/**
 * Classe permettant de créer des entités de type Environment.
 * 
 * @see Environment
 */
public class EnvironmentFactory {
    
    /**
     * Crée une entité de type Environment.
     * 
     * @param entityType le type de l'entité
     * @param position la position de l'entité
     * @return l'entité créée
     */
    public static Environment createEnvironment(String entityType, Position position) {
        switch (entityType) {
            case GameConfiguration.TREE_ASSET_ENTITYTYPE :
                return new Environment(position, GameConfiguration.TREE_ASSET_NAME, GameConfiguration.TREE_ASSET_ENTITYTYPE);
            case GameConfiguration.WALL_ASSET_ENTITYTYPE :
                return new Environment(position, GameConfiguration.WALL_ASSET_NAME, GameConfiguration.WALL_ASSET_ENTITYTYPE);
            case GameConfiguration.GATE_ASSET_ENTITYTYPE :
                return new Environment(position, GameConfiguration.GATE_ASSET_NAME, GameConfiguration.GATE_ASSET_ENTITYTYPE);
            case GameConfiguration.PIPE_ASSET_ENTITYTYPE :
                return new Environment(position, GameConfiguration.PIPE_ASSET_NAME, GameConfiguration.PIPE_ASSET_ENTITYTYPE);
            case GameConfiguration.TABLE_ASSET_ENTITYTYPE :
                return new Environment(position, GameConfiguration.TABLE_ASSET_NAME, GameConfiguration.TABLE_ASSET_ENTITYTYPE);
            default:
                throw new IllegalArgumentException("Entité inconnue : " + entityType);
        }
    }

}
