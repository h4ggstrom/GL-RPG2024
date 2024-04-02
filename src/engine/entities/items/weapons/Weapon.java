package engine.entities.items.weapons;

import engine.dungeon.Position;
import engine.entities.items.Item;

public abstract class Weapon extends Item {
    
    int attackDamage;
    int attackSpeed;
    int attackRange;
    
    public Weapon(int attackDamage, int attackSpeed, int attackRange, String weaponType, Position position) {
        super(position, weaponType);
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

}
