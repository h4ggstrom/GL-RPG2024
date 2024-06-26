package engine.process.management;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.entities.Entity;
import engine.entities.characters.Enemy;
import engine.entities.characters.Player;
import engine.entities.containers.Chest;
import engine.entities.containers.Garbage;
import engine.entities.environment.Environment;
import engine.entities.items.Key;
import engine.entities.items.consumables.*;
import engine.entities.items.equipment.*;
import engine.entities.npc.Vendor;
import engine.process.factories.*;
import engine.dungeon.Dungeon;
import log.Gamelog;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe gère la génération du jeu, c'est-à-dire la génération des salles, des entités, des obstacles, des ennemis, des coffres, des vendeurs, etc.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class GameBuilder {

    private static Logger logger = Gamelog.getLogger();
    private static EntityManager manager = EntityManager.getInstance();

    public static Dungeon buildDungeon() {
        logger.trace("New instance of Dungeon sent");
        return new Dungeon();
    }

    /**
     * Cette méthode permet de générer les entités de la salle actuelle
     * 
     * @param dungeon le donjon dans lequel se trouve la salle
     * 
     * @see engine.dungeon.Dungeon pour les détails du donjon
     */
    public static void buildInitEntities (Dungeon dungeon) {
        manager.setDungeon(dungeon);
        logger.trace("New instance of manager");

        initializePlayer();
        logger.trace("Initialized player");

        initializeEntities();
        logger.trace("Initialized entities");
    }

    /**
     * Cette méthode permet d'initialiser le joueur au centre de la salle actuelle
     * 
     * @see engine.process.management.EntityManager pour les détails du processus de gestion des actions du joueur
     */
    private static void initializePlayer () {
        // On récupère l'instance de Player
        Player player = Player.getInstance();
        // On ajoute le joueur à la liste d'entités
        manager.getCurrentRoom().addEntity(player);
    }

    /**
     * Cette méthode permet d'initialiser les entités de la salle actuelle
     * 
     * @see engine.process.management.EntityManager pour les détails du processus de gestion des actions des entités
     */
    public static void initializeEntities() {

        Room currentRoom = manager.getCurrentRoom();

        initializeEnvironment();
        logger.trace("Initialized environment");

        if(currentRoom.isShop()) {
            // On initialise le vendeur
            initializeVendor();
            // On ouvre directement la porte
            manager.getCurrentRoom().open();
            logger.trace("Initialized vendor");
        }
        else if(currentRoom.isBoss()) {
            initializeBoss();
        }
        else {
            initializeEnemies();
            logger.trace("Initialized ennemies");
        }

    }

    /**
     * Cette méthode permet d'initialiser les ennemis à des endroits aléatoires de la salle.
     * Le type d'ennemi est différent à mesure que le joueur avance dans les salles.
     * 
     * @see engine.process.management.manager pour les détails du processus de gestion des ennemis
     */
    public static void initializeEnemies() {
        Player player = Player.getInstance();
        int stageNumber = player.getStageNumber();
        int enemyCount = player.getStageNumber() * player.getRoomNumber(); 
        for (int i = 0; i < enemyCount; i++) {
            int randomNumber = getRandomNumber(1, 2);
            String enemyType = "";
            if(stageNumber == 1) {
                if(randomNumber == 1) {
                    enemyType = "rat_fistule";
                }
                else {
                    enemyType = "rocky_blateboa";
                }
            }
            else if(stageNumber == 2) {
                if(randomNumber == 1) {
                    enemyType = "crackhead";
                }
                else {
                    enemyType = "chevre";
                }
            }
            else if(stageNumber == 3) {
                if(randomNumber == 1) {
                    enemyType = "secretaire";
                }
                else {
                    enemyType = "professor";
                }
            }
            Enemy enemy = EnemyFactory.createEnemy(enemyType, null); // On instancie des rats fistulés
            randomPlaceEntity(enemy); // On le place de manière aléatoire
            initializeEquipmentOfEnemy(enemy); // On l'équipe
        }
    }

    /**
     * Cette méthode permet d'initialiser le boss de la salle actuelle
     * 
     * @see engine.process.management.manager pour les détails du processus de gestion des ennemis
     */
    public static void initializeBoss() {
        Player player = Player.getInstance();
        int stageNumber = player.getStageNumber();
        if(stageNumber == 1) {
            Enemy boss = EnemyFactory.createEnemy("abomination_des_egouts", null);
            boss.getInventory().addItem(WeaponFactory.createWeapon("epee_legendaire_des_egouts", null));
            randomPlaceEntity(boss);
        }
        else if(stageNumber == 2) {
            Enemy boss = EnemyFactory.createEnemy("gobelin_malefique", null);
            boss.getInventory().addItem(WeaponFactory.createWeapon("arc_mystique_de_gobelin", null));
            randomPlaceEntity(boss);
        }
        else if(stageNumber == 3) {
            Enemy boss = EnemyFactory.createEnemy("derdoudiable", null);
            boss.getInventory().addItem(WeaponFactory.createWeapon("tampon_legendaire_de_derdoudiable", null));
            randomPlaceEntity(boss);
        }
    }
    
    /**
     * Cette méthode permet d'initialiser l'équipement de l'ennemi
     * 
     * @param enemy l'ennemi à équiper
     */
    private static void initializeEquipmentOfEnemy(Enemy enemy) {
        // On génère un nombre aléatoire de pièces
        int randomNumber = getRandomNumber(1, 25);

        // On ajoute à l'inventaire de l'ennemi les pièces
        Coin coins = (Coin)EntityFactory.createEntity(GameConfiguration.COIN_ENTITYTYPE, null);
        coins.setConsumableValue(randomNumber);
        enemy.getInventory().addItem(coins);

        // On donne ou pas à l'ennemi son arme favorite
        if(randomNumber < 12) {
            switch(enemy.getEntityType()) {
                case "rat_fistule":
                    enemy.getEquipment().setWeapon(WeaponFactory.createWeapon("epee_de_rat", null));
                    break;
                case "rocky_blateboa":
                    enemy.getEquipment().setWeapon(WeaponFactory.createWeapon("epee_de_chevalier", null));
                    break;
                case "crackhead":
                    enemy.getEquipment().setWeapon(WeaponFactory.createWeapon("dagues_de_crackhead", null));
                    break;
                case "chevre":
                    enemy.getEquipment().setWeapon(WeaponFactory.createWeapon("arc_de_chevre", null));
                    break;
                case "secretaire":
                    enemy.getEquipment().setWeapon(WeaponFactory.createWeapon("dagues_de_secretaire", null));
                    break;
                case "professor":
                    enemy.getEquipment().setWeapon(WeaponFactory.createWeapon("sceptre_de_professeur", null));
                    break;
                default:
                    break;
            }
        }

        randomNumber = getRandomNumber(1, 3);
        if(randomNumber == 2) {
            switch(enemy.getEntityType()) {
                case "rat_fistule":
                    enemy.getEquipment().setHelmet((Helmet)ClotheFactory.createClothe("chapeau_de_rat", null));
                    break;
                case "rocky_blateboa":
                    enemy.getEquipment().setBoots((Boots)ClotheFactory.createClothe("bottes_remplies_de_blattes", null));
                    break;
                case "crackhead":
                    enemy.getEquipment().setPants((Pants)ClotheFactory.createClothe("pantalon_de_troubadour", null));
                    break;
                case "chevre":
                    enemy.getEquipment().setHelmet((Helmet)ClotheFactory.createClothe("casque_de_chevalier", null));
                    break;
                case "secretaire":
                    enemy.getEquipment().setGloves((Gloves)ClotheFactory.createClothe("gants_d_aventurier", null));
                    break;
                case "professor":
                    enemy.getEquipment().setChestplate((Chestplate)ClotheFactory.createClothe("plastron_de_chevalier", null));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * On initialise l'environnement dans un ordre particulier : les murs avant le reste pour que tout soit contenu à l'intérieur des murs
     */
    public static void initializeEnvironment() {
        Room currentRoom = manager.getCurrentRoom();

        initializeWalls();
        if(!currentRoom.isBoss()) {
            initializeObstacles();
            initializeChest();
            initializeGarbage();
        }
    }

    /**
     * Cette méthode permet d'initialiser les obstacles à des endroits aléatoires de la salle.
     * Le type d'obstacle est différent à mesure que le joueur avance dans les salles.
     */
    public static void initializeObstacles() {
        // on veut entre 7 et 11 arbres
        int obstacleNumber = getRandomNumber(7, 11);
        int stageNumber = Player.getInstance().getStageNumber();
        String entityType = "";
        if(stageNumber == 1) {
            entityType = GameConfiguration.PIPE_ASSET_ENTITYTYPE;
        }
        else if(stageNumber == 2) {
            entityType = GameConfiguration.TREE_ASSET_ENTITYTYPE;
        }
        else if(stageNumber == 3) {
            entityType = GameConfiguration.TABLE_ASSET_ENTITYTYPE;
        }
        for(int i = 0 ; i < obstacleNumber ; i++) {
            Environment environment = EnvironmentFactory.createEnvironment(entityType, null);
            randomPlaceEntity(environment);
        }
    }

    /**
     * Cette méthode permet d'initialiser un coffre à un endroit aléatoire de la salle.
     * Ce coffre contiendra soit une potion de vie, soit un magôt d'une grande valeur.
     */
    public static void initializeChest() {
        Chest chest = (Chest)EntityFactory.createEntity(GameConfiguration.CHEST_ENTITYTYPE, null);
        int randomNumber = getRandomNumber(1, 4);
        if(randomNumber == 1) {
            logger.info("Potion de santé added to chest");
            chest.addItem((Flask)EntityFactory.createEntity("potion_de_sante", null));
        }
        else if(randomNumber == 2) {
            logger.info("Potion de mana added to chest");
            chest.addItem((Flask)EntityFactory.createEntity("potion_de_mana", null));
        }
        else {
            logger.info("Coin added to chest");
            Coin coin = (Coin)EntityFactory.createEntity(GameConfiguration.COIN_ENTITYTYPE, null);
            coin.setConsumableValue(getRandomNumber(70, 180));
            chest.addItem(coin);
        }
        randomPlaceEntity(chest);
    }

    /**
     * Cette méthode permet d'initialiser un vendeur à un endroit aléatoire de la salle.
     * Ce vendeur vendra des objets différents à chaque étage.
     */
    public static void initializeVendor() {
        Vendor vendor = (Vendor)EntityFactory.createEntity(GameConfiguration.VENDOR_ENTITYTYPE, null);
        switch(Player.getInstance().getStageNumber()) {
            case 1:
                vendor.addSellingItem(ClotheFactory.createClothe("casque_des_terres_arides", null), 200);
                vendor.addSellingItem((Flask)EntityFactory.createEntity("potion_de_sante", null), 50);
                vendor.addSellingItem(WeaponFactory.createWeapon("arc_des_terres_arides", null), 300);
                break;
            case 2:
                vendor.addSellingItem(ClotheFactory.createClothe("plastron_des_terres_arides", null), 375);
                vendor.addSellingItem((Flask)EntityFactory.createEntity("potion_de_mana", null), 80);
                vendor.addSellingItem(WeaponFactory.createWeapon("sceptre_des_terres_arides", null), 650);
                break;
            case 3:
                vendor.addSellingItem(ClotheFactory.createClothe("nike_de_clignancourt", null), 425);
                vendor.addSellingItem((Flask)EntityFactory.createEntity("potion_de_sante", null), 100);
                vendor.addSellingItem(WeaponFactory.createWeapon("ak_47", null), 999);
        }
        randomPlaceEntity(vendor);
    }

    /**
     * Cette méthode permet d'initialiser les tas de déchêts à des endroits aléatoires de la salle.
     * Chaque tas de déchêts a une chance de contenir une clé.
     */
    public static void initializeGarbage() {
        // On met entre 6 et 16 piles de déchêts par salle
        int garbageNumber = getRandomNumber(6, 16);
        for(int i = 0 ; i < garbageNumber ; i++) {
            Garbage garbage = (Garbage)EntityFactory.createEntity(GameConfiguration.GARBAGE_ENTITYTYPE, null);
            // On veut qu'il y ai une chance sur 10 qu'une clé se trouve à l'intérieur du tas de détritus
            int keyOdds = getRandomNumber(1, 10);
            if(keyOdds == 10) {
                garbage.addItem((Key)EntityFactory.createEntity(GameConfiguration.KEY_ENTITYTYPE, null));
            }
            randomPlaceEntity(garbage);
        }
    }

    /**
     * Cette méthode permet d'initialiser les murs de la salle actuelle
     */
    public static void initializeWalls() {
        Room currentRoom = manager.getCurrentRoom();
        Environment sideWall = EnvironmentFactory.createEnvironment(GameConfiguration.WALL_ASSET_ENTITYTYPE, null);
        sideWall.getHitbox().drawHitbox(new Position(0, 0), new Position(GameConfiguration.ROOM_LEFT_LIMITATION, GameConfiguration.WINDOW_HEIGHT));
        currentRoom.addEntity(sideWall);

        Environment upperWall = EnvironmentFactory.createEnvironment(GameConfiguration.WALL_ASSET_ENTITYTYPE, null);
        upperWall.getHitbox().drawHitbox(new Position(0, 0), new Position(GameConfiguration.WINDOW_WIDTH, GameConfiguration.ROOM_UPPER_LIMITATION));
        currentRoom.addEntity(upperWall);

        Environment lowerWall = EnvironmentFactory.createEnvironment(GameConfiguration.WALL_ASSET_ENTITYTYPE, null);
        lowerWall.getHitbox().drawHitbox(new Position(0, GameConfiguration.ROOM_LOWER_LIMITATION), new Position(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT));
        currentRoom.addEntity(lowerWall);

        Environment upperGateWall = EnvironmentFactory.createEnvironment(GameConfiguration.WALL_ASSET_ENTITYTYPE, null);
        upperGateWall.getHitbox().drawHitbox(new Position(GameConfiguration.ROOM_RIGHT_LIMITATION, 0), GameConfiguration.GATE_UPPERRIGHT);
        currentRoom.addEntity(upperGateWall);

        Environment lowerGateWall = EnvironmentFactory.createEnvironment(GameConfiguration.WALL_ASSET_ENTITYTYPE, null);
        lowerGateWall.getHitbox().drawHitbox(GameConfiguration.GATE_BOTTOMLEFT, new Position(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT));
        currentRoom.addEntity(lowerGateWall);

        Environment gate = EnvironmentFactory.createEnvironment(GameConfiguration.GATE_ASSET_ENTITYTYPE, null);
        gate.getHitbox().drawHitbox(GameConfiguration.GATE_UPPERLEFT, GameConfiguration.GATE_BOTTOMRIGHT);
        currentRoom.setGate(gate);
        currentRoom.addEntity(gate);
    }

    /**
     * Cette méthode permet de placer une entité de manière aléatoire dans la salle actuelle
     * 
     * @param entity l'entité à placer
     */
    public static void randomPlaceEntity(Entity entity) {
        boolean cannotBePlaced = true;
        while(cannotBePlaced) {
            int randomX = getRandomNumber(0, GameConfiguration.WINDOW_WIDTH);
            int randomY = getRandomNumber(0, GameConfiguration.WINDOW_HEIGHT);
            Position randomPosition = new Position(randomX, randomY);
            entity.setPosition(randomPosition);
            // Si la hitbox de l'ennemi n'est en collision avec aucune autre dans la Room
            if(manager.verifHitboxes(entity, entity.getHitbox()) && randomPosition.notNearGate()) {
                manager.getCurrentRoom().addEntity(entity); // On ajoute l'entité à la liste d'entités de la Room
                logger.info(entity.getEntityType() + " added to room");
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
