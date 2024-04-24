package engine.entities.containers;

import config.GameConfiguration;
import engine.process.visitor.EntityVisitor;

public class Inventory extends Container {
    
    public Inventory() {
        super(null, GameConfiguration.INVENTORY_NAME, GameConfiguration.INVENTORY_ENTITYTYPE, GameConfiguration.INVENTORY_MAX);
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}

}
