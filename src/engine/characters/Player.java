package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Position;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient toutes les données relatives au joueur. Cette classe est un Singleton et ne s'instancie qu'une fois au lancement du jeu.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Player extends GameCharacter {

    private static Player player;
  
    /**
     * Constructeur par défaut. Génère une nouvelle instance de player en utilisant le constructeur de la classe abstraite {@link engine.characters.GameCharacter}
     * 
     * @param position la position du joueur.
     */
    private Player(Position position) {
        super(position, "player", GameConfiguration.PLAYER_HEALTH);
    }

    public static Player getInstance() {
        // Si l'instance n'a pas encore été créée, on la crée
        if (player == null) {
            player = new Player(new Position(GameConfiguration.ROOM_CENTER_X, GameConfiguration.ROOM_CENTER_Y));
        }
        // On retourne l'instance unique
        return player;
    }
    
}