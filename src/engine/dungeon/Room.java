package engine.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import engine.characters.Enemy;
import engine.characters.Hitbox;
import engine.items.Item;
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

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>(); // liste des ennemis présents dans la salle
    private ArrayList<Hitbox> enemyHitboxes = new ArrayList<Hitbox>(); // liste des hitboxes associées aux ennemis présents dans la salle
    private HashMap<Position, Item> itemsOnTheGround = new HashMap<Position, Item>();
    private String fileName = "room";

    public Room () {
        this.cleaned = false; // Par défaut, une Room est remplie de monstres et doit-être nettoyée
        logger.trace("New instance of Room");
    }

    public void empty() {
        this.cleaned = false;
        this.exited = false;
        this.enemies = new ArrayList<Enemy>();
        this.enemyHitboxes = new ArrayList<Hitbox>();
        this.itemsOnTheGround = new HashMap<Position, Item>();
        fileName = "room";
    }
  
    /**
     * Cette méthode ajoute un personnage à la liste des ennemis présents dans la salle
     * 
     * @param character le personnage à ajouter
     */
    public void addEnemy (Enemy enemy) {
        enemies.add(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * Cette méthode retire un ennemi à la liste des ennemis présents dans la salle
     * 
     * @param enemy l'ennemi à retirer de la liste
     */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        logger.trace(enemy + " removed");
    }


    /**
     * Cette méthode permet d'ajouter une hitbox (liée à un ennemi) à la liste des hitboxes.
     * 
     * @param hitbox la hitbox à ajouter
     */
    public void addEnemyHitbox (Hitbox hitbox) {
        enemyHitboxes.add(hitbox);
        logger.trace("hitbox added to array");
    }

    public ArrayList<Hitbox> getEnemyHitboxes() {
        return this.enemyHitboxes;
    }

    /**
     * Cette méthode permet de retirer une hitbox (liée à un ennemi) de la liste des hitboxes.
     * 
     * @param hitbox
     */
    public void removeEnemyHitbox(Hitbox hitbox) {
        enemyHitboxes.remove(hitbox);
        logger.trace("hitbox removed from array");
    }

    public void addItemOnTheGround(Position position, Item item) {
        itemsOnTheGround.put(position, item);
        logger.trace("new item spawned on ground at coords" + position.toString());
    }

    public void removeItemOnTheGround(Item item) {
        // On initialise une variable pour la clé associée à notre item que l'on va trouver en parcourant la HashMap
        Position positionOfItemToRemove = null;
        // On parcourt chaque couple de la HashMap
        for (Map.Entry<Position, Item> couple : itemsOnTheGround.entrySet()) {
            // Si on trouve la valeur correspondant à l'item
            if (couple.getValue().equals(item)) {
                // On sauvegarde sa clé qui est sa position
                positionOfItemToRemove = couple.getKey();
            }
        }
        // On retire cet item de la HashMap grâce à sa clé : sa position
        itemsOnTheGround.remove(positionOfItemToRemove);
    }

    public HashMap<Position, Item> getItemsOnTheGround () {
        return this.itemsOnTheGround;
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
}
