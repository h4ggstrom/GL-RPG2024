package engine.entities.npc;

import java.util.HashMap;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.items.Item;
import engine.process.visitor.EntityVisitor;

/**
 * Classe représentant un vendeur.
 * 
 * @param itemsForSale la liste des items en vente
 */
public class Vendor extends Entity {

    private HashMap<Item, Integer> itemsForSale = new HashMap<Item, Integer>();

    /**
     * Constructeur par défaut. Génère une nouvelle instance de vendeur.
     * 
     * @param position la position du vendeur
     */
    public Vendor(Position position) {
        super(position, GameConfiguration.VENDOR_NAME, GameConfiguration.VENDOR_ENTITYTYPE);
    }

    public HashMap<Item, Integer> getItemsForSale() {
        return itemsForSale;
    }

    public void setItemsForSale(HashMap<Item, Integer> itemsForSale) {
        this.itemsForSale = itemsForSale;
    }

    public void addSellingItem(Item item, int price) {
        if(itemsForSale.size() <= 3) {
            itemsForSale.put(item, price);
        }
    }

    public void removeSellingItem(Item item) {
        itemsForSale.remove(item);
    }

    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}

}
