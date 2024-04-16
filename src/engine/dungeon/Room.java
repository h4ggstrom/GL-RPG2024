package engine.dungeon;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import engine.entities.Entity;
import engine.entities.environment.GateEnv;
import log.Gamelog;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient toutes les données relatives à une salle. Cela comprend la liste des entités présentes à la génération de la salle
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Room {

    private static Logger logger = Gamelog.getLogger();

    private int number;
    private GateEnv gate;


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

    public void setGate(GateEnv gate) {
        this.gate = gate;
    }

    /**
     * Méthode permettant de créer une liste d'entitées destinées à être seulement parcourue et non modifiée, évitant ainsi l'Exception : ConcurrentModificationException
     * Cette exception survient lorsqu'on parcourt un ArrayList et qu'on la modifie parrallèlement (blesser et tuer les ennemis par exemple)
     * @return Une nouvelle liste avec les entitées de la Room, destinée aux traitements statiques
     */
    public ArrayList<Entity> getStaticEntities() {
        return new ArrayList<>(entities);
    }

    public String getDifficulty() {
        if (this.number >= 1 && this.number <= 2) {
            return "facile";
        } else if (this.number >= 3 && this.number <= 5) {
            return "moyenne";
        } else if (this.number >= 6 && this.number <= 7) {
            return "difficile";
        }
        return "undefined";
    }
}