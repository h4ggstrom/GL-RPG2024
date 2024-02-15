package engine.characters;

import engine.dungeon.Pixel;
import engine.dungeon.Room;

public abstract class GameCharacter {

    private Pixel position;
    private Hitbox hitbox;

    public GameCharacter (Room room, Pixel position) {
        this.position = position;
        this.hitbox = new Hitbox(room, position);
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

    @Override
    public String toString() {
        return "Character [position=" + position + "]";
    }
    
}
