package engine.entities.items.weapons;

import engine.dungeon.Position;
import engine.entities.items.Item;

public class Weapon extends Item {
    
    int attackDamage;
    int attackRange;
    
    public Weapon(int attackDamage, int attackRange, String weaponName, String weaponType, Position position) {
        super(position, weaponName, weaponType);
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
