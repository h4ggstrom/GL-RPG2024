package engine.dungeon;

import java.io.Serializable;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient toutes les données relatives à la position d'une entité.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Position implements Serializable {

    // définition des attributs.
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;

    /**
     * Constructeur par défaut. Génère une nouvelle instance de position à partir de deux coordonnées en pixels.
     * 
     * @param x coordonnée horizontale
     * @param y coordonnée verticale
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Pixel [x=" + x + ", y=" + y + "]";
    }
}
