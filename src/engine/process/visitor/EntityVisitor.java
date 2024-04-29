package engine.process.visitor;

import engine.entities.characters.Enemy;
import engine.entities.characters.Player;
import engine.entities.containers.Bag;
import engine.entities.containers.Chest;
import engine.entities.containers.Garbage;
import engine.entities.containers.Inventory;
import engine.entities.environment.Environment;
import engine.entities.items.Item;
import engine.entities.npc.Vendor;

/**
 * Interface définissant les méthodes de visite des entités.
 * 
 * @param <E> le type de retour de la visite
 */
public interface EntityVisitor<E> {
    
    E visit(Enemy entity);

    E visit(Item entity);

    E visit(Bag entity);

    E visit(Vendor entity);

    E visit(Garbage entity);

    E visit(Chest entity);

    E visit(Player entity);

    E visit(Environment entity);

    E visit(Inventory inventory);

}
