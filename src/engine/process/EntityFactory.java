package engine.process;

import engine.Entity;
import engine.characters.Enemy;
import engine.dungeon.Position;
import engine.items.weapons.Sword;

/**
 * Cette classe permet la création d'entités de tout type concret.
 */
public class EntityFactory {
    private static final String ENEMY_LABEL = "enemy";
    private static final String SWORD_LABEL = "sword";

    public static Entity createEntity(String entityType, Position position) {
        switch (entityType) {
            case ENEMY_LABEL:
                return new Enemy(position);
            case SWORD_LABEL:
                return new Sword(position);
            default:
                throw new IllegalArgumentException("Entité inconnue : " + entityType);
        }
    }
}
