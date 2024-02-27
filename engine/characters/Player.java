package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Player extends GameCharacter {
  
    public Player( Position position) {
        super(position, "player", GameConfiguration.PLAYER_HEALTH);
    }
    
}
