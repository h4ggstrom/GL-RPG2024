package engine.entities.containers;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.process.visitor.EntityVisitor;

/**
 * Classe permettant de créer des entités de type Garbage.
 * 
 * @see Container
 */
public class Garbage extends Container {
    
    /**
     * Constructeur de la classe Garbage.
     * 
     * @param position
     */
    public Garbage (Position position) {
        super(position, GameConfiguration.GARBAGE_NAME, GameConfiguration.GARBAGE_ENTITYTYPE, GameConfiguration.GARBAGE_MAX);
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}

}
