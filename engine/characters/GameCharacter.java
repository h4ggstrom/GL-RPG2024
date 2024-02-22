package engine.characters;

import engine.dungeon.Pixel;
import engine.dungeon.Room;

public abstract class GameCharacter {

    private Pixel position;
    private Hitbox hitbox;
    private int health;

    public GameCharacter (Room room, Pixel position, String characterType, int health) {
        this.position = position;
        this.hitbox = new Hitbox(room, position, characterType, this);
        this.health = health;
    }

    public Pixel getPosition () {
        return this.position;
    }

    public void setPosition (Pixel position) {
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
