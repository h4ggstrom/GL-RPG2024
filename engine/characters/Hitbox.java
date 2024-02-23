package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Pixel;

/**
 * Une Hitbox est un rectangle défini par ses 4 coins et son milieu, intraversable et touchable avec des attaques
 */
public class Hitbox {

    private Pixel upperLeft;
    private Pixel upperRight;
    private Pixel bottomLeft;
    private Pixel bottomRight;
    private Pixel center;
    private GameCharacter character;

    public Hitbox(Pixel upperLeft, String characterType, GameCharacter character) {
        this.upperLeft = upperLeft;
        int width = 0;
        int height = 0;
        switch (characterType) {
            case "enemy" :
                width = GameConfiguration.ENEMY_WIDTH;
                height = GameConfiguration.ENEMY_HEIGHT;
                break;
            case "player" :
                width = GameConfiguration.PLAYER_WIDTH;
                height = GameConfiguration.PLAYER_HEIGHT;
                break;
            case "default" :
                break;
        }
        this.upperRight = new Pixel(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Pixel(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Pixel(bottomLeft.getX() + width, bottomLeft.getY());
        this.center = new Pixel(upperLeft.getX() + ( (upperRight.getX() - upperLeft.getX()) / 2), upperLeft.getY() + ( (bottomLeft.getY() - upperLeft.getY()) / 2));
        this.character = character;
    }

    /**
     * Cette méthode vérifie si deux Hitbox sont en collision
     */
    public boolean isInCollision (Hitbox hitbox) {
        return (this.isContaining(hitbox.upperLeft) || this.isContaining(hitbox.upperRight) || this.isContaining(hitbox.bottomLeft) || this.isContaining(hitbox.bottomRight));
    }

    /*
     * Cette méthode vérifie si un pixel est inclus dans la Hitbox
     */
    public boolean isContaining(Pixel pixel) {
        return ( ( this.upperLeft.getX() <= pixel.getX() && pixel.getX() <= this.upperRight.getX() ) && ( this.upperLeft.getY() <= pixel.getY() && pixel.getY() <= this.bottomLeft.getY() ));
    }

    public Pixel getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Pixel upperLeft) {
        this.upperLeft = upperLeft;
    }

    public Pixel getUpperRight() {
        return upperRight;
    }

    public void setUpperRight(Pixel upperRight) {
        this.upperRight = upperRight;
    }

    public Pixel getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Pixel bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Pixel getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Pixel bottomRight) {
        this.bottomRight = bottomRight;
    }

    public Enemy getEnemy() {
        return (Enemy)this.character;
    }

    public Pixel getCenter() {
        return center;
    }

    public void setCenter(Pixel center) {
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
