package config;

import java.awt.Color;

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

    // dimensions de la fenetre
    public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 720;
	
    // vitesse d'exécution du jeu
	public static final int GAME_SPEED = 1;

    // statistiques du donjon
    public static final int NUMBER_OF_STAGES = 3;
    public static final int NUMBER_OF_ROOMS = 7;

    // statistiques du joueur : vitesse par défaut, dimensions, et points de vie
    public static final int PLAYER_DEFAULT_SPEED = 10;
    public static final int PLAYER_WIDTH = 20;
    public static final int PLAYER_HEIGHT = 40;
    public static final int PLAYER_HEALTH = 50;
    public static final int PLAYER_LIFEBAR_XSHIFT = -15;
    public static final int PLAYER_ENTITY_INTERACTION_RANGE = 50;
    
    // dégats de l'epée
    public static final int SWORD_DAMAGE = 5;
    public static final int SWORD_SPEED = 0;
    public static final int SWORD_RANGE = 100;
    public static final int SWORD_WIDTH = 30;
    public static final int SWORD_HEIGHT = 30;

    // points de vie récupérés par le health
    public static final int HEALTH_POINTS = 10;
    public static final int HEALTH_WIDTH = 30;
    public static final int HEALTH_HEIGHT = 30;

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

    // nombre d'ennemis par défaut, dimensions, et points de vie
    public static final int ENEMY_DEFAULT_SPEED = 5;
    public static final int ENEMIES_INIT_NUMBER = 2;
    public static final int ENEMY_WIDTH = 20;
    public static final int ENEMY_HEIGHT = 40;
    public static final int ENEMY_HEALTH = 30;
    public static final int ENEMY_LIFEBAR_XSHIFT = -5;
    public static final int ENEMY_NOSPAWNAREA = 100;

    // corrections pour le MouseListener    
    public static final int CORRECTCLICK_XSHIFT = -8; // Le MouseListener séléctionne une coordonnée en X incorrecte de 8 pixels en trop
    public static final int CORRECTCLICK_YSHIFT = -32; // Le MouseListener séléctionne une coordonnée en Y incorrecte de 31 pixels en trop

    public static final int CHARACTER_NAMETAG_XSHIFT = -5;
    public static final int CHARACTER_NAMETAG_YSHIFT = -5;
    public static final int CHARACTER_LIFEBAR_YSHIFT = 5;

    // partie inventaire
    public static final int INVENTORY_HEIGHT = WINDOW_HEIGHT/2;
    public static final int INVENTORY_WIDTH = WINDOW_WIDTH/2;
    public static final Position INVENTORY_POSITION = new Position((WINDOW_WIDTH/2) - (INVENTORY_WIDTH/2),(WINDOW_HEIGHT/2) - (INVENTORY_HEIGHT/2)) ;
    public static final Color INVENTORY_COLOR = Color.getHSBColor(181, 10, 68);
    public static final int INVENTORY_TITLEFONTSIZE = 32;
    public static final Position INVENTORY_TITLE_POSITION = new Position(INVENTORY_POSITION.getX() + INVENTORY_WIDTH/3, INVENTORY_POSITION.getY() + INVENTORY_HEIGHT/7);
    public static final int INVENTORY_MAX = 7;
    public static final int ITEM_NAMETAG_XSHIFT = 0;
    public static final int ITEM_NAMETAG_YSHIFT = -5;
    public static final int SLOT_DIMENSION = 30;

    //Fin du jeu
    public static final int GAME_OVER_HEIGHT = WINDOW_HEIGHT/2;
    public static final int GAME_OVER_WIDTH = WINDOW_WIDTH/2;
    public static final int GAME_OVER_TITLEFONTSIZE = 20;
    public static final Position GAME_OVER_POSITION = new Position((GameConfiguration.WINDOW_WIDTH - GameConfiguration.GAME_OVER_WIDTH) / 2,(GameConfiguration.WINDOW_HEIGHT - GameConfiguration.GAME_OVER_HEIGHT) / 2) ;
}