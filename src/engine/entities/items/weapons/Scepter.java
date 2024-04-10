package engine.entities.items.weapons;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Scepter extends Weapon {

    int burnDamage;

    public Scepter(Position position) {
        super(GameConfiguration.SCEPTER_DAMAGE, GameConfiguration.SCEPTER_RANGE,  GameConfiguration.SCEPTER_NAME, GameConfiguration.SCEPTER_ENTITYTYPE, position);
        this.burnDamage = GameConfiguration.SCEPTER_BURN_DAMAGE;
    }

    public int getBurnDamage() {
        return burnDamage;
    }

    public void setBurnDamage(int burnDamage) {
        this.burnDamage = burnDamage;
    }

}