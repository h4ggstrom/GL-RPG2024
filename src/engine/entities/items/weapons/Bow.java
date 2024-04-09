package engine.entities.items.weapons;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Bow extends Weapon {

    int dmgOverRange;

    public Bow(Position position) {
        super(GameConfiguration.BOW_DAMAGE, GameConfiguration.BOW_SPEED, GameConfiguration.BOW_RANGE, "bow", position);
        this.dmgOverRange = GameConfiguration.BOW_DAMAGE_OVER_RANGE;
    }

    public int getDmgOverRange() {
        return dmgOverRange;
    }

    public void setDmgOverRange(int dmgOverRange) {
        this.dmgOverRange = dmgOverRange;
    }

}