package engine.entities.items.weapons;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Whip extends Weapon {

    int areaOfEffectDamage;

    public Whip(Position position) {
        super(GameConfiguration.WHIP_DAMAGE, GameConfiguration.WHIP_SPEED, GameConfiguration.WHIP_RANGE, "whip", position);
        this.areaOfEffectDamage = GameConfiguration.WHIP_AREA_DAMAGE;

    }

    public int getAreaOfEffectDamage() {
        return areaOfEffectDamage;
    }

    public void setAreaOfEffectDamage(int areaOfEffectDamage) {
        this.areaOfEffectDamage = areaOfEffectDamage;
    }

}