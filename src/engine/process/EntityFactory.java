package engine.process;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.characters.Enemy;
import engine.entities.items.armor.Boots;
import engine.entities.items.armor.Chestplate;
import engine.entities.items.armor.Gloves;
import engine.entities.items.armor.Helmet;
import engine.entities.items.armor.Pants;
import engine.entities.items.consumables.HealthFlask;
import engine.entities.items.weapons.Bow;
import engine.entities.items.weapons.Dagger;
import engine.entities.items.weapons.Grimoire;
import engine.entities.items.weapons.Scepter;
import engine.entities.items.weapons.Sword;
import engine.entities.items.weapons.Whip;

/**
 * Cette classe permet la création d'entités de tout type concret.
 */
public class EntityFactory {
    private static final String ENEMY_LABEL = "enemy";
    private static final String SWORD_LABEL = "sword";

    private static final String HEALTH_FLASK_LABEL = "healthFlask";

    public static Entity createEntity(String entityType, Position position) {
        switch (entityType) {
            case GameConfiguration.ENEMY_LABEL:
                return new Enemy(position);
            case GameConfiguration.SWORD_LABEL:
                return new Sword(position);
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
            case HEALTH_FLASK_LABEL:
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
            default:
                throw new IllegalArgumentException("Entité inconnue : " + entityType);
        }
    }
}
