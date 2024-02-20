package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Pixel;
import engine.dungeon.Room;

/**
 * Une Hitbox est un rectangle défini par ses 4 coins, intraversable et touchable avec des attaques
 */
public class Hitbox {

    private Pixel upperLeft;
    private Pixel upperRight;
    private Pixel bottomLeft;
    private Pixel bottomRight;

    public Hitbox(Room room, Pixel upperLeft, String characterType) {
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
        this.upperRight = room.getPixel(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = room.getPixel(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = room.getPixel(bottomLeft.getX() + width, bottomLeft.getY());
    }

    /**
     * Cette méthode vérifie si deux Hitbox sont en collision
     * @param H1
     * @param H2
     * @return
     */
    public boolean isInCollision (Hitbox hitbox) {
        return (this.isContaining(hitbox.upperLeft) || this.isContaining(hitbox.upperRight) || this.isContaining(hitbox.bottomLeft) || this.isContaining(hitbox.bottomRight));
    }

    /*
     * Cette méthode vérifie si un pixel est inclus dans la Hitbox
     * @param pixel
     * @return
     */
    private boolean isContaining(Pixel pixel) {
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

    @Override
    public String toString() {
        return "Hitbox [upperLeft=" + upperLeft + ", upperRight=" + upperRight + ", bottomLeft=" + bottomLeft
                + ", bottomRight=" + bottomRight + "]";
    }

}
