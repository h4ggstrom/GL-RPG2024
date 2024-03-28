package engine.items;

import engine.Entity;
import engine.dungeon.Position;

public abstract class Item extends Entity {
    
    public Item(Position position, String entityType) {
        super(position, entityType);
    }

}
