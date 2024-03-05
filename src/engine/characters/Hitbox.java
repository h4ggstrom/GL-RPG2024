package engine.characters;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.dungeon.Position;
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
public class Hitbox {

    // définition des attributs
    private static Logger logger = Gamelog.getLogger();
    private Position upperLeft;
    private Position upperRight;
    private Position bottomLeft;
    private Position bottomRight;
    private Position center;
    private GameCharacter character;
    private String characterType;

    /**
     * Constructeur par défaut. Génère une nouvelle instance de hitbox contenant ses dimensions (dépendant du type de personnage).
     * 
     * @param upperLeft la position en haut à gauche du personnage. La hitbox est calculée à partir de ce point
     * @param characterType le type de personnage
     * @param character le personnage à qui attribuer la hitbox
     */
    public Hitbox(Position center, String characterType, GameCharacter character) {
        this.center = center;
        this.characterType = characterType;
        this.character = character;
        this.calculateCorners();
    }

    public void calculateCorners() {
        int width = 0;
        int height = 0;
        switch (this.characterType) {
            case "enemy" :
                width = GameConfiguration.ENEMY_WIDTH;
                height = GameConfiguration.ENEMY_HEIGHT;
                break;
            case "player" :
                width = GameConfiguration.PLAYER_WIDTH;
                height = GameConfiguration.PLAYER_HEIGHT;
                break;
            case "default" :
                logger.warn(characterType + " is not a recognized type of entity");
                break;
        }
        this.upperLeft = new Position(center.getX() - (width/2), center.getY() - (height/2));
        this.upperRight = new Position(center.getX() + (width/2), center.getY() - (height/2));
        this.bottomLeft = new Position(center.getX() - (width/2), center.getY() + (height/2));
        this.bottomRight = new Position(center.getX() + (width/2), center.getY() + (height/2));
        logger.trace("New hitbox generated : " + this.toString());
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
        return (this.isContaining(hitbox.upperLeft) || this.isContaining(hitbox.upperRight) || this.isContaining(hitbox.bottomLeft) || this.isContaining(hitbox.bottomRight));
    }

    /**
     * Cette méthode vérifie si un pixel est inclus dans la Hitbox
     * 
     * @param pixel la position à comparer
     */
    public boolean isContaining(Position pixel) {
        return ( ( this.upperLeft.getX() <= pixel.getX() && pixel.getX() <= this.upperRight.getX() ) && ( this.upperLeft.getY() <= pixel.getY() && pixel.getY() <= this.bottomLeft.getY() ));
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
        return (Enemy)this.character;
    }

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "Hitbox [upperLeft=" + upperLeft + ", upperRight=" + upperRight + ", bottomLeft=" + bottomLeft
                + ", bottomRight=" + bottomRight + ", center=" + center + "]";
    }
    
}
