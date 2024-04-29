package engine.entities.environment;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.process.visitor.EntityVisitor;

/**
 * Classe permettant de créer des entités de type Environment.
 * 
 * @see Entity
 */
public class Environment extends Entity {

    /**
     * Constructeur de la classe Environment.
     * 
     * @param position
     * @param environmentName
     * @param environmentType
     */
    public Environment(Position position, String environmentName, String environmentType) {
        super(position, environmentName, environmentType);
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}
    
}
