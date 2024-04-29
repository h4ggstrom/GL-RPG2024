package engine.entities.characters;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.process.visitor.EntityVisitor;
import gui.LevelUpGUI;

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
    private int stageNumber = 1;
    private int roomNumber = 1;
    private int experience = 0;
    private int level = 1;

    private int coinCounter = 0;
    private String playerClass;
    private int playerHeight;
    private int playerWidth;
  
    /**
     * Constructeur par défaut. Génère une nouvelle instance de player en utilisant le constructeur de la classe abstraite {@link engine.entities.characters.GameCharacter}
     * 
     * @param position la position du joueur.
     */
    private Player(Position position, String playerClass) {
        super(position, GameConfiguration.PLAYER_NAME, GameConfiguration.PLAYER_ENTITYTYPE);
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

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public void incrementStage() {
        if(stageNumber < 3) {
            this.stageNumber++;
        }
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void incrementRoom() {
        this.roomNumber++;
        if(roomNumber > 7) {
            this.roomNumber = 1;
            incrementStage();
        }
    }

    public void gainExp(int exp) {
        this.experience += exp;
        if(experience >= GameConfiguration.PLAYER_EXP_MAX) {
            this.experience -= GameConfiguration.PLAYER_EXP_MAX;
            gainLevel();
        }
    }

    public int getExp() {
        return this.experience;
    }

    public int getLevel() {
        return level;
    }

    public void gainLevel() {
        this.level++;
        new LevelUpGUI();
    }

    public void addCoins(int numberOfCoins) {
        this.coinCounter += numberOfCoins;
    }

    public void removeCoins(int numberOfCoins) {
        this.coinCounter -= numberOfCoins;
    }

    public int getCoinCount() {
        return this.coinCounter;
    }

    public String getPlayerClass() {
        return this.playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
        switch (playerClass) {
            case "heavy":
                super.setMaxHealth(150);
                super.setHealth(150);
                super.setMoveSpeed(8);
                super.setAttackSpeed(500);
                super.setArmor(30);
                super.setAttackDamage(15);
                super.setAttackRange(GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE);
                super.setAbilityCooldown(1400);
                super.setStunCooldown(200);
                this.playerHeight = 40;
                this.playerWidth = 20;
                // FIXME : corriger les dimensions pour le HEAVY qd on aura un spray convenable
                break;

            case "sorcerer":
                super.setMaxHealth(100);
                super.setHealth(100);
                super.setMoveSpeed(10);
                super.setAttackSpeed(400);
                super.setArmor(0);
                super.setAttackDamage(10);
                super.setAttackRange(GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE);
                super.setAbilityCooldown(1000);
                super.setStunCooldown(1000);
                this.playerHeight = 40;
                this.playerWidth = 20;
                break;

            case "fast":
                super.setMaxHealth(85);
                super.setHealth(85);
                super.setMoveSpeed(14);
                super.setAttackSpeed(300);
                super.setArmor(0);
                super.setAttackDamage(5);
                super.setAttackRange(GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE);
                super.setAbilityCooldown(700);
                super.setStunCooldown(1000);
                this.playerHeight = 40;
                this.playerWidth = 20;
                // FIXME : corriger les dimensions pour le FAST qd on aura un spray convenable
                break;

            default:
                throw new IllegalArgumentException("specified class doesn't exist : " + playerClass);
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

    public void loadSave() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/save/save.dat"))) {
            player = (Player)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de blesser le joueur en particuler, pratique pour les vérifications avec l'ability du heavy
     */
    @Override
    public void hurtCharacter(int damage) {
        // Si le joueur est un heavy
        if(playerClass.equals("heavy")) {
            // On vérifie que l'ability du heavy s'est activée il y a plus de 5 secondes
            if(getMana() >= 500) {
                // Si c'est le cas on peut blesser le joueur
                super.hurtCharacter(damage);
            }
        }
        else {
            super.hurtCharacter(damage);
        }
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}

}