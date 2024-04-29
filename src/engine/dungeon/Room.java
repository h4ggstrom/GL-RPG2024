package engine.dungeon;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import engine.entities.Entity;
import engine.entities.characters.Enemy;
import engine.entities.environment.Environment;
import log.Gamelog;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient toutes les données relatives à une salle. Cela comprend la liste des entités présentes dans la salle, la porte de la salle, et si la salle est un magasin ou un boss.
 * 
 * @param number le numéro de la salle
 * @param gate la porte de la salle
 * @param isShop si la salle est un magasin
 * @param isBoss si la salle est un boss
 * @param entities la liste des entités présentes dans la salle
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Room {

    private static Logger logger = Gamelog.getLogger();

    private int number;
    private Environment gate;
    private boolean isShop;
    private boolean isBoss;

    // Liste des entités présentes dans la salle
    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public Room (int number) {
        this.number = number;
        logger.trace("New instance of Room");
    }

    public void empty() {
        entities.clear();
    }

    /**
     * Méthode permettant d'ouvrir la porte de la salle
     */
    public void open() {
        this.removeEntity(gate);
    }
  
    /**
     * Cette méthode ajoute une entité à la salle
     * 
     * @param entity l'entité à ajouter
     */
    public void addEntity (Entity entity) {
        entities.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    /**
     * Cette méthode retire une entité à la liste d'entités de la salle
     * 
     * @param entity l'entité à retirer de la liste
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
        logger.trace(entity + " removed");
    }

    public int getNumber() {
        return number;
    }

    public void setGate(Environment gate) {
        this.gate = gate;
    }

    /**
     * Méthode permettant de récupérer les ennemis présents dans la Room
     */
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemiesFetched = new ArrayList<Enemy>();
        // Pour chaque entité présente dans la salle
        for (Entity entity : getEntities()) {
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                enemiesFetched.add(enemy);
            }
        }
        return enemiesFetched;
    }

    public boolean isShop() {
        return isShop;
    }

    public void setShop(boolean isShop) {
        this.isShop = isShop;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean isBoss) {
        this.isBoss = isBoss;
    }
}