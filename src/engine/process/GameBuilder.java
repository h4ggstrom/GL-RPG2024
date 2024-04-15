package engine.process;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.characters.Enemy;
import engine.entities.characters.Player;
import engine.entities.environment.TreeEnv;
import engine.entities.items.Coin;
import engine.entities.items.equipment.*;
import engine.entities.items.weapons.*;
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
     * génère la salle et les ennemis à l'apparition de la salle en utilisant le constructeur de la classe {@link src.engine.characters.EntityManager}
     * 
     * @param room la salle dans laquelle évolue le joueur
     * @return le système de gestion de la partie. Pour plus de détails, voir {@link src.engine.characters.EntityManager}
     */
    public static EntityManager buildInitCharacters (Dungeon dungeon) {
        EntityManager manager = new EntityManager(dungeon);
        logger.trace("New instance of EntityManager");

        initializePlayer(manager);
        logger.trace("Initialized player");

        initializeEnemies(manager);
        logger.trace("Initialized ennemies");

        initializeTreesInRoom(manager);

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
        manager.getRoom().addEntity(player);
        manager.set(player);
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
        int currentRoom = player.getCurrentRoom();
        int enemyCount = 0; 
        for (int i = 0; i < (manager.getPlayer().getCurrentStage() * manager.getPlayer().getCurrentRoom()); i++) {
            int enemyX = getRandomNumber(GameConfiguration.ROOM_LEFT_LIMITATION + GameConfiguration.ENEMY_WIDTH/2, GameConfiguration.ROOM_RIGHT_LIMITATION - GameConfiguration.ENEMY_WIDTH/2);
            int enemyY = getRandomNumber(GameConfiguration.ROOM_UPPER_LIMITATION + GameConfiguration.ENEMY_HEIGHT/2, GameConfiguration.ROOM_LOWER_LIMITATION - GameConfiguration.ENEMY_HEIGHT/2);
            Position position = new Position(enemyX, enemyY); // On instancie sa position
            Enemy enemy = (Enemy)EntityFactory.createEntity("enemy", position); // On instancie l'Enemy

            // Partie stuff de l'Enemy
            initializeEquipmentToEnemy(enemy, currentRoom, enemyCount);
            

            // Si la hitbox de l'ennemi n'est en collision avec aucune autre dans la Room
            if(manager.verifHitboxes(enemy.getHitbox()) || enemy.getHitbox().isInCollision(Player.getInstance().getHitbox())) {
                manager.getRoom().addEntity(enemy); // On ajoute l'ennemi à la liste d'entités de la Room
                enemyCount++;
            }
            else {
                i--; // Sinon on refait un tour de boucle
            }
        }
    }
    
    private static void initializeEquipmentToEnemy(Enemy enemy, int currentRoom, int enemyCount) {
        // On équipe un enemy sur deux d'un Scepter
        if ( currentRoom > 2 && enemyCount % 2 == 0) {
            enemy.getEquipment().setWeapon((Weapon)EntityFactory.createEntity(GameConfiguration.SCEPTER_ENTITYTYPE, null));
        } else {
            enemy.getEquipment().setWeapon((Weapon)EntityFactory.createEntity(GameConfiguration.SWORD_ENTITYTYPE, null));
        }

        // On génère ensuite un nombre aléatoire entre 1 et 5
        int min = 1;
        int max = 5;
        int randomNumber = (int)Math.floor(Math.random() *(max - min + 1) + min);

        // L'ennemi aura un des 5 habits en équipement
        switch(randomNumber) {
            case 1:
                enemy.getEquipment().setHelmet((Helmet)EntityFactory.createEntity(GameConfiguration.HELMET_ENTITYTYPE, null));
                break;
            case 2:
                enemy.getEquipment().setGloves((Gloves)EntityFactory.createEntity(GameConfiguration.GLOVES_ENTITYTYPE, null));
                break;
            case 3:
                enemy.getEquipment().setChestplate((Chestplate)EntityFactory.createEntity(GameConfiguration.CHESTPLATE_ENTITYTYPE, null));
                break;
            case 4:
                enemy.getEquipment().setPants((Pants)EntityFactory.createEntity(GameConfiguration.PANTS_ENTITYTYPE, null));
                break;
            case 5:
                enemy.getEquipment().setBoots((Boots)EntityFactory.createEntity(GameConfiguration.BOOTS_ENTITYTYPE, null));
                break;
        }
        
        // On génère ensuite un nombre aléatoire entre 1 et 10 * le numéro de la room
        min = 1;
        max = 10*currentRoom;
        randomNumber = (int)Math.floor(Math.random() *(max - min + 1) + min);

        // On ajoute cette fois-ci à l'inventaire de l'ennemi des pièces
        Coin coins = (Coin)EntityFactory.createEntity(GameConfiguration.COIN_ENTITYTYPE, null);
        coins.setValue(randomNumber);
        enemy.getInventory().addItem(coins);
    }
    
    /**
     * Cette méthode génère un nombre aléatoire compris entre un minimum et un maximum
     * 
     * @param min le nombre minimum
     * @param max le nombre maximum
     * @return le nombre aléatoire généré, formatté en entier
     */
    private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
    }

    public static void initializeTreesInRoom(EntityManager manager) {
        ArrayList<TreeEnv> trees = new ArrayList<>();

        Position TreePosition1 = new Position(300, 500);
        TreeEnv Tree1 = (TreeEnv)EntityFactory.createEntity("tree", TreePosition1);
        trees.add(Tree1);

        Position TreePosition2 = new Position(550, 200);
        TreeEnv Tree2 = (TreeEnv)EntityFactory.createEntity("tree", TreePosition2);
        trees.add(Tree2);

        Position TreePosition3 = new Position(800, 500);
        TreeEnv Tree3 = (TreeEnv)EntityFactory.createEntity("tree", TreePosition3);
        trees.add(Tree3);

        for (TreeEnv tree : trees) {
            manager.getRoom().addEntity(tree);
        }
    }

}
