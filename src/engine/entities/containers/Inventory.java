package engine.entities.containers;

import config.GameConfiguration;
import engine.process.visitor.EntityVisitor;

/**
 * Classe permettant de créer des entités de type Inventory.
 * 
 * @see Container
 */
public class Inventory extends Container {
    
    /**
     * Constructeur de la classe Inventory.
     */
    public Inventory() {
        super(null, GameConfiguration.INVENTORY_NAME, GameConfiguration.INVENTORY_ENTITYTYPE, GameConfiguration.INVENTORY_MAX);
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}

}
