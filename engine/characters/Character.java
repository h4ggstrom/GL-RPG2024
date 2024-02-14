package engine.characters;

import engine.dungeon.Pixel;

public abstract class Character {

    private Pixel position;

    public Character (Pixel position) {
        this.position = position;
    }

    public Pixel getPosition () {
        return this.position;
    }

    public void setPosition (Pixel position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Character [position=" + position + "]";
    }
    
}
