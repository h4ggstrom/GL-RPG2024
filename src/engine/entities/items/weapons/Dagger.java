package engine.entities.items.weapons;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Dagger extends Weapon {

    int bleedDamage;

    public Dagger(Position position) {
        super(GameConfiguration.DAGGER_DAMAGE, GameConfiguration.DAGGER_RANGE, GameConfiguration.DAGGER_NAME, GameConfiguration.DAGGER_ENTITYTYPE, position);
        this.bleedDamage = GameConfiguration.DAGGER_BLEED_DAMAGE;
    }

    public int getBleedDamage() {
        return bleedDamage;
    }

    public void setBleedDamage(int bleedDamage) {
        this.bleedDamage = bleedDamage;
    }
}