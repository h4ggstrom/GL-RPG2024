package config;

import engine.dungeon.Position;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient les constantes nécessaires au fonctionnement du jeu.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 *  
 */
public class GameConfiguration {

    // PARTIE DIMENSIONS
    public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 720;

    public static final int PLAYER_WIDTH = 20;
    public static final int PLAYER_HEIGHT = 40;

    public static final int ENEMY_WIDTH = 20;
    public static final int ENEMY_HEIGHT = 40;

    public static final int SWORD_WIDTH = 30;
    public static final int SWORD_HEIGHT = 30;

    public static final int HEALTHFLASK_WIDTH = 12;
    public static final int HEALTHFLASK_HEIGHT = 32;
    
    public static final int HELMET_WIDTH = 30;
    public static final int HELMET_HEIGHT = 30;

    public static final int GLOVES_WIDTH = 30;
    public static final int GLOVES_HEIGHT = 30;

    public static final int CHESTPLATE_WIDTH = 30;
    public static final int CHESTPLATE_HEIGHT = 30;

    public static final int PANTS_WIDTH = 30;
    public static final int PANTS_HEIGHT = 30;

    public static final int BOOTS_WIDTH = 30;
    public static final int BOOTS_HEIGHT = 30;

    public static final int BAG_WIDTH = 30;
    public static final int BAG_HEIGHT = 30;

    public static final int SCEPTER_WIDTH = 10;
    public static final int SCEPTER_HEIGHT = 20;

    public static final int GAME_OVER_HEIGHT = WINDOW_HEIGHT/2;
    public static final int GAME_OVER_WIDTH = WINDOW_WIDTH/2;
	
    // vitesse d'exécution du jeu
	public static final int GAME_SPEED = 1;

    // statistiques du donjon
    public static final int NUMBER_OF_STAGES = 3;
    public static final int NUMBER_OF_ROOMS = 7;

    // statistiques par défaut du joueur
    public static final int PLAYER_DEFAULT_MAXHEALTH = 50;
    public static final int PLAYER_DEFAULT_ARMOR = 20;
    public static final int PLAYER_DEFAULT_ATTACKSPEED = 4000; // En millisecondes
    public static final int PLAYER_DEFAULT_ATTACKRANGE = 50;
    public static final int PLAYER_DEFAULT_ATTACKDAMAGE = 5;
    public static final int PLAYER_DEFAULT_MOVESPEED = 10;
    public static final int PLAYER_DEFAULT_ABILITYCOOLDOWN = 1000;
    public static final int PLAYER_DEFAULT_STUNCOOLDOWN = 4000;
    public static final int PLAYER_LIFEBAR_XSHIFT = -15;
    public static final int PLAYER_ENTITY_INTERACTION_RANGE = 50;
    
    // PARTIE ARMES

    // Épée
    public static final int SWORD_DAMAGE = 15;
    public static final int SWORD_RANGE = 50;

    // Fouet
    public static final int WHIP_DAMAGE = 3;
    public static final int WHIP_RANGE = 100;
    public static final int WHIP_AREA_DAMAGE = 1;

    // Sceptre
    public static final int SCEPTER_BURN_DAMAGE = 1;
    public static final int SCEPTER_DAMAGE = 5;
    public static final int SCEPTER_RANGE = 250;

    // Grimoire
    public static final int GRIMOIRE_ROOT_DURATION = 3;
    public static final int GRIMOIRE_DAMAGE = 2;
    public static final int GRIMOIRE_RANGE = 250;

    // Dague
    public static final int DAGGER_DAMAGE = 3;
    public static final int DAGGER_RANGE = 50;
    public static final int DAGGER_BLEED_DAMAGE = 1;

    // Arc
    public static final int BOW_DAMAGE = 5;
    public static final int BOW_RANGE = 300;
    public static final int BOW_DAMAGE_OVER_RANGE = 5;

    // dimensions et limites de la salle
    public static final int ROOM_UPPER_LIMITATION = 52; // Le mur bloque le joueur avant cette valeur en Y
    public static final int ROOM_LOWER_LIMITATION = 669; // Le mur bloque le joueur après cette valeur en Y
    public static final int ROOM_LEFT_LIMITATION = 52; // Le mur bloque le joueur avant cette valeur en X
    public static final int ROOM_RIGHT_LIMITATION = 1020; // Le mur bloque le joueur après cette valeur en X
    public static final int ROOM_CENTER_X = 549;
    public static final int ROOM_CENTER_Y = 359;
    public static final Position ROOM_CENTER = new Position(ROOM_CENTER_X, ROOM_CENTER_Y);
    public static final Position GATE_UP = new Position(1026, 309); // Le pixel haut de la porte de sortie
    public static final Position GATE_DOWN = new Position(1028, 448); // Le pixel bas de la porte de sortie

