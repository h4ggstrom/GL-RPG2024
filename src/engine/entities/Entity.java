package engine.entities;

import java.io.Serializable;

import engine.dungeon.Position;
import engine.process.visitor.EntityVisitor;

/**
 * Classe abstraite représentant une entité du jeu.
 * 
 */
public abstract class Entity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Hitbox hitbox;
    private String entityName;
    private String entityType;
    public abstract <E> E accept(EntityVisitor<E> visitor);

    /**
     * Constructeur par défaut. Génère une nouvelle instance d'entité.
     * 
     * @param position la position de l'entité
     * @param entityName le nom de l'entité
     * @param entityType le type de l'entité
     */
    public Entity (Position position, String entityName, String entityType) {
        this.entityName = entityName;
        this.entityType = entityType;
        this.hitbox = new Hitbox(position, entityType, this);
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public Position getPosition() {
        return hitbox.getCenter();
    }

    public void setPosition (Position position) {
        this.hitbox.setPosition(position);
    }

        public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

}
