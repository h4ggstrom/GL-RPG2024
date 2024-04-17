package engine.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.dungeon.Dungeon;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.entities.Entity;
import engine.entities.Hitbox;
import engine.entities.characters.Enemy;
import engine.entities.characters.GameCharacter;
import engine.entities.characters.Player;
import engine.entities.containers.Bag;
import engine.entities.items.Item;
import engine.entities.items.Slot;
import engine.entities.items.consumables.Coin;
import engine.entities.items.consumables.Consumable;
import engine.entities.items.weapons.*;
import engine.entities.items.equipment.*;
import gui.containersGUI.BagGUI;
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
    private Player player = Player.getInstance(); // le joueur
    private Dungeon dungeon; // la salle dans laquelle évolue le joueur
    public static ContainerRefreshListener bagRefreshListener;
    public static ContainerRefreshListener inventoryRefreshListener;

    /**
     * Constructeur par défaut. Génère une nouvelle instance de CharacterManager.
     * @param room la salle dans laquelle évoluera le joueur
     */
    public EntityManager (Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }

    /**
     * Méthode permettant de retourner l'instance de la Room ou le joueur se trouve
     * @return room instance de la Room ou le joueur se trouve
     */
    public Room getCurrentRoom() {
        return this.dungeon.getStages().get(player.getstageNumber() - 1).getRooms().get(player.getRoomNumber() - 1);
    }

    /**
     * Cette méthode gère les déplacements du personnage
     *
     * @param direction l'input envoyé par le personnage, au format String
     */
    public void moveCharacter (GameCharacter character, String direction) {
        Position startPosition = character.getHitbox().getCenter();
        Position endPosition;
        String entityType = character.getEntityType();
        int speed = character.getMoveSpeed();
        if (character instanceof Player) {
            logger.trace("L'entité sélectionnée pour être déplacée est le joueur.");
        }
        else if (character instanceof Enemy) {
            logger.trace("L'entité sélectionnée pour être déplacée est un ennemi.");
        }

        // Pour maintenir une vitesse constante dans toutes les directions
        int diagonalSpeed = (int) (speed / Math.sqrt(2));
        // Switch case pour calculer la nouvelle position
        switch (direction) {
            case "up":
                endPosition = new Position(startPosition.getX(), startPosition.getY() - speed);
            break;
            case "down":
                endPosition = new Position(startPosition.getX(), startPosition.getY() + speed);
                break;
            case "left":
                endPosition = new Position(startPosition.getX() - speed, startPosition.getY());
                break;
            case "right":
                endPosition = new Position(startPosition.getX() + speed, startPosition.getY());
                break;
            case "up-right":
                endPosition = new Position(startPosition.getX() + diagonalSpeed, startPosition.getY() - diagonalSpeed);
                break;
            case "up-left":
                endPosition = new Position(startPosition.getX() - diagonalSpeed, startPosition.getY() - diagonalSpeed);
                break;
            case "down-right":
                endPosition = new Position(startPosition.getX() + diagonalSpeed, startPosition.getY() + diagonalSpeed);
                break;
            case "down-left":
                endPosition = new Position(startPosition.getX() - diagonalSpeed, startPosition.getY() + diagonalSpeed);
            break;
            default:
                endPosition = startPosition; // Sinon, on garde la même position
                break;
        }

        Hitbox finaleHitbox = new Hitbox(endPosition, entityType, character); // On instancie la Hitbox sur l'emplacement final

        Boolean canBeMoved = true;

        logger.trace("On vérifie la collision avec les Hitbox des entités de la Room");
        this.getCurrentRoom().removeEntity(character); // on retire le personnage de la Room pour ne pas vérifier la collision avec sa propre hitbox
        canBeMoved = verifHitboxes(finaleHitbox); // on vérifie que la Hitbox finale n'est en collision avec aucune autre
        logger.trace("Verification de canBeMoved après la verification des hitboxs : " + canBeMoved);
        this.getCurrentRoom().addEntity(character); // on replace l'entité dans le Room

        if (canBeMoved) { // Si on a jugé que le personnage peut se déplacer
            logger.trace(entityType + " peut se déplacer.");
            logger.trace(character + "moved " + direction);
            character.setHitbox(finaleHitbox); // On associe la nouvelle Hitbox
        }

        // Si le personnage sort des limites de l'écran
        if (character.getHitbox().getCenter().getX() > GameConfiguration.WINDOW_WIDTH || character.getHitbox().getCenter().getY() > GameConfiguration.WINDOW_HEIGHT) {
            // Il sort de la Room
            this.nextRoom();
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
        for(Entity entity : this.getCurrentRoom().getEntities()) {
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

            // On vérifie si le click du joueur est compris dans la range de son arme
            if(distance <= player.getAttackRange() && player.canAttack()) {
                logger.trace("enemy attacked");
                // Si c'est le cas, on attaque l'Enemy visé avec l'arme du joueur
                selectedEnemy.hurtCharacter(player.getAttackDamage());;
                logger.trace("enemy now has "+ selectedEnemy.getHealth()+ " HP");
                // On indique que le joueur ne peut plus attaquer
                player.setCanAttack(false);
            }

            // Si la vie de l'Enemy atteint 0 (ou moins)
            if (selectedEnemy.getHealth() <= 0) {
                // On l'ajoute à la liste d'Enemy éliminés
                eliminatedEnemies.add(selectedEnemy);
                logger.trace("enemy eliminated");
            }

            // On parcourt les Enemy éliminés pour les retirer du jeu et faire tomber leur sac
            for (Enemy eliminatedEnemy : eliminatedEnemies) {
                // On crée un sac
                Bag bag = (Bag)EntityFactory.createEntity(GameConfiguration.BAG_ENTITYTYPE, eliminatedEnemy.getPosition());
                // On le remplit avec tout ce qu'il y a sur l'Enemy
                bag.fillBagWithGameCharacterItems(selectedEnemy);
                // On ajoute le sac à la liste d'entités de la Room
                this.getCurrentRoom().addEntity(bag);
                // on retire l'Enemy de la liste d'entités de la Room
                this.getCurrentRoom().removeEntity(selectedEnemy);
            }

            // On vérifie finalement si il ne reste plus aucun ennemis
            Boolean hasBeenCleaned = true;
            for(Entity entity : this.getCurrentRoom().getEntities()) {
                if (entity instanceof Enemy) {
                    hasBeenCleaned = false;
                }
            }

            // Si plus aucun ennemi ne reste
            if(hasBeenCleaned) {
                this.getCurrentRoom().open();
                Position healthPosition = new Position(GameConfiguration.ROOM_CENTER_X, GameConfiguration.ROOM_UPPER_LIMITATION + GameConfiguration.HEALTHFLASK_HEIGHT/2);
                Consumable healthPotion = (Consumable)EntityFactory.createEntity("healthFlask", healthPosition);
                this.getCurrentRoom().addEntity(healthPotion);
            }
        }

        // Si l'entité sélectionnée est un Sac et qu'on est à portée de l'ouvrir
        else if(selectedEntity instanceof Bag && distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE) {
            Bag bag = (Bag)selectedEntity;
            new BagGUI(this, bag);
        }

        // Si l'entité sélectionnée est un Item
        else if(selectedEntity instanceof Item) {

            logger.trace("item selected");

            Item selectedItem = (Item)selectedEntity;
            // Si la distance entre l'Item est le joueur est assez restreinte et qu'on a de la place dans l'inventaire, on peut intéragir
            if(distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE && player.getInventory().getNumberOfItems() < GameConfiguration.INVENTORY_MAX) {
                // Le joueur ramasse l'Item et l'ajoute à son inventaire
                logger.trace("item fetched");
                player.getInventory().addItem(selectedItem); // ajout à l'inventaire
                this.getCurrentRoom().removeEntity(selectedItem); // on retire l'item de la room
            }
        }

        else if(selectedEntity instanceof Coin){

            Coin selectedCoin = (Coin)selectedEntity;

            logger.trace("coin selected");
            if(distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE) {
                // Le joueur ramasse le coin
                player.addCoins(selectedCoin.getConsumableValue());
                this.getCurrentRoom().removeEntity(selectedCoin); 
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
     * Méthode pour passer à la Room suivante
     */
    public void nextRoom() {
        // On incrémente le numéro de la room
        player.setRoomNumber(player.getRoomNumber() + 1);
        // On récupère cette nouvelle Room
        Room nextRoom = this.getCurrentRoom();
        // On place le joueur au milieu de la Room
        player.setPosition(new Position(GameConfiguration.ROOM_CENTER_X, GameConfiguration.ROOM_CENTER_Y));
        // On ajoute le joueur à la liste d'entités
        nextRoom.addEntity(player);
        // On génère les entités de cette room
        GameBuilder.initializeEntities(this);
    }

    /**
     * Cette méthode permet de parcourir toutes les Hitbox de la Room et de vérifier qu'aucune d'entre elles n'est en collision avec la Hitbox fournie
     * On parcourt les Hitbox mais on ne modifie rien donc on veille à parcourir l'ArrayList statique pour éviter des erreurs de commodification
     * @param finaleHitbox la hitbox à analyser
     * @return un booléen nous disant si la hitbox est en collision avec une entité
     */
    public boolean verifHitboxes(Hitbox finaleHitbox) {
        boolean verif = true;
        // On parcourt toutes les Hitbox d'Entity de la Room
        for (Entity entity : this.getCurrentRoom().getStaticEntities()) {
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
        for (Enemy enemy : getCurrentRoom().getEnemies()) {
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

    public void attackforEnemy(){
        for (Enemy enemy : getCurrentRoom().getEnemies()) {
            Position enemyPosition = enemy.getHitbox().getCenter();
            Position playerPosition = player.getHitbox().getCenter();
            int distanceEnemyPlayer = calculateDistance(enemyPosition, playerPosition);
            if(distanceEnemyPlayer <= enemy.getAttackRange() && enemy.canAttack()){ 
                // On blesse le joueur
                player.hurtCharacter(enemy.getAttackDamage());
                // On indique que l'ennemi à attaqué et ne peut plus attaquer 
                enemy.setCanAttack(false);
                // On indique aux conteneurs si ils sont ouverts que des nouvelles valeurs pour les PV sont à afficher
                refreshContainers();
            }
        }  
    }

    public void gameOver(){
        this.getCurrentRoom().empty();
    }

    /**
     * Méthode récupérant un Slot de l'inventaire et équipant l'item contenu si la place n'est pas déjà prise dans le slot d'équipement correspondant
     * @param slot
     */
    public void equipInventoryItem(int slotNumber) {
        Slot slot = player.getInventory().getSlots().get(slotNumber);
        Item item = slot.getItem();
        if(item instanceof Weapon) {
            if(player.getEquipment().getWeapon() == null) {
                player.getEquipment().setWeapon((Weapon)item);
            }
        }
        else if(item instanceof Helmet) {
            if(player.getEquipment().getHelmet() == null) {
                player.getEquipment().setHelmet((Helmet)item);
            }
        }
        else if(item instanceof Gloves) {
            if(player.getEquipment().getGloves() == null) {
                player.getEquipment().setGloves((Gloves)item);
            }
        }
        else if(item instanceof Chestplate) {
            if(player.getEquipment().getChestplate() == null) {
                player.getEquipment().setChestplate((Chestplate)item);
            }
        }
        else if(item instanceof Pants) {
            if(player.getEquipment().getPants() == null) {
                player.getEquipment().setPants((Pants)item);
            }
        }
        else if(item instanceof Boots) {
            if(player.getEquipment().getBoots() == null) {
                player.getEquipment().setBoots((Boots)item);
            }
        }
        // On supprime l'item de l'inventaire
        player.getInventory().removeItem(slotNumber);
        refreshContainers();
    }

    public void desequipInventoryItem(String entityType) {
        if(!player.getInventory().isFull()) {
            switch(entityType) {
                case "weapon":
                    Weapon weapon = player.getEquipment().getWeapon();
                    player.getInventory().addItem(weapon);
                    player.getEquipment().setWeapon(null);
                    break;
                case "helmet":
                    Helmet helmet = player.getEquipment().getHelmet();
                    player.getInventory().addItem(helmet);
                    player.getEquipment().setHelmet(null);
                    break;
                case "gloves":
                    Gloves gloves = player.getEquipment().getGloves();
                    player.getInventory().addItem(gloves);
                    player.getEquipment().setGloves(null);
                    break;
                case "chestplate":
                    Chestplate chestplate = player.getEquipment().getChestplate();
                    player.getInventory().addItem(chestplate);
                    player.getEquipment().setChestplate(null);
                    break;
                case "pants":
                    Pants pants = player.getEquipment().getPants();
                    player.getInventory().addItem(pants);
                    player.getEquipment().setPants(null);
                    break;
                case "boots":
                    Boots boots = player.getEquipment().getBoots();
                    player.getInventory().addItem(boots);
                    player.getEquipment().setBoots(null);
                    break;
            }
            refreshContainers();
        }
    }

    /**
     * Méthode permettant de supprimer un Item de l'inventaire du joueur
     * @param slotNumber le numéro du slot dans l'inventaire
     */
    public void deleteInventoryItem(int slotNumber) {
        player.getInventory().removeItem(slotNumber);
        refreshContainers();
    }

    /**
     * Permet de ramasser un objet dans un sac
     * @param slot
     */
    public void pickupBagItem(Slot slot) {
        if(player.getInventory().getNumberOfItems() < GameConfiguration.INVENTORY_MAX) {
            player.getInventory().addItem(slot.getItem());
            slot.setItem(null);
            refreshContainers();
        }
    }

    public static void refreshContainers() {
        if(bagRefreshListener != null) {
            bagRefreshListener.refreshContainer();
        }
        if(inventoryRefreshListener != null) {
            inventoryRefreshListener.refreshContainer();
        }
    }

    /**
     * Cette méthode permet à certaines valeurs de ticks (en millisecondes) de redonner la possibilité aux personnages d'attaquer
     * @param tick temps du jeu en millisecondes
     */
    public void giveBackAttackPossibility(int tick) {
        if(tick%Player.getInstance().getAttackSpeed() == 0) {
            Player.getInstance().setCanAttack(true);
        }
        
        for(Enemy enemy : getCurrentRoom().getEnemies()) {
            if(tick%enemy.getAttackSpeed() == 0) {
                enemy.setCanAttack(true);
            }
        }
    }

}
