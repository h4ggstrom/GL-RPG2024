package engine.entities.items;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.process.visitor.EntityVisitor;

public abstract class Item extends Entity {

    public Item(Position position, String nom, String entityType) {
        super(position, nom, entityType);
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}

}
