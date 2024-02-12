package engine.characters;

import engine.dungeon.Block;

public abstract class Character {
    private Block position;

    public Character(Block position) {
		position.occupy();
		this.position = position;
	}

	public Block getPosition() {
		return position;
	}

	public void setPosition(Block position) {
		this.position = position;
	}
}
