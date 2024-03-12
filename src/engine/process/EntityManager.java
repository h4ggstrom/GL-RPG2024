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
import engine.characters.GameCharacter;
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
     * Cette méthode gère les déplacements du personnage
     *
     * @param direction l'input envoyé par le personnage, au format String
     */
    public void moveCharacter (GameCharacter character, String direction) {
        Position startPosition = character.getHitbox().getCenter();
        Position endPosition;
        Boolean canBeMoved = true;
        String entityType = "";
        int speed = 0;
        if (character instanceof Player) {
            logger.trace("L'entité sélectionnée pour être déplacée est le joueur.");
            speed = GameConfiguration.PLAYER_DEFAULT_SPEED;
            entityType = "player";
        }
        else if (character instanceof Enemy) {
            logger.trace("L'entité sélectionnée pour être déplacée est un ennemi.");
            speed = GameConfiguration.ENEMY_DEFAULT_SPEED;
            entityType = "enemy";
        }
        // Switch case pour calculer la nouvelle position
        switch (direction) {
            case "up":
                endPosition = new Position(startPosition.getX(), startPosition.getY() - speed);
                break;
            case "left":
                endPosition = new Position(startPosition.getX() - speed, startPosition.getY());
                break;
            case "down":
                endPosition = new Position(startPosition.getX(), startPosition.getY() + speed);
                break;
            case "right":
                endPosition = new Position(startPosition.getX() + speed, startPosition.getY());
                break;
            default:
                endPosition = startPosition; // Sinon, on garde la même position
                break;
        }

        Hitbox finaleHitbox = new Hitbox(endPosition, entityType, character); // On instancie la Hitbox sur l'emplacement final

        // Si la position finale du personnage n'est pas dans les limites de la Room
        if ( ! checkRoomBounds(finaleHitbox) ) {
            logger.trace("La position finale du " + entityType + "n'est pas dans les limites de la Room");
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
        else {
            logger.trace("La position du " + entityType + " est dans les limites de la room");
        }

        if(canBeMoved) {
            logger.trace("On vérifie la collision avec les Hitbox des entités de la Room");
            room.removeEntity(character); // on retire l'Entité de la Room pour ne pas vérifier la collision avec sa propre hitbox
            canBeMoved = verifHitboxes(finaleHitbox);
            logger.trace("Verification de canBeMoved après la verification des hitboxs : " + canBeMoved);
            room.addEntity(character); // on replace l'entité dans le Room
        }


        if (canBeMoved) { // Si on a jugé que le personnage peut se déplacer
            logger.trace(entityType + " peut se déplacer.");
            logger.trace(character + "moved " + direction);
            character.setHitbox(finaleHitbox); // On associe la nouvelle Hitbox
        }

        if (character.getHitbox().getCenter().getX() > GameConfiguration.WINDOW_WIDTH) {
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
            // Si la distance entre l'Item est le joueur est assez restreinte et qu'on a de la place dans l'inventaire, on peut intéragir
            if(distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE && player.getInventory().getNumberOfItems() < GameConfiguration.INVENTORY_MAX) {
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

    /**
     * set the next room by cleaning the current one and generating a new one
     */
    public void nextRoom() {
        room.empty();
        player.setPosition(new Position(GameConfiguration.ROOM_CENTER_X, GameConfiguration.ROOM_CENTER_Y));
        GameBuilder.initializeEnemies(this);
    }

    public boolean verifHitboxes(Hitbox finaleHitbox) {
        boolean verif = true;
        // On parcourt toutes les Hitbox d'Entity de la Room
        for (Entity entity : room.getEntities()) {
            Hitbox hitbox = entity.getHitbox();
            logger.trace("Les deux hitboxs à inspecter : " + hitbox + " et " + finaleHitbox);
            if ( hitbox.isInCollision(finaleHitbox) ) { // Si la Hitbox finale est en collision avec une des Hitbox de la salle
                logger.trace("finaleHitbox.isInCollision(hitbox) = " + finaleHitbox.isInCollision(hitbox));
                verif = false; // Il ne peut pas être déplacé
            }
        }
        return verif;
    }

    /**
     * Cette méthode génére les déplacement des ennemis.
     */
    public void moveEnemies() {
        ArrayList<Enemy> enemiesFetched = new ArrayList<Enemy>();
        // Pour chaque entité présente dans la salle
        for (Entity entity : room.getEntities()) {
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                enemiesFetched.add(enemy);
            }
        }
        for (Enemy enemy : enemiesFetched) {
            Position enemyPosition = enemy.getHitbox().getCenter();
            Position playerPosition = player.getHitbox().getCenter();
    
            // Calcul de la direction optimale pour se rapprocher du joueur
            int dx = playerPosition.getX() - enemyPosition.getX();
            logger.trace("La différence de x entre l'ennemi et le joueur est " + dx);
            int dy = playerPosition.getY() - enemyPosition.getY();
    
            if(dx != 0) {
                moveCharacter(enemy, dx > 0 ? "right" : "left");
            }

            if(dy != 0) {
                moveCharacter(enemy, dy > 0 ? "down" : "up");
            }
        }
    }

    /**
     * Vérifie si la Hitbox donnée est dans les limites de la salle.
     * @param hitbox La Hitbox à vérifier.
     * @return true si la Hitbox est dans les limites, false sinon.
     */
    private boolean checkRoomBounds(Hitbox hitbox) {
        return (GameConfiguration.ROOM_LEFT_LIMITATION < hitbox.getUpperLeft().getX() &&
                hitbox.getUpperRight().getX() < GameConfiguration.ROOM_RIGHT_LIMITATION) &&
                (GameConfiguration.ROOM_UPPER_LIMITATION < hitbox.getUpperRight().getY() &&
                hitbox.getBottomRight().getY() < GameConfiguration.ROOM_LOWER_LIMITATION);
    }
    
    public void attackPlayer(){
        for (Entity entity : room.getEntities()) {
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
            }
        }
    }
}
