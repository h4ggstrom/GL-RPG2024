package engine.entities;

import java.io.Serializable;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.entities.characters.Enemy;
import log.Gamelog;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe gère toutes les boîtes de collision des personnages.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Hitbox implements Serializable {

    // définition des attributs
    private static final long serialVersionUID = 1L;
    private static Logger logger = Gamelog.getLogger();
    private Position upperLeft;
    private Position upperRight;
    private Position bottomLeft;
    private Position bottomRight;
    private Position center;
    private int width;
    private int height;
    private Entity entity;
    private String entityType;

    /**
     * Constructeur par défaut. Génère une nouvelle instance de hitbox contenant ses dimensions (dépendant du type de personnage).
     * 
     * @param upperLeft la position en haut à gauche du personnage. La hitbox est calculée à partir de ce point
     * @param entityType le type de l'entité
     * @param character le personnage à qui attribuer la hitbox
     */
    public Hitbox(Position center, String entityType, Entity entity) {
        this.center = center;
        this.entityType = entityType;
        this.entity = entity;
        if(center != null){
            this.calculateCorners();
        }
    }

    public void calculateCorners() {
        int width = 0;
        int height = 0;
        switch (this.entityType) {
            case "rat_fistule" :
                width = GameConfiguration.RAT_FISTULE_WIDTH;
                height = GameConfiguration.RAT_FISTULE_HEIGHT;
                break;
            case GameConfiguration.PLAYER_ENTITYTYPE :
                width = GameConfiguration.PLAYER_WIDTH;
                height = GameConfiguration.PLAYER_HEIGHT;
                break;
            case GameConfiguration.SWORD_ENTITYTYPE :
                width = GameConfiguration.SWORD_WIDTH;
                height = GameConfiguration.SWORD_HEIGHT;
                break;
            case GameConfiguration.HEALTHFLASK_ENTITYTYPE : 
                width = GameConfiguration.HEALTHFLASK_WIDTH;
                height = GameConfiguration.HEALTHFLASK_HEIGHT;
                break;
            case GameConfiguration.BAG_ENTITYTYPE :
                width = GameConfiguration.BAG_WIDTH;
                height = GameConfiguration.BAG_HEIGHT;
                break;
            case GameConfiguration.TREE_ASSET_ENTITYTYPE:
                width = GameConfiguration.TREE_WIDTH;
                height = GameConfiguration.TREE_HEIGHT;
                break;
            case GameConfiguration.WALL_ASSET_ENTITYTYPE:
                width = 0;
                height = 0;
                break;
            case GameConfiguration.CHEST_ENTITYTYPE:
                width = GameConfiguration.CHEST_WIDTH;
                height = GameConfiguration.CHEST_HEIGHT;
                break;
            case GameConfiguration.KEY_ENTITYTYPE:
                width = GameConfiguration.KEY_WIDTH;
                height = GameConfiguration.KEY_HEIGHT;
                break;
            case GameConfiguration.GARBAGE_ENTITYTYPE:
                width = GameConfiguration.GARBAGE_WIDTH;
                height = GameConfiguration.GARBAGE_HEIGHT;
                break;
            case "default" :
                logger.warn(entityType + " is not a recognized type of entity");
                break;
        }
        this.upperLeft = new Position(center.getX() - (width/2), center.getY() - (height/2));
        this.upperRight = new Position(center.getX() + (width/2), center.getY() - (height/2));
        this.bottomLeft = new Position(center.getX() - (width/2), center.getY() + (height/2));
        this.bottomRight = new Position(center.getX() + (width/2), center.getY() + (height/2));
        calculateProportions();
        logger.trace("New hitbox generated : " + this.toString());
    }

    public void calculateProportions() {
        this.width = upperRight.getX() - upperLeft.getX();
        this.height = bottomLeft.getY() - upperLeft.getY();
    }

    public void setPosition(Position center) {
        this.center = center;
        this.calculateCorners();
    }

    /**
     * Cette méthode vérifie si deux Hitbox sont en collision
     * 
     * @param hitbox la hitbox à comparer
     */
    public boolean isInCollision (Hitbox hitbox) {
        return ((this.isContaining(hitbox.upperLeft) || this.isContaining(hitbox.upperRight) || this.isContaining(hitbox.bottomLeft) || this.isContaining(hitbox.bottomRight) || this.isContaining(hitbox.center))
                || (hitbox.isContaining(this.upperLeft) || hitbox.isContaining(this.upperRight) || hitbox.isContaining(this.bottomLeft) || hitbox.isContaining(this.bottomRight) || hitbox.isContaining(this.center)));
    }

    /**
     * Cette méthode vérifie si un pixel est inclus dans la Hitbox
     * 
     * @param position la position à comparer
     */
    public boolean isContaining(Position position) {
        return ( ( ( this.upperLeft.getX() <= position.getX() ) && ( position.getX() <= this.upperRight.getX() ) ) && ( this.upperLeft.getY() <= position.getY() && position.getY() <= this.bottomLeft.getY() ));
    }

    /**
     * Méthode permettant de redéfinir les positions des points de la Hitbox en fonction des deux points extrêmes
     * @param upperLeft le point tout en haut à gauche
     * @param bottomRight le point tout en bas à droite
     */
    public void drawHitbox(Position upperLeft, Position bottomRight) {
        setUpperLeft(upperLeft);
        setBottomRight(bottomRight);

        // Recalcul des autres points

        setUpperRight(new Position(bottomRight.getX(), upperLeft.getY()));
        setBottomLeft(new Position(upperLeft.getX(), bottomRight.getY()));
        setCenter(new Position( upperLeft.getX() + (upperRight.getX() - upperLeft.getX())/2, upperLeft.getY() + (bottomLeft.getY() - upperLeft.getY())/2 ));

        calculateProportions();
    }

    public Position getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Position upperLeft) {
        this.upperLeft = upperLeft;
    }

    public Position getUpperRight() {
        return upperRight;
    }

    public void setUpperRight(Position upperRight) {
        this.upperRight = upperRight;
    }

    public Position getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Position bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Position getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Position bottomRight) {
        this.bottomRight = bottomRight;
    }

    public Enemy getEnemy() {
        return (Enemy)this.entity;
    }

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Milieu de la Hitbox : " + this.center;
    }
    
}
