package engine.entities.environment;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.process.visitor.EntityVisitor;

public class Environment extends Entity {

    public Environment(Position position, String environmentName, String environmentType) {
        super(position, environmentName, environmentType);
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}
    
}
