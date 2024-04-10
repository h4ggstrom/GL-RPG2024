package engine.entities.items.weapons;

import config.GameConfiguration;
import engine.dungeon.Position;

public class Grimoire extends Weapon {

    int rootDuration;

    public Grimoire(Position position) {
        super(GameConfiguration.GRIMOIRE_DAMAGE, GameConfiguration.GRIMOIRE_RANGE, GameConfiguration.GRIMOIRE_NAME, GameConfiguration.GRIMOIRE_ENTITYTYPE, position);
        this.rootDuration = GameConfiguration.GRIMOIRE_ROOT_DURATION;
    }

    public int getRootDuration() {
        return rootDuration;
    }

    public void setRootDuration(int rootDuration) {
        this.rootDuration = rootDuration;
    }

}