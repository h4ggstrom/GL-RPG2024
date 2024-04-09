package engine.process;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.characters.Enemy;
import engine.entities.items.weapons.*;
import engine.entities.items.consumables.*;
import engine.entities.items.containers.*;
import engine.entities.items.equipment.*;
import config.GameConfiguration;

/**
 * Cette classe permet la création d'entités de tout type concret.
 */
public class EntityFactory {

    public static Entity createEntity(String entityType, Position position) {
        switch (entityType) {
            case GameConfiguration.ENEMY_LABEL:
                return new Enemy(position);
            case GameConfiguration.SWORD_LABEL:
                return new Sword(position);
            case GameConfiguration.HEALTHFLASK_LABEL:
                return new HealthFlask(position);
            case GameConfiguration.HELMET_LABEL:
                return new Helmet(position);
            case GameConfiguration.GLOVES_LABEL:
                return new Gloves(position);
            case GameConfiguration.CHESTPLATE_LABEL:
                return new Chestplate(position);
            case GameConfiguration.PANTS_LABEL:
                return new Pants(position);
            case GameConfiguration.BOOTS_LABEL:
                return new Boots(position);
            case GameConfiguration.BAG_LABEL:
                return new Bag(position);
                case GameConfiguration.DAGGER_LABEL:
                return new Dagger(position);
            case GameConfiguration.WHIP_LABEL:
                return new Whip(position);
            case GameConfiguration.SCEPTER_LABEL:
                return new Scepter(position);
            case GameConfiguration.GRIMOIRE_LABEL:
                return new Grimoire(position);
            case GameConfiguration.BOW_LABEL:
                return new Bow(position);
            default:
                throw new IllegalArgumentException("Entité inconnue : " + entityType);
        }
    }
}
