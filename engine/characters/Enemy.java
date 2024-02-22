package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Pixel;
import engine.dungeon.Room;

public class Enemy extends GameCharacter {
    
    public Enemy (Room room, Pixel position) {
        super(room, position, "enemy", GameConfiguration.ENEMY_HEALTH);
    }

}
