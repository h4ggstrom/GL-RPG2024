package engine.items.weapons;

import config.GameConfiguration;

public class Sword extends Weapon {

    public Sword() {
        super(GameConfiguration.SWORD_DAMAGE, GameConfiguration.SWORD_SPEED, GameConfiguration.SWORD_RANGE);
    }
    
}
