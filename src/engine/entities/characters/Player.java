package engine.entities.characters;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.items.weapons.Weapon;
import engine.process.EntityFactory;
import log.Gamelog;

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
    private int mana;
    private String playerClass;
    private int playerHeight;
    private int playerWidth;
    private static Logger logger = Gamelog.getLogger();
  
    /**
     * Constructeur par défaut. Génère une nouvelle instance de player en utilisant le constructeur de la classe abstraite {@link engine.entities.characters.GameCharacter}
     * 
     * @param position la position du joueur.
     */
    private Player(Position position, String playerClass) {
        super(position, GameConfiguration.PLAYER_NAME, GameConfiguration.PLAYER_ENTITYTYPE, GameConfiguration.PLAYER_DEFAULT_MAXHEALTH, GameConfiguration.PLAYER_DEFAULT_MAXHEALTH, GameConfiguration.PLAYER_DEFAULT_ARMOR, GameConfiguration.PLAYER_DEFAULT_ATTACKSPEED, GameConfiguration.PLAYER_DEFAULT_ATTACKRANGE, GameConfiguration.PLAYER_DEFAULT_ATTACKDAMAGE, GameConfiguration.PLAYER_DEFAULT_MOVESPEED, GameConfiguration.PLAYER_DEFAULT_ABILITYCOOLDOWN, GameConfiguration.PLAYER_DEFAULT_STUNCOOLDOWN);
        setPlayerClass(playerClass);
    }

    public static Player getInstance() {
        // Si l'instance n'a pas encore été créée, on la crée
        if (player == null) {
            player = new Player(new Position(GameConfiguration.ROOM_CENTER_X, GameConfiguration.ROOM_CENTER_Y), "sorcerer");
        }
        // On retourne l'instance unique
        return player;
    }

    public int getCurrentStage() {
        if (currentRoom > 7) {
            currentStage++;
            currentRoom = 1;
        }
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

    public int getMana() {
        return this.mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
        return;
    }

    public String getPlayerClass() {
        return this.playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
        switch (playerClass) {
            case "heavy":
                super.setMaxHealth(150);
                super.setMoveSpeed(8);
                super.setAttackSpeed(5000);
                this.getEquipment().setWeapon((Weapon)EntityFactory.createEntity(GameConfiguration.SWORD_ENTITYTYPE, null));
                this.mana = 100;
                this.playerHeight = 40;
                this.playerWidth = 20;
                // FIXME : corriger les dimensions pour le HEAVY qd on aura un spray convenable
                break;

            case "sorcerer":
                super.setMaxHealth(100);
                super.setMoveSpeed(10);
                super.setAttackSpeed(4000);
                this.getEquipment().setWeapon((Weapon)EntityFactory.createEntity(GameConfiguration.SCEPTER_ENTITYTYPE, null)); 
                this.mana = 200;
                this.playerHeight = 40;
                this.playerWidth = 20;
                break;

            case "fast":
                super.setMaxHealth(85);
                super.setMoveSpeed(15);
                super.setAttackSpeed(3000);
                this.mana = 80;
                this.playerHeight = 40;
                this.playerWidth = 20;
                // FIXME : corriger les dimensions pour le FAST qd on aura un spray convenable
                break;

            default:
                logger.error("specified class doesn't exist : " + playerClass);
                break;
        }
    }

    public int getPlayerHeight() {
        return this.playerHeight;
    }

    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }
    
    public int getPlayerWidth() {
        return this.playerWidth;
    }

    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth;
    } 

}