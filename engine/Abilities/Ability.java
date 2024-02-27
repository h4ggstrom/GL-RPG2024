package engine.Abilities;

import engine.characters.GameCharacter;
import engine.dungeon.Position;

public class Ability {
    
    private GameCharacter caster;
    private int damage;
    private int range;
    private Position target;

    public Ability(GameCharacter caster, int damage, int range, Position target) {
        this.caster = caster;
        this.damage = damage;
        this.range = range;
        this.target = target;
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

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }
  
}
