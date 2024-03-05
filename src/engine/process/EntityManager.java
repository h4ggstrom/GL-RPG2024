package engine.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.characters.Player;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.items.Item;
import engine.items.weapons.Weapon;
import engine.Entity;
import engine.characters.Enemy;
import engine.characters.Hitbox;
import log.Gamelog;

/**
 * Génie Logiciel - Projet RPG.
 *
 * Cette classe contient toutes les processus liés aux déplacements et autres actions du joueur dans l'environnement de jeu.
 *
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 *
 */
public class EntityManager {

    // définition des attributs
    private static Logger logger = Gamelog.getLogger();
    private Player player; // le contrôle
    private Room room; // la salle dans laquelle évolue le joueur


    /**
     * Constructeur par défaut. Génère une nouvelle instance de CharacterManager.
     *
     * @param room la salle dans laquelle évoluera le joueur
     */
    public EntityManager (Room room) {
        this.room = room;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void set (Player player) {
        this.player = player;
    }

    public Player getPlayer () {
        return this.player;
    }

    public Room getRoom() {
        return this.room;
    }

    /**
     * Cette méthode gère les déplacements du joueur
     *
     * @param direction l'input envoyé par le joueur, au format String
     */
    public void movePlayer (String direction) {
        Position startPosition = player.getHitbox().getCenter();
        Position endPosition;
        Boolean canBeMoved = true;
        // Switch case pour calculer la nouvelle position
        switch (direction) {
            case "up":
                endPosition = new Position(startPosition.getX(), startPosition.getY() - GameConfiguration.PLAYER_DEFAULT_SPEED);
                break;
            case "left":
                endPosition = new Position(startPosition.getX() - GameConfiguration.PLAYER_DEFAULT_SPEED, startPosition.getY());
                break;
            case "down":
                endPosition = new Position(startPosition.getX(), startPosition.getY() + GameConfiguration.PLAYER_DEFAULT_SPEED);
                break;
            case "right":
                endPosition = new Position(startPosition.getX() + GameConfiguration.PLAYER_DEFAULT_SPEED, startPosition.getY());
                break;
            default:
                endPosition = startPosition; // Sinon, on garde la même position
                break;
        }

        Hitbox finaleHitbox = new Hitbox(endPosition, "player", player); // On instancie la Hitbox sur l'emplacement final

        // Si la position finale du joueur n'est pas dans les limites de la Room
        if ( ! ( ( GameConfiguration.ROOM_LEFT_LIMITATION < finaleHitbox.getUpperLeft().getX() && finaleHitbox.getUpperRight().getX() < GameConfiguration.ROOM_RIGHT_LIMITATION ) && ( GameConfiguration.ROOM_UPPER_LIMITATION < finaleHitbox.getUpperRight().getY() && finaleHitbox.getBottomRight().getY() < GameConfiguration.ROOM_LOWER_LIMITATION ) ) ) {
            // Si la Room n'est pas nettoyée
            if(!room.getCleaned()){
                canBeMoved = false; // Il ne peut pas être déplacé
            }
            // Si la Room est nettoyée, la porte à droite est ouverte
            else {
                // Si il veut se déplacer derrière le mur de droite, entre le haut et le bas de la porte
                if ( ( GameConfiguration.ROOM_RIGHT_LIMITATION < finaleHitbox.getUpperRight().getX() ) && ( (GameConfiguration.GATE_UP.getY() < endPosition.getY()) && (endPosition.getY() < GameConfiguration.GATE_DOWN.getY() - GameConfiguration.PLAYER_HEIGHT))) {
                    canBeMoved = true; // On peut le déplacer
                }
                else {
                    canBeMoved = false;
                }
            }
        }

        // On parcourt toutes les Hitbox d'Entity de la Room
        for (Entity entity : room.getEntities()) {
            Hitbox hitbox = entity.getHitbox();
            if ( finaleHitbox.isInCollision(hitbox) ) { // Si la Hitbox finale du joueur est en collision avec une des Hitbox de la salle
                canBeMoved = false; // Il ne peut pas être déplacé
            }
        }

        if (canBeMoved) { // Si on a jugé que le joueur peut se déplacer
            logger.trace("moved " + direction);
            player.setHitbox(finaleHitbox); // On associe la nouvelle Hitbox 
        }

        if (player.getHitbox().getCenter().getX() > GameConfiguration.WINDOW_WIDTH) {
            room.exit();
        }
    }

    /**
     * Cette méthode gère les interactions du joueur avec les entités.
     *
     * @param click la position du click
     */
    public void interact(Position click) {
        
        // On récupère les coordonnées du centre de la hitbox du joueur
        Position playerCenter = player.getPosition();

        // On récupère la distance entre le click et le centre du joueur
        int distance = this.calculateDistance(playerCenter, click);
        logger.trace("distance to click = " + distance); 

        // Si le click visait une Entity, on la récupère
        Entity selectedEntity = null;
        for(Entity entity : this.room.getEntities()) {
            Hitbox selectedHitbox = entity.getHitbox();
            if(selectedHitbox.isContaining(click)) {
                selectedEntity = entity;
            }
        }

        // Si l'Entity sélectionnée est un Enemy
        if(selectedEntity instanceof Enemy) {

            logger.trace("enemy selected");

            Enemy selectedEnemy = (Enemy)selectedEntity;

            List<Enemy> eliminatedEnemies = new ArrayList<Enemy>();

            // On récupère l'instance de l'arme équipée par le joueur
            Weapon playerWeapon = player.getWeaponSlot().getWeapon();

            // On vérifie si le click du joueur est compris dans la range de son arme
            if(distance <= playerWeapon.getAttackRange()) {
                logger.trace("enemy attacked");
                // Si c'est le cas, on attaque l'Enemy visé avec l'arme du joueur
                selectedEnemy.setHealth(selectedEnemy.getHealth() - playerWeapon.getAttackDamage());
                logger.trace("enemy now has "+ selectedEnemy.getHealth()+ " HP");
            }

            // Si la vie de l'Enemy atteint 0 (ou moins)
            if (selectedEnemy.getHealth() <= 0) {
                // On l'ajoute à la liste d'Enemy éliminés
                eliminatedEnemies.add(selectedEnemy);
                logger.trace("enemy eliminated");
            }

            // On parcourt les Enemy éliminés pour les retirer du jeu
            for (Enemy eliminatedEnemy : eliminatedEnemies) {
                // on récupère l'instance de l'arme équipée de l'ennemi
                Weapon enemyWeapon = eliminatedEnemy.getWeaponSlot().getWeapon();
                // on lui associe la position de mort de l'ennemi
                enemyWeapon.setPosition(eliminatedEnemy.getPosition());
                // on ajoute l'arme à la liste d'entités de la Room
                room.addEntity(enemyWeapon);
                // on retire l'Enemy de la liste d'entités de la Room
                room.removeEntity(selectedEnemy);
            }

            // On vérifie finalement si il ne reste plus aucun ennemis
            Boolean hasBeenCleaned = true;
            for(Entity entity : room.getEntities()) {
                if (entity instanceof Enemy) {
                    hasBeenCleaned = false;
                }
            }

            if(hasBeenCleaned) {
                room.clean();
            }
        }

        // Si l'entité sélectionnée est un Item
        if(selectedEntity instanceof Item) {

            logger.trace("item selected");

            Item selectedItem = (Item)selectedEntity;
            // Si la distance entre l'Item est le joueur est assez restreinte, on peut intéragir
            if(distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE) {
                // Le joueur ramasse l'Item et l'ajoute à son inventaire
                logger.trace("item fetched");
                player.getInventory().addItem(selectedItem); // ajout à l'inventaire
                room.removeEntity(selectedItem); // on retire l'item de la room
            }
        }
    }

    /**
     * Cette méthode permet de calculer la distance entre deux objets en utilisant le théorème de Pytaghore.
     *
     * @param p1 la position du premier objet
     * @param p2 la position du deuxieme objet
     * @return la distance entière entre l'objet p1 et p2
     */
    public int calculateDistance(Position p1, Position p2) {
        return ((int)(Math.sqrt(Math.pow(Math.abs(p1.getX() - p2.getX()), 2) + Math.pow(Math.abs(p1.getY()) - p2.getY(), 2))));
    }

    public void nextRoom() {
        room.empty();
        player.setPosition(new Position(GameConfiguration.ROOM_CENTER_X, GameConfiguration.ROOM_CENTER_Y));
        GameBuilder.initializeEnemies(this);
    }
}
