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

    // À faire : FIXME
    // BIEN DOCUMENTER TOUTES LES CLASSES ET MÉTHODES (optimiser au max la réutilisabilité -> manuels utilisateur avec @see et @link ...)

    //CORRIGER : Important ! Doser valeurs (Game Speed, vitesse de déplacement, vitesse d'attaque, dégats...)
    //CORRIGER : Parfois les arbres apparaissent devant la porte de sortie ...
    //CORRIGER : Les ennemies peuvent être repoussés dans les entités et par la suite être coincés

    //AMELIORER : Visuels GUI en général
    
    //AJOUTER : Tous les types d'ennemis et BOSS à la fin des étages
    //AJOUTER : Tous les types d'armes
    //AJOUTER : Capacités (mana, immobilisation, visuels) idée : magicien peut stun, guerrier peut repousser, fast peut invisibilité
    //--> Cooldown de compétences fonctionnel et visuel & réduire durée stun ptite souris (d'ailleurs durée variable par ennemis)
    //AJOUTER : Paramètres
    //AJOUTER : Implémenter l'armure, stun et cooldown, ability et cooldown
    //AJOUTER : Changement d'environnement, égoûts. (Changer d'environnement à chaque étage si on est chauds)

    public static final String GAME_TITLE = "Le Donjon de Cergy-Préfecture";

    // PARTIE DIMENSIONS
    public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 720;

    public static final int PLAYER_WIDTH = 20;
    public static final int PLAYER_HEIGHT = 40;

    public static final int VENDOR_WIDTH = 20;
    public static final int VENDOR_HEIGHT = 40;

    public static final int ENEMY_WIDTH = 20;
    public static final int ENEMY_HEIGHT = 40;

    public static final int RAT_FISTULE_WIDTH = 24;
    public static final int RAT_FISTULE_HEIGHT = 21;

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

    // distance maximale entre le player l'enemy, en dessous duquel l'enemy se déplacera vers le player
    public static final int MAXIMAL_DISTANCE = 200;
	
    // vitesse d'exécution du jeu, un tick sera égal à ce nombre de millisecondes
	public static final int GAME_SPEED = 10;

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
    public static final int SCEPTER_DAMAGE = 80;
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

    // écran gameover
    public static final int GAME_OVER_TITLEFONTSIZE = 20;
    public static final Position GAME_OVER_POSITION = new Position((GameConfiguration.WINDOW_WIDTH - GameConfiguration.GAME_OVER_WIDTH) / 2,(GameConfiguration.WINDOW_HEIGHT - GameConfiguration.GAME_OVER_HEIGHT) / 2) ;
}