package engine.entities.characters;

import config.GameConfiguration;
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
    public Enemy(Position position) {
        super(position, "enemy", GameConfiguration.ENEMY_DEFAULT_MAXHEALTH, GameConfiguration.ENEMY_DEFAULT_MAXHEALTH, GameConfiguration.ENEMY_DEFAULT_ARMOR, GameConfiguration.ENEMY_DEFAULT_ATTACKSPEED, GameConfiguration.ENEMY_DEFAULT_ATTACKRANGE, GameConfiguration.ENEMY_DEFAULT_ATTACKDAMAGE, GameConfiguration.ENEMY_DEFAULT_MOVESPEED, GameConfiguration.ENEMY_DEFAULT_ABILITYCOOLDOWN, GameConfiguration.ENEMY_DEFAULT_STUNCOOLDOWN);
    }
}
