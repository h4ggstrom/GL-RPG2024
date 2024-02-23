package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Pixel;

public class Player extends GameCharacter {

    public Player( Pixel position) {
        super(position, "player", GameConfiguration.PLAYER_HEALTH);
    }
    
}
