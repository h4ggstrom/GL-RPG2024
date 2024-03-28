package engine.entities;

import engine.dungeon.Position;

public abstract class Entity {
    
    private Hitbox hitbox;

    public Entity (Position position, String entityType) {
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

}
