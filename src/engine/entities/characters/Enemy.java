package engine.entities.characters;

import engine.dungeon.Position;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient les données liées aux ennemis.
 * Pour les valeurs par défaut, voir {@link config.GameConfiguration}
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Enemy extends GameCharacter {

    /**
     * Constructeur par défaut. Crée une nouvelle instance d'Enemy en utilisant le constructeur de {@link engine.entities.characters.GameCharacter}
     * 
     * @param position la position de départ de l'ennemi
     */
    public Enemy(Position position, String enemyName, String enemyType, int maxHealth, int health, int armor, int attackSpeed, int attackRange, int attackDamage, int moveSpeed, int abilityCooldown, int stunCooldown) {
        super(position, enemyName, enemyType, maxHealth, health, armor, attackSpeed, attackRange, attackDamage, moveSpeed, abilityCooldown, stunCooldown);
    }
}
