package engine.entities.characters;

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

    // définition des attributs
    private static Player player;
    private int currentStage = 1;
    private int currentRoom = 1;
    private int experience = 0;
    private int coinCounter = 0;
  
    /**
     * Constructeur par défaut. Génère une nouvelle instance de player en utilisant le constructeur de la classe abstraite {@link engine.entities.characters.GameCharacter}
     * 
     * @param position la position du joueur.
     */
    private Player(Position position) {
        super(position, GameConfiguration.PLAYER_NAME, GameConfiguration.PLAYER_ENTITYTYPE, GameConfiguration.PLAYER_DEFAULT_MAXHEALTH, GameConfiguration.PLAYER_DEFAULT_MAXHEALTH, GameConfiguration.PLAYER_DEFAULT_ARMOR, GameConfiguration.PLAYER_DEFAULT_ATTACKSPEED, GameConfiguration.PLAYER_DEFAULT_ATTACKRANGE, GameConfiguration.PLAYER_DEFAULT_ATTACKDAMAGE, GameConfiguration.PLAYER_DEFAULT_MOVESPEED, GameConfiguration.PLAYER_DEFAULT_ABILITYCOOLDOWN, GameConfiguration.PLAYER_DEFAULT_STUNCOOLDOWN);
    }

    public static Player getInstance() {
        // Si l'instance n'a pas encore été créée, on la crée
        if (player == null) {
            player = new Player(new Position(GameConfiguration.ROOM_CENTER_X, GameConfiguration.ROOM_CENTER_Y));
        }
        // On retourne l'instance unique
        return player;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void moveToNextRoom() {
        this.currentRoom++;
    }

    public void gainExp(int exp) {
        this.experience += exp;
    }

    public int getExp() {
        return this.experience;
    }

    public void addCoins() {
        this.coinCounter += GameConfiguration.COIN_VALUE;
    }

    public int getCoinCount() {
        return this.coinCounter;
    }

}