package engine.process.factories;

import engine.dungeon.Position;
import engine.entities.characters.Enemy;

/**
 * Cette classe permet la création des multiples type d'ennemis
 * Les vitesses (sauf déplacement) sont exprimées en tick (10 millisecondes)
 */
public class EnemyFactory {

    public static Enemy createEnemy(String enemyType, Position position) {
        switch (enemyType) {
            case "rat_fistule":
                Enemy ratFistuled = new Enemy(position, "Rat fistulé", "rat_fistule");
                ratFistuled.setMaxHealth(15);
                ratFistuled.setHealth(15);
                ratFistuled.setArmor(1);
                ratFistuled.setAttackDamage(2);
                ratFistuled.setAttackRange(30);
                ratFistuled.setAttackSpeed(100);
                ratFistuled.setMoveSpeed(1);
                ratFistuled.setAbilityCooldown(100);
                ratFistuled.setStunCooldown(1000);
                return ratFistuled;
            default:
                throw new IllegalArgumentException("Type d'ennemi inconnu : " + enemyType);
        }
    }
}
