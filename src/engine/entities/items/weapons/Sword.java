package engine.entities.items.weapons;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Sword extends Weapon {

    public Sword(Position position) {
        super(GameConfiguration.SWORD_DAMAGE, GameConfiguration.SWORD_SPEED, GameConfiguration.SWORD_RANGE, "sword", position);
    }
    
}
