package engine.entities.items;

import engine.dungeon.Position;
import engine.entities.Entity;

public abstract class Item extends Entity {
    
    public Item(Position position, String entityType) {
        super(position, entityType);
    }

}
