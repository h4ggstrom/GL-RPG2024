package engine.entities.containers;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.process.visitor.EntityVisitor;

/**
 * Classe permettant de créer des entités de type Chest.
 * 
 * @see Container
 */
public class Chest extends Container {

    private boolean isLocked = true;

    /**
     * Constructeur de la classe Chest.
     * 
     * @param position
     */
    public Chest (Position position) {
        super(position, GameConfiguration.CHEST_NAME, GameConfiguration.CHEST_ENTITYTYPE, GameConfiguration.CHEST_MAX);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}

}
