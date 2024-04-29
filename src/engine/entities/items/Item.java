package engine.entities.items;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.process.visitor.EntityVisitor;

/**
 * Classe permettant de créer des entités de type Item.
 */
public abstract class Item extends Entity {

    /**
     * Constructeur de la classe Item.
     * 
     * @param position
     * @param nom
     * @param entityType
     */
    public Item(Position position, String nom, String entityType) {
        super(position, nom, entityType);
    }

    /**
     * Méthode permettant de récupérer le nom de l'entité.
     * 
     * @return le nom de l'entité
     */
    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}

}
