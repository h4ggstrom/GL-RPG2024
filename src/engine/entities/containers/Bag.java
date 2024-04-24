package engine.entities.containers;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.characters.GameCharacter;
import engine.entities.items.Item;
import engine.entities.items.Slot;
import engine.entities.items.equipment.*;
import engine.process.visitor.EntityVisitor;

public class Bag extends Container {

    public Bag (Position position) {
        super(position, GameConfiguration.BAG_NAME, GameConfiguration.BAG_ENTITYTYPE, GameConfiguration.BAG_MAX);
    }

    /**
     * Cette méthode permet de remplir le sac avec les Item que porte un GameCharacter
     * @param character
     */
    public void fillBagWithGameCharacterItems(GameCharacter character) {
        // On commence par sortir son Equipment et l'ajouter au sac
        Equipment equipment = character.getEquipment();
        for(Item item : equipment.getEquipmentList()) {
            this.addItem(item);
        }
        // On parcourt l'inventaire et on ajoute chaque item au sac
        Inventory inventory = character.getInventory();
        for (Slot slot : inventory.getSlots()) {
            Item item = slot.getItem();
            this.addItem(item);
        }
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}

}
