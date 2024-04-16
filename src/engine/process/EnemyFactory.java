package engine.process;

import engine.dungeon.Position;
import engine.entities.characters.Enemy;

/**
 * Cette classe permet la création des multiples type d'ennemis
 * Rappel de l'ordre des constructeurs :
 * Position | Nom | Type | PV max | PV | Armure | Vitesse d'attaque | Portée d'attaque | Dégâts d'attaque | Vitesse de déplacement | Récupération d'abilité | Temps d'immobilisation
 * Les vitesses (sauf déplacement) sont exprimées en tick (10 millisecondes)
 */
public class EnemyFactory {

    public static Enemy createEnemy(String enemyType, Position position) {
        switch (enemyType) {
            case "rat_fistule":
                return new Enemy(position, "Rat fistulé", "rat_fistule", 15, 15, 10, 100, 30, 2, 1, 100, 1000);
            default:
                throw new IllegalArgumentException("Type d'ennemi inconnu : " + enemyType);
        }
    }
}
