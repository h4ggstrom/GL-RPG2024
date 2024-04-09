package engine.entities.items.weapons;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Scepter extends Weapon {

    int burnDamage;

    public Scepter(Position position) {
        super(GameConfiguration.SCEPTER_DAMAGE, GameConfiguration.SCEPTER_RANGE, "scepter", position);
        this.burnDamage = GameConfiguration.SCEPTER_BURN_DAMAGE;
    }

    public int getBurnDamage() {
        return burnDamage;
    }

    public void setBurnDamage(int burnDamage) {
        this.burnDamage = burnDamage;
    }

}