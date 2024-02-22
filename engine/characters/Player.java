package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Pixel;
import engine.dungeon.Room;

public class Player extends GameCharacter {

    public Player(Room room, Pixel position) {
        super(room, position, "player", GameConfiguration.PLAYER_HEALTH);
    }
    
}
