package engine.process;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.characters.Enemy;
import engine.entities.items.consumables.Health;
import engine.entities.items.weapons.Sword;

/**
 * Cette classe permet la création d'entités de tout type concret.
 */
public class EntityFactory {
    private static final String ENEMY_LABEL = "enemy";
    private static final String SWORD_LABEL = "sword";
    private static final String HEALTH_FLASK_LABEL = "health";

    public static Entity createEntity(String entityType, Position position) {
        switch (entityType) {
            case ENEMY_LABEL:
                return new Enemy(position);
            case SWORD_LABEL:
                return new Sword(position);
            case HEALTH_FLASK_LABEL:
                return new Health(position);
            default:
                throw new IllegalArgumentException("Entité inconnue : " + entityType);
        }
    }
}
