package engine.characters;

import engine.dungeon.Position;

public abstract class GameCharacter {

    private Position position;
    private Hitbox hitbox;
    private int health;

    public GameCharacter (Position position, String characterType, int health) {
        this.position = position;
        this.hitbox = new Hitbox(position, characterType, this);
        this.health = health;
    }

    public Position getPosition () {
        return this.position;
    }

    public void setPosition (Position position) {
        this.position = position;
    }

    public Hitbox getHitbox () {
        return this.hitbox;
    }

    public void setHitbox (Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "GameCharacter [position=" + position + ", hitbox=" + hitbox + ", health=" + health + "]";
    }
    
    
}
