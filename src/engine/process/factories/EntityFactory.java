package engine.process.factories;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.npc.Vendor;
import engine.entities.items.Key;
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
            case GameConfiguration.COIN_ENTITYTYPE:
                return new Coin(position);
            case GameConfiguration.CHEST_ENTITYTYPE:
                return new Chest(position);
            case GameConfiguration.KEY_ENTITYTYPE:
                return new Key(position);
            case GameConfiguration.GARBAGE_ENTITYTYPE:
                return new Garbage(position);
            case GameConfiguration.VENDOR_ENTITYTYPE:
                return new Vendor(position);
            default:
                throw new IllegalArgumentException("Entité inconnue : " + entityType);
        }
    }
}
