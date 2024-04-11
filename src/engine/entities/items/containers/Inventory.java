package engine.entities.items.containers;

import config.GameConfiguration;
import engine.entities.Coin;

public class Inventory extends Container {
    
    public Inventory() {
        super(null, GameConfiguration.INVENTORY_NAME, GameConfiguration.INVENTORY_ENTITYTYPE, GameConfiguration.INVENTORY_MAX);
    }

}
