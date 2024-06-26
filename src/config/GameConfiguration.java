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

    public static final String GAME_TITLE = "Le Donjon de Cergy-Préfecture";

    // PARTIE DIMENSIONS D'ENTITÉS
    public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 720;

    public static final int PLAYER_WIDTH = 40;
    public static final int PLAYER_HEIGHT = 40;

    public static final int VENDOR_WIDTH = 30;
    public static final int VENDOR_HEIGHT = 60;

    public static final int ENEMY_WIDTH = 20;
    public static final int ENEMY_HEIGHT = 40;

    public static final int RAT_FISTULE_WIDTH = 24;
    public static final int RAT_FISTULE_HEIGHT = 21;

    public static final int ROCKY_BLATEBOA_WIDTH = 30;
    public static final int ROCKY_BLATEBOA_HEIGHT = 30;

    public static final int ABOMINATION_DES_EGOUTS_WIDTH = 120;
    public static final int ABOMINATION_DES_EGOUTS_HEIGHT = 120;

    public static final int CRACKHEAD_WIDTH = 40;
    public static final int CRACKHEAD_HEIGHT = 40;

    public static final int CHEVRE_WIDTH = 40;
    public static final int CHEVRE_HEIGHT = 40;

    public static final int GOBLIN_WIDTH = 120;
    public static final int GOBLIN_HEIGHT = 120;

    public static final int PROFESSOR_WIDTH = 50;
    public static final int PROFESSOR_HEIGHT = 50;

    public static final int SECRETAIRE_WIDTH = 50;
    public static final int SECRETAIRE_HEIGHT = 50;

    public static final int DERDOUDIABLE_WIDTH = 200;
    public static final int DERDOUDIABLE_HEIGHT = 200;

    public static final int SWORD_WIDTH = 30;
    public static final int SWORD_HEIGHT = 30;

    public static final int FLASK_WIDTH = 12;
    public static final int FLASK_HEIGHT = 32;
    
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

    public static final int TREE_WIDTH = 56;
    public static final int TREE_HEIGHT = 51;

    public static final int PIPE_WIDTH = 40;
    public static final int PIPE_HEIGHT = 51;

    public static final int TABLE_WIDTH = 46;
    public static final int TABLE_HEIGHT = 30;

    public static final int CHEST_WIDTH = 32;
    public static final int CHEST_HEIGHT = 32;

    public static final int KEY_WIDTH = 30;
    public static final int KEY_HEIGHT = 30;

    public static final int GARBAGE_WIDTH = 36;
    public static final int GARBAGE_HEIGHT = 36;
	
    // vitesse d'exécution du jeu, un tick sera égal à ce nombre de millisecondes
	public static final int GAME_SPEED = 10;

    // statistiques du donjon
    public static final int NUMBER_OF_STAGES = 3;
    public static final int NUMBER_OF_ROOMS = 7;

    // valeurs par défaut relatives au joueur
    public static final int PLAYER_LIFEBAR_XSHIFT = -15;
    public static final int PLAYER_ENTITY_INTERACTION_RANGE = 60;

    // dimensions et limites de la salle
    public static final int ROOM_UPPER_LIMITATION = 52; // Le mur bloque le joueur avant cette valeur en Y
    public static final int ROOM_LOWER_LIMITATION = 669; // Le mur bloque le joueur après cette valeur en Y
    public static final int ROOM_LEFT_LIMITATION = 52; // Le mur bloque le joueur avant cette valeur en X
    public static final int ROOM_RIGHT_LIMITATION = 1026; // Le mur bloque le joueur après cette valeur en X

    public static final int ROOM_CENTER_X = 549;
    public static final int ROOM_CENTER_Y = 359;
    public static final Position ROOM_CENTER = new Position(ROOM_CENTER_X, ROOM_CENTER_Y);
    
    public static final Position GATE_UPPERLEFT = new Position(1026, 309); // Le pixel haut droit de la porte de sortie
    public static final Position GATE_UPPERRIGHT = new Position(1080, 309); // Le pixel haut droit de la porte de sortie
    public static final Position GATE_BOTTOMLEFT = new Position(1026, 448); // Le pixel bas gauche de la porte de sortie
    public static final Position GATE_BOTTOMRIGHT = new Position(1080, 448); // Le pixel bas gauche de la porte de sortie

    public static final int ENEMY_LIFEBAR_XSHIFT = -5;
    public static final int ENEMY_NOSPAWNAREA = 100;

    // corrections pour le MouseListener    
    public static final int CORRECTCLICK_XSHIFT = -8; // Le MouseListener séléctionne une coordonnée en X incorrecte de 8 pixels en trop
    public static final int CORRECTCLICK_YSHIFT = -32; // Le MouseListener séléctionne une coordonnée en Y incorrecte de 31 pixels en trop

    public static final int CHARACTER_NAMETAG_XSHIFT = -5;
    public static final int CHARACTER_NAMETAG_YSHIFT = -5;
    public static final int CHARACTER_LIFEBAR_YSHIFT = 5;

    // partie maximums
    public static final int INVENTORY_MAX = 6;
    public static final int BAG_MAX = 12;
    public static final int CHEST_MAX = 1;
    public static final int GARBAGE_MAX = 1;
    public static final int PLAYER_EXP_MAX = 1000;

    // partie noms d'entités (utile pour les assets par exemple)
    public static final String INVENTORY_ENTITYTYPE = "inventory";
    public static final String BAG_ENTITYTYPE = "bag";
    public static final String PLAYER_ENTITYTYPE = "player";
    public static final String COIN_ENTITYTYPE = "coin";
    public static final String TREE_ASSET_ENTITYTYPE = "tree";
    public static final String WALL_ASSET_ENTITYTYPE = "wall";
    public static final String GATE_ASSET_ENTITYTYPE = "gate";
    public static final String CHEST_ENTITYTYPE = "chest";
    public static final String KEY_ENTITYTYPE = "key";
    public static final String GARBAGE_ENTITYTYPE = "garbage";
    public static final String VENDOR_ENTITYTYPE = "vendor";
    public static final String PIPE_ASSET_ENTITYTYPE = "pipe";
    public static final String TABLE_ASSET_ENTITYTYPE = "table";

    // partie noms d'objets
    public static final String INVENTORY_NAME = "Inventaire";
    public static final String BAG_NAME = "Sac";
    public static final String PLAYER_NAME = "Joueur";
    public static final String COIN_NAME = "Pièce";
    public static final String TREE_ASSET_NAME = "Arbre";
    public static final String WALL_ASSET_NAME = "Mur";
    public static final String GATE_ASSET_NAME = "Porte";
    public static final String STRONG_NAME = "Costaud";
    public static final String SORCERER_NAME = "Sorcier";
    public static final String FAST_NAME = "Rapide";
    public static final String CHEST_NAME = "Coffre";
    public static final String KEY_NAME = "Clé de coffre";
    public static final String GARBAGE_NAME = "Pile de détritus";
    public static final String VENDOR_NAME = "Mohamed-Lamine le Marchand";
    public static final String PIPE_ASSET_NAME = "Tuyau";
    public static final String TABLE_ASSET_NAME = "Table";

    // écran gameover
    public static final int GAME_OVER_TITLEFONTSIZE = 20;
    public static final Position GAME_OVER_POSITION = new Position((GameConfiguration.WINDOW_WIDTH - GameConfiguration.GAME_OVER_WIDTH) / 2,(GameConfiguration.WINDOW_HEIGHT - GameConfiguration.GAME_OVER_HEIGHT) / 2) ;
}