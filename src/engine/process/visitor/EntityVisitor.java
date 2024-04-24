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

public interface EntityVisitor<E> {
    
    E visit(Enemy entity);

    E visit(Player entity);

    E visit(Item entity);

    E visit(Bag entity);

    E visit(Vendor entity);
    
    E visit(Environment entity);

    E visit(Inventory entity);

    E visit(Garbage entity);

    E visit(Chest entity);

}
