package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Position;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient toutes les données relatives à l'avatar du joueur.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Player extends GameCharacter {
  
    /**
     * Constructeur par défaut. Génère une nouvelle instance de player en utilisant le constructeur de la classe abstraite {@link engine.characters.GameCharacter}
     * 
     * @param position la position du joueur.
     */
    public Player( Position position) {
        super(position, "player", GameConfiguration.PLAYER_HEALTH);
    }
    
}