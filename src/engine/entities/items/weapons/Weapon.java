package engine.entities.items.weapons;

import engine.dungeon.Position;
import engine.entities.items.Item;

public abstract class Weapon extends Item {
    
    int attackDamage;
    int attackRange;
    
    public Weapon(int attackDamage, int attackRange, String weaponType, Position position) {
        super(position, weaponType);
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

}
