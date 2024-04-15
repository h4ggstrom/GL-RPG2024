package engine.dungeon;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.entities.Entity;
import engine.entities.environment.GateAsset;
import engine.entities.environment.WallAsset;
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
    
    // définition des attributs
    private Boolean cleaned = false; // booléen pour savoir si la salle a été nettoyée de toute entité hostile
    private Boolean exited = false; // booléen  pour savoir si le joueur a quitté

    private int number;


    // Liste des entités présentes dans la salle
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private String fileName = "room";

    public Room (int number) {
        this.number = number;
        buildWalls();
        logger.trace("New instance of Room");
    }
    
    public void buildWalls() {
        WallAsset leftWall = new WallAsset(null);
        leftWall.getHitbox().drawHitbox(new Position(0, 0), new Position(GameConfiguration.ROOM_LEFT_LIMITATION, GameConfiguration.WINDOW_HEIGHT));
        addEntity(leftWall);

        WallAsset upperWall = new WallAsset(null);
        upperWall.getHitbox().drawHitbox(new Position(0, 0), new Position(GameConfiguration.WINDOW_WIDTH, GameConfiguration.ROOM_UPPER_LIMITATION));
        addEntity(upperWall);

        WallAsset lowerWall = new WallAsset(null);
        lowerWall.getHitbox().drawHitbox(new Position(0, GameConfiguration.ROOM_LOWER_LIMITATION), new Position(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT));
        addEntity(lowerWall);

        WallAsset upperGateWall = new WallAsset(null);
        upperGateWall.getHitbox().drawHitbox(new Position(GameConfiguration.ROOM_RIGHT_LIMITATION, 0), GameConfiguration.GATE_UPPERRIGHT);
        addEntity(upperGateWall);

        WallAsset lowerGateWall = new WallAsset(null);
        lowerGateWall.getHitbox().drawHitbox(GameConfiguration.GATE_BOTTOMLEFT, new Position(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT));
        addEntity(lowerGateWall);

        GateAsset gate = new GateAsset(null);
        gate.getHitbox().drawHitbox(GameConfiguration.GATE_UPPERLEFT, GameConfiguration.GATE_BOTTOMRIGHT);
        addEntity(gate);
    }

    public void empty() {
        this.cleaned = false;
        this.exited = false;
        entities.clear();
        fileName = "room";
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

    /**
     * Cette méthode permet de déclarer la salle comme etant nettoyée (de toute entité hostile)
     */
    public void clean () {
        this.cleaned = true;
        logger.info("room has been marked as cleaned");
        this.fileName = "room_open";
    }

    public Boolean getCleaned () {
        return this.cleaned;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void exit() {
        this.exited = true;
    }

    public Boolean getExited() {
        return exited;
    }

    public int getNumber() {
        return number;
    }

    /**
     * Méthode permettant de créer une liste d'entitées destinées à être dessinées dans le GUI, évitant ainsi l'Exception : ConcurrentModificationException
     * @return Une nouvelle liste avec les entitées de la Room, destinée au dessin dans le GUI
     */
    public ArrayList<Entity> getEntitiesToDraw() {
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