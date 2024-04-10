package engine.process;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.characters.Enemy;
import engine.entities.characters.Player;
import engine.entities.items.weapons.Sword;
import engine.entities.items.equipment.*;
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
        for (int i = 0; i < (manager.getPlayer().getCurrentStage() * manager.getPlayer().getCurrentRoom()); i++) {
            int enemyX = getRandomNumber(GameConfiguration.ROOM_LEFT_LIMITATION + GameConfiguration.ENEMY_WIDTH/2, GameConfiguration.ROOM_RIGHT_LIMITATION - GameConfiguration.ENEMY_WIDTH/2);
            int enemyY = getRandomNumber(GameConfiguration.ROOM_UPPER_LIMITATION + GameConfiguration.ENEMY_HEIGHT/2, GameConfiguration.ROOM_LOWER_LIMITATION - GameConfiguration.ENEMY_HEIGHT/2);
            Position position = new Position(enemyX, enemyY); // On instancie sa position
            Enemy enemy = (Enemy)EntityFactory.createEntity("enemy", position); // On instancie l'Enemy

            // Partie stuff de l'Enemy
            enemy.getEquipment().setWeapon((Sword)EntityFactory.createEntity(GameConfiguration.SWORD_ENTITYTYPE, null));
            enemy.getEquipment().setHelmet((Helmet)EntityFactory.createEntity(GameConfiguration.HELMET_ENTITYTYPE, null));
            enemy.getEquipment().setGloves((Gloves)EntityFactory.createEntity(GameConfiguration.GLOVES_ENTITYTYPE, null));
            enemy.getEquipment().setChestplate((Chestplate)EntityFactory.createEntity(GameConfiguration.CHESTPLATE_ENTITYTYPE, null));
            enemy.getEquipment().setPants((Pants)EntityFactory.createEntity(GameConfiguration.PANTS_ENTITYTYPE, null));
            enemy.getEquipment().setBoots((Boots)EntityFactory.createEntity(GameConfiguration.BOOTS_ENTITYTYPE, null));

            // Si la hitbox de l'ennemi n'est en collision avec aucune autre dans la Room
            if(manager.verifHitboxes(enemy.getHitbox()) || enemy.getHitbox().isInCollision(Player.getInstance().getHitbox())) {
                manager.getRoom().addEntity(enemy); // On ajoute l'ennemi à la liste d'entités de la Room
            }
            else {
                i--; // Sinon on refait un tour de boucle
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
    private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
    }

}
