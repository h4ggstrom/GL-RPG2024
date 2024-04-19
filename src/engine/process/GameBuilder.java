package engine.process;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.entities.Entity;
import engine.entities.characters.Enemy;
import engine.entities.characters.Player;
import engine.entities.containers.Chest;
import engine.entities.containers.Garbage;
import engine.entities.environment.GateEnv;
import engine.entities.environment.TreeEnv;
import engine.entities.environment.WallEnv;
import engine.entities.items.Key;
import engine.entities.items.consumables.Coin;
import engine.entities.items.consumables.HealthFlask;
import engine.entities.items.equipment.Boots;
import engine.entities.items.equipment.Chestplate;
import engine.entities.items.weapons.Scepter;
import engine.entities.npc.Vendor;
import engine.dungeon.Dungeon;
import log.Gamelog;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe gère la génération du jeu (salle et entités)
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class GameBuilder {

    private static Logger logger = Gamelog.getLogger();

    public static Dungeon buildDungeon() {
        logger.trace("New instance of Dungeon sent");
        return new Dungeon();
    }

    /**
     * Génère la salle et les ennemis à l'apparition de la salle en utilisant le constructeur de la classe {@link src.engine.characters.EntityManager}
     * 
     * @param room la salle dans laquelle évolue le joueur
     * @return le système de gestion de la partie. Pour plus de détails, voir {@link src.engine.characters.EntityManager}
     */
    public static EntityManager buildInitEntities (Dungeon dungeon) {
        EntityManager manager = new EntityManager(dungeon);
        logger.trace("New instance of EntityManager");

        initializePlayer(manager);
        logger.trace("Initialized player");

        initializeEntities(manager);
        logger.trace("Initialized entities");

        logger.trace("Returning manager");
        return manager;
    }

    /**
     * Cette méthode permet d'initialiser le joueur au centre de la salle
     * 
     * @param manager le process de gestion des actions, auquel sera ajouté le joueur généré
     * 
     * @see engine.process.EntityManager pour les détails du processus de gestion des actions du joueur
     */
    private static void initializePlayer (EntityManager manager) {
        // On récupère l'instance de Player
        Player player = Player.getInstance();
        // On ajoute le joueur à la liste d'entités
        manager.getCurrentRoom().addEntity(player);
    }

    /**
     * Permet l'initialisation de toutes les entités de la salle actuelle, l'ordre de génération est important
     * @param manager
     */
    public static void initializeEntities(EntityManager manager) {

        Room currentRoom = manager.getCurrentRoom();

        initializeEnvironment(manager);
        logger.trace("Initialized environment");

        if(currentRoom.isShop()) {
            // On initialise le vendeur
            initializeVendor(manager);
            // On ouvre directement la porte
            manager.getCurrentRoom().open();
            logger.trace("Initialized vendor");
        }
        else {
            initializeEnemies(manager);
            logger.trace("Initialized ennemies");
        }

    }

    /**
     * Cette méthode permet d'initialiser les ennemis à des endroits aléatoires de la salle.
     * 
     * @param manager le process de gestion des actions auquel seront ajoutés les ennemis générés.
     * 
     * @see engine.process.EntityManager pour les détails du processus de gestion des ennemis
     */
    public static void initializeEnemies(EntityManager manager) {
        Player player = Player.getInstance();
        int roomNumber = player.getRoomNumber();
        int enemyCount = player.getstageNumber() * player.getRoomNumber(); 
        for (int i = 0; i < enemyCount; i++) {
            Enemy enemy = EnemyFactory.createEnemy("rat_fistule", null); // On instancie des rats fistulés
            randomPlaceEntity(manager, enemy); // On le place de manière aléatoire
            initializeEquipmentOfEnemy(enemy, roomNumber, enemyCount); // On l'équipe
        }
    }
    
    private static void initializeEquipmentOfEnemy(Enemy enemy, int currentRoom, int enemyCount) {
        switch(enemy.getEntityType()) {
            case "rat_fistule":
                // On génère un nombre aléatoire entre 1 et 10 * le numéro de la room
                int randomNumber = getRandomNumber(1, 10*currentRoom);

                // On ajoute à l'inventaire du rat fistulé des pièces
                Coin coins = (Coin)EntityFactory.createEntity(GameConfiguration.COIN_ENTITYTYPE, null);
                coins.setConsumableValue(randomNumber);
                enemy.getInventory().addItem(coins);

                break;
        }
    }

    /**
     * On initialise l'environnement dans un ordre particulier : les murs avant le reste pour que tout soit contenu à l'intérieur des murs
     * @param manager
     */
    public static void initializeEnvironment(EntityManager manager) {
        initializeWalls(manager);
        initializeTrees(manager);
        initializeChest(manager);
        initializeGarbage(manager);
    }

    public static void initializeTrees(EntityManager manager) {
        // on veut entre 7 et 11 arbres
        int treeNumber = getRandomNumber(7, 11);
        for(int i = 0 ; i < treeNumber ; i++) {
            TreeEnv tree = (TreeEnv)EntityFactory.createEntity(GameConfiguration.TREE_ASSET_ENTITYTYPE, null);
            randomPlaceEntity(manager, tree);
        }
    }

    public static void initializeChest(EntityManager manager) {
        Chest chest = (Chest)EntityFactory.createEntity(GameConfiguration.CHEST_ENTITYTYPE, null);
        chest.addItem((Scepter)EntityFactory.createEntity(GameConfiguration.SCEPTER_ENTITYTYPE, null));
        randomPlaceEntity(manager, chest);
    }

    public static void initializeVendor(EntityManager manager) {
        Vendor vendor = (Vendor)EntityFactory.createEntity(GameConfiguration.VENDOR_ENTITYTYPE, null);
        vendor.addSellingItem((Chestplate)EntityFactory.createEntity(GameConfiguration.CHESTPLATE_ENTITYTYPE, null), 300);
        vendor.addSellingItem((HealthFlask)EntityFactory.createEntity(GameConfiguration.HEALTHFLASK_ENTITYTYPE, null), 50);
        vendor.addSellingItem((Boots)EntityFactory.createEntity(GameConfiguration.BOOTS_ENTITYTYPE, null), 250);
        randomPlaceEntity(manager, vendor);
    }

    public static void initializeGarbage(EntityManager manager) {
        // On met entre 6 et 16 piles de déchêts par salle
        int garbageNumber = getRandomNumber(6, 16);
        for(int i = 0 ; i < garbageNumber ; i++) {
            Garbage garbage = (Garbage)EntityFactory.createEntity(GameConfiguration.GARBAGE_ENTITYTYPE, null);
            // On veut qu'il y ai une chance sur 10 qu'une clé se trouve à l'intérieur du tas de détritus
            int keyOdds = getRandomNumber(1, 10);
            if(keyOdds == 10) {
                garbage.addItem((Key)EntityFactory.createEntity(GameConfiguration.KEY_ENTITYTYPE, null));
            }
            randomPlaceEntity(manager, garbage);
        }
    }

    public static void initializeWalls(EntityManager manager) {
        Room currentRoom = manager.getCurrentRoom();
        WallEnv sideWall = new WallEnv(null);
        sideWall.getHitbox().drawHitbox(new Position(0, 0), new Position(GameConfiguration.ROOM_LEFT_LIMITATION, GameConfiguration.WINDOW_HEIGHT));
        currentRoom.addEntity(sideWall);

        WallEnv upperWall = new WallEnv(null);
        upperWall.getHitbox().drawHitbox(new Position(0, 0), new Position(GameConfiguration.WINDOW_WIDTH, GameConfiguration.ROOM_UPPER_LIMITATION));
        currentRoom.addEntity(upperWall);

        WallEnv lowerWall = new WallEnv(null);
        lowerWall.getHitbox().drawHitbox(new Position(0, GameConfiguration.ROOM_LOWER_LIMITATION), new Position(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT));
        currentRoom.addEntity(lowerWall);

        WallEnv upperGateWall = new WallEnv(null);
        upperGateWall.getHitbox().drawHitbox(new Position(GameConfiguration.ROOM_RIGHT_LIMITATION, 0), GameConfiguration.GATE_UPPERRIGHT);
        currentRoom.addEntity(upperGateWall);

        WallEnv lowerGateWall = new WallEnv(null);
        lowerGateWall.getHitbox().drawHitbox(GameConfiguration.GATE_BOTTOMLEFT, new Position(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT));
        currentRoom.addEntity(lowerGateWall);

        GateEnv gate = new GateEnv(null);
        gate.getHitbox().drawHitbox(GameConfiguration.GATE_UPPERLEFT, GameConfiguration.GATE_BOTTOMRIGHT);
        currentRoom.setGate(gate);
        currentRoom.addEntity(gate);
    }

    public static void randomPlaceEntity(EntityManager manager, Entity entity) {
        boolean cannotBePlaced = true;
        while(cannotBePlaced) {
            int randomX = getRandomNumber(0, GameConfiguration.WINDOW_WIDTH);
            int randomY = getRandomNumber(0, GameConfiguration.WINDOW_HEIGHT);
            Position randomPosition = new Position(randomX, randomY);
            entity.setPosition(randomPosition);
            // Si la hitbox de l'ennemi n'est en collision avec aucune autre dans la Room
            if(manager.verifHitboxes(entity.getHitbox())) {
                manager.getCurrentRoom().addEntity(entity); // On ajoute l'entité à la liste d'entités de la Room
                break; // On sort de la boucle
            }
        }
    }
    
    /**
     * Cette méthode génère un nombre aléatoire compris entre un minimum et un maximum
     * 
     * @param min le nombre minimum
     * @param max le nombre maximum
     * @return le nombre aléatoire généré, formatté en entier
     */
    public static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
    }
}
