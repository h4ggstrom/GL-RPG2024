package engine.entities.items.consumables;

import config.GameConfiguration;
import engine.dungeon.Position;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe gère la monnaie du jeu.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Coin extends Consumable {

    public Coin(Position position) {
        super("money", 0, GameConfiguration.COIN_NAME, GameConfiguration.COIN_ENTITYTYPE, position);
    }
    
}
