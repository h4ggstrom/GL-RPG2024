package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Pixel;

public class Enemy extends GameCharacter {
    
    public Enemy (Pixel position) {
        super(position, "enemy", GameConfiguration.ENEMY_HEALTH);
    }

}
