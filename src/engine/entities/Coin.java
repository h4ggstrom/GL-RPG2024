package engine.entities;

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
public class Coin extends Entity {
    private int value;

    public Coin(Position position, int value) {
        super(position, GameConfiguration.COIN_NAME, GameConfiguration.COIN_ENTITYPE);
        this.value = GameConfiguration.COIN_VALUE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
