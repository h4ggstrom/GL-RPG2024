package engine.entities.items;

import engine.dungeon.Position;
import engine.entities.Entity;

public abstract class Item extends Entity {

    public Item(Position position, String nom, String entityType) {
        super(position, nom, entityType);
    }

}
