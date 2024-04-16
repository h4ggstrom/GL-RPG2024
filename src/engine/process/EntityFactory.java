package engine.process;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.environment.TreeEnv;
import engine.entities.items.weapons.*;
import engine.entities.items.consumables.*;
import engine.entities.containers.*;
import engine.entities.items.equipment.*;

import config.GameConfiguration;

/**
 * Cette classe permet la création d'entités de tout type concret.
 */
public class EntityFactory {

    public static Entity createEntity(String entityType, Position position) {
        switch (entityType) {
            case GameConfiguration.SWORD_ENTITYTYPE:
                return new Sword(position);
            case GameConfiguration.HEALTHFLASK_ENTITYTYPE:
                return new HealthFlask(position);
            case GameConfiguration.HELMET_ENTITYTYPE:
                return new Helmet(position);
            case GameConfiguration.GLOVES_ENTITYTYPE:
                return new Gloves(position);
            case GameConfiguration.CHESTPLATE_ENTITYTYPE:
                return new Chestplate(position);
            case GameConfiguration.PANTS_ENTITYTYPE:
                return new Pants(position);
            case GameConfiguration.BOOTS_ENTITYTYPE:
                return new Boots(position);
            case GameConfiguration.BAG_ENTITYTYPE:
                return new Bag(position);
            case GameConfiguration.DAGGER_ENTITYTYPE:
                return new Dagger(position);
            case GameConfiguration.WHIP_ENTITYTYPE:
                return new Whip(position);
            case GameConfiguration.SCEPTER_ENTITYTYPE:
                return new Scepter(position);
            case GameConfiguration.GRIMOIRE_ENTITYTYPE:
                return new Grimoire(position);
            case GameConfiguration.BOW_ENTITYTYPE:
                return new Bow(position);
            case GameConfiguration.COIN_ENTITYTYPE:
                return new Coin(position);
            case GameConfiguration.TREE_ASSET_ENTITYTYPE:
                return new TreeEnv(position);
            default:
                throw new IllegalArgumentException("Entité inconnue : " + entityType);
        }
    }
}