    // statistiques par défaut des ennemis
    public static final int ENEMIES_INIT_NUMBER = 2;
    public static final int ENEMY_DEFAULT_MAXHEALTH = 30;
    public static final int ENEMY_DEFAULT_ARMOR = 10;
    public static final int ENEMY_DEFAULT_ATTACKSPEED = 5;
    public static final int ENEMY_DEFAULT_ATTACKRANGE = 50;
    public static final int ENEMY_DEFAULT_ATTACKDAMAGE = 3;
    public static final int ENEMY_DEFAULT_MOVESPEED = 5;
    public static final int ENEMY_DEFAULT_ABILITYCOOLDOWN = 1000;
    public static final int ENEMY_DEFAULT_STUNCOOLDOWN = 4000;
    public static final int ENEMY_LIFEBAR_XSHIFT = -5;
    public static final int ENEMY_NOSPAWNAREA = 100;

    // corrections pour le MouseListener    
    public static final int CORRECTCLICK_XSHIFT = -8; // Le MouseListener séléctionne une coordonnée en X incorrecte de 8 pixels en trop
    public static final int CORRECTCLICK_YSHIFT = -32; // Le MouseListener séléctionne une coordonnée en Y incorrecte de 31 pixels en trop

    public static final int CHARACTER_NAMETAG_XSHIFT = -5;
    public static final int CHARACTER_NAMETAG_YSHIFT = -5;
    public static final int CHARACTER_LIFEBAR_YSHIFT = 5;

    // partie inventaire et sac
    public static final int INVENTORY_MAX = 6;
    public static final int BAG_MAX = 12;

    // partie noms d'entités
    public static final String INVENTORY_ENTITYTYPE = "inventory";
    public static final String BAG_ENTITYTYPE = "bag";
    public static final String PLAYER_ENTITYTYPE = "player";
    public static final String ENEMY_ENTITYTYPE = "enemy";
    public static final String SWORD_ENTITYTYPE = "sword";
    public static final String HEALTHFLASK_ENTITYTYPE = "healthFlask";
    public static final String HELMET_ENTITYTYPE = "helmet";
    public static final String GLOVES_ENTITYTYPE = "gloves";
    public static final String CHESTPLATE_ENTITYTYPE = "chestplate";
    public static final String PANTS_ENTITYTYPE = "pants";
    public static final String BOOTS_ENTITYTYPE = "boots";
    public static final String DAGGER_ENTITYTYPE = "dagger";
    public static final String WHIP_ENTITYTYPE = "whip";
    public static final String SCEPTER_ENTITYTYPE = "scepter";
    public static final String GRIMOIRE_ENTITYTYPE = "grimoire";
    public static final String BOW_ENTITYTYPE = "bow";
    public static final String COIN_ENTITYPE = "coin";

    // partie noms d'objets
    public static final String INVENTORY_NAME = "Inventaire";
    public static final String BAG_NAME = "Sac";
    public static final String PLAYER_NAME = "Joueur";
    public static final String ENEMY_NAME = "Ennemi";
    public static final String SWORD_NAME = "Épée de rat";
    public static final String HEALTHFLASK_NAME = "Potion de santé";
    public static final String HELMET_NAME = "Casque de rat";
    public static final String GLOVES_NAME = "Gants de rat";
    public static final String CHESTPLATE_NAME = "Plastron de rat";
    public static final String PANTS_NAME = "Pantalon de rat";
    public static final String BOOTS_NAME = "Bottes de rat";
    public static final String DAGGER_NAME = "Dague de rat";
    public static final String WHIP_NAME = "Fouet de rat";
    public static final String SCEPTER_NAME = "Sceptre de rat";
    public static final String GRIMOIRE_NAME = "Grimoire de rat";
    public static final String BOW_NAME = "Arc de rat";
    public static final String COIN_NAME = "pièce";

    // partie effets
    public static final String HEALTHFLASK_EFFECT = "heal";
    public static final String HELMET_EFFECT = "abilityCooldown";
    public static final String GLOVES_EFFECT = "attackSpeed";
    public static final String CHESTPLATE_EFFECT = "armor";
    public static final String PANTS_EFFECT = "stunCooldown";
    public static final String BOOTS_EFFECT = "moveSpeed";

    // partie bonus
    public static final int HEALTHFLASK_BONUS = 10;
    public static final int HELMET_BONUS = 3;
    public static final int GLOVES_BONUS = -2000; // En millisecondes
    public static final int CHESTPLATE_BONUS = 10;
    public static final int PANTS_BONUS = 2;
    public static final int BOOTS_BONUS = 5;

    // partie monnaie
    public static final int COIN_VALUE = 1;

    // écran gameover
    public static final int GAME_OVER_TITLEFONTSIZE = 20;
    public static final Position GAME_OVER_POSITION = new Position((GameConfiguration.WINDOW_WIDTH - GameConfiguration.GAME_OVER_WIDTH) / 2,(GameConfiguration.WINDOW_HEIGHT - GameConfiguration.GAME_OVER_HEIGHT) / 2) ;
}