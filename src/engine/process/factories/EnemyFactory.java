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
                ratFistuled.addMaxHealth(15);
                ratFistuled.healCharacter(15);
                ratFistuled.addArmor(1);
                ratFistuled.addAttackDamage(2);
                ratFistuled.addAttackRange(30);
                ratFistuled.addAttackSpeed(100);
                ratFistuled.addMoveSpeed(1);
                ratFistuled.addAbilityCooldown(100);
                ratFistuled.addStunCooldown(1000);
                return ratFistuled;
            default:
                throw new IllegalArgumentException("Type d'ennemi inconnu : " + enemyType);
        }
    }
}
