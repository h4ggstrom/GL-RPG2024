package engine.process.visitor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.characters.Enemy;
import engine.entities.characters.Player;
import engine.entities.containers.Bag;
import engine.entities.containers.Chest;
import engine.entities.containers.Garbage;
import engine.entities.containers.Inventory;
import engine.entities.environment.Environment;
import engine.entities.items.Item;
import engine.entities.items.Key;
import engine.entities.items.Slot;
import engine.entities.npc.Vendor;
import engine.process.management.EntityFactory;
import engine.process.management.EntityManager;
import engine.process.management.GameBuilder;
import gui.containersGUI.BagGUI;
import gui.containersGUI.ChestGUI;
import gui.containersGUI.VendorGUI;
import log.Gamelog;

public class InteractionVisitor implements EntityVisitor<Void>{

    private static Logger logger = Gamelog.getLogger();
    private static EntityManager manager = EntityManager.getInstance();
    private Player player = Player.getInstance();
    private Position playerPosition;

    public InteractionVisitor() {
        playerPosition = player.getPosition();
    }
    
    @Override
    public Void visit(Enemy entity) {
        // On récupère la distance entre le joueur et l'ennemi
        int distance = manager.calculateDistance(playerPosition, entity.getPosition());
        logger.trace("Distance to selected Enemy = " + distance);

        List<Enemy> eliminatedEnemies = new ArrayList<Enemy>();

        // On vérifie si l'ennemi est compris dans la range de l'arme du joueur
        if(distance <= player.getAttackRange() && player.canAttack()) {
            logger.trace("Enemy attacked");
            // Si c'est le cas, on attaque l'Enemy visé avec les dégâts du joueur
            entity.hurtCharacter(player.getAttackDamage());;
            logger.trace("Enemy now has "+ entity.getHealth()+ " HP");
            // On indique que le joueur ne peut plus attaquer
            player.setCanAttack(false);
        }

        // Si la vie de l'Enemy atteint 0 (ou moins)
        if (entity.getHealth() <= 0) {
            // On l'ajoute à la liste d'Enemy éliminés
            eliminatedEnemies.add(entity);
            logger.trace("enemy eliminated");
            // Le joueur gagne de l'EXP
            player.gainExp(GameBuilder.getRandomNumber(100,200));
            // On rafraîchit les potentielles fenêtres ouvertes
            manager.refreshContainers();
        }

        // On parcourt les Enemy éliminés pour les retirer du jeu et faire tomber leur sac
        for (Enemy eliminatedEnemy : eliminatedEnemies) {
            // On crée un sac
            Bag bag = (Bag)EntityFactory.createEntity(GameConfiguration.BAG_ENTITYTYPE, eliminatedEnemy.getPosition());
            // On le remplit avec tout ce qu'il y a sur l'Enemy
            bag.fillBagWithGameCharacterItems(entity);
            // On ajoute le sac à la liste d'entités de la Room
            manager.getCurrentRoom().addEntity(bag);
            // on retire l'Enemy de la liste d'entités de la Room
            manager.getCurrentRoom().removeEntity(entity);
        }

        // On vérifie finalement si il ne reste plus aucun ennemis
        Boolean hasBeenCleaned = true;
        for(Entity inRoomEntity : manager.getCurrentRoom().getEntities()) {
            if (inRoomEntity instanceof Enemy) {
                hasBeenCleaned = false;
            }
        }

        // Si plus aucun ennemi ne reste
        if(hasBeenCleaned) {
            manager.getCurrentRoom().open();
        }
        return null;
    }

    @Override
    public Void visit(Player entity) {
        return null;
    }

    @Override
    public Void visit(Item entity) {
        int distance = manager.calculateDistance(playerPosition, entity.getPosition());
        logger.trace("Distance to selected item = " + distance);
        if(distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE) {
            // Le joueur ramasse l'Item et l'ajoute à son inventaire
            logger.trace("item fetched");
            player.getInventory().addItem(entity); // ajout à l'inventaire
            manager.getCurrentRoom().removeEntity(entity); // on retire l'item de la room
        }
        return null;
    }

    @Override
    public Void visit(Bag entity) {
        int distance = manager.calculateDistance(playerPosition, entity.getPosition());
        logger.trace("Distance to selected item = " + distance); 
        if(distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE) {
            new BagGUI(entity);
        }
        return null;
    }

    @Override
    public Void visit(Vendor entity) {
        int distance = manager.calculateDistance(playerPosition, entity.getPosition());
        logger.trace("Distance to selected item = " + distance);
        if(distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE) {
            new VendorGUI(entity);
        }
        return null;
    }

    @Override
    public Void visit(Environment entity) {
        return null;
    }

    @Override
    public Void visit(Inventory entity) {
        return null;
    }

    @Override
    public Void visit(Garbage entity) {
        int distance = manager.calculateDistance(playerPosition, entity.getPosition());
        logger.trace("Distance to selected item = " + distance);
        if(distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE) {
            // On retire la pile de détritus de la room
            manager.getCurrentRoom().removeEntity(entity);
            Item item = entity.getSlots().get(0).getItem();
            // Si la pile de détritus contient bien un item
            if(item != null) {
                item.setPosition(entity.getPosition());
                manager.getCurrentRoom().addEntity(item);
            }
        }
        return null;
    }

    @Override
    public Void visit(Chest entity) {
        int distance = manager.calculateDistance(playerPosition, entity.getPosition());
        logger.trace("Distance to selected item = " + distance);
        if(distance <= GameConfiguration.PLAYER_ENTITY_INTERACTION_RANGE) {
            if(!entity.isLocked()) {
                new ChestGUI(entity);
            }
            else {
                // On vérifie si le joueur à une clé
                ArrayList<Slot> slots = player.getInventory().getSlots();
                for (Slot slot : slots) {
                    Item item = slot.getItem();
                    if(item instanceof Key) {
                        // On supprime la clé
                        slot.setItem(null);
                        // On ouvre le coffre
                        entity.setLocked(false);
                        // On rafraîchit les potientiels GUI ouverts
                        manager.refreshContainers();
                    }
                }
            }
        }
        return null;
    }

}
