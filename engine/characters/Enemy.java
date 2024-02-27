package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Enemy extends GameCharacter {
    
    public Enemy (Position position) {
        super(position, "enemy", GameConfiguration.ENEMY_HEALTH);
    }
}
