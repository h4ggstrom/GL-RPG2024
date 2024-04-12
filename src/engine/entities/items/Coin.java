package engine.entities.items;

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
public class Coin extends Item {
    private int value;

    public Coin(Position position) {
        super(position, GameConfiguration.COIN_NAME, GameConfiguration.COIN_ENTITYTYPE);
        this.value = GameConfiguration.COIN_VALUE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
