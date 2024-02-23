package engine.Abilities;

import engine.characters.GameCharacter;
import engine.dungeon.Pixel;

public class Ability {
    
    private GameCharacter caster;
    private int damage;
    private int range;
    private Pixel direction;

    public Ability(GameCharacter caster, int damage, int range, Pixel direction) {
        this.caster = caster;
        this.damage = damage;
        this.range = range;
        this.direction = direction;
    }

    public GameCharacter getCaster() {
        return caster;
    }

    public void setCaster(GameCharacter caster) {
        this.caster = caster;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Pixel getDirection() {
        return direction;
    }

    public void setDirection(Pixel direction) {
        this.direction = direction;
    }

}
