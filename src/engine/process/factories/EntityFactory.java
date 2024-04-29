package engine.process.factories;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.npc.Vendor;
import engine.entities.items.Key;
import engine.entities.items.consumables.*;
import engine.entities.containers.*;

import config.GameConfiguration;

/**
 * Cette classe permet la création d'entités de tout type concret.
 */
public class EntityFactory {

    public static Entity createEntity(String entityType, Position position) {
        switch (entityType) {
            case "potion_de_sante":
                return new Flask("Régénération", 50, "Potion de santé", "potion_de_sante", position);
            case "potion_de_mana":
                return new Flask("Mana", 5000, "Potion de mana", "potion_de_mana", position);
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
