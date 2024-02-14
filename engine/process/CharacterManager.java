package engine.process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfiguration;
import engine.characters.Player;
import engine.dungeon.Pixel;
import engine.dungeon.Room;
import engine.characters.Enemy;

public class CharacterManager {
    
    private Room room;
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public CharacterManager (Room room) {
        this.room = room;
    }

    public void set (Player player) {
        this.player = player;
    }

    public Player getPlayer () {
        return this.player;
    }

    public List<Enemy> getEnemies () {
		return enemies;
	}
    
    public void add (Enemy enemy) {
		enemies.add(enemy);
	}

    public void movePlayer (String direction) {
        Pixel startPosition = player.getPosition();
        Pixel endPosition;
        switch (direction) {
            case "up":
                endPosition = room.getPixel(startPosition.getX(), startPosition.getY() - GameConfiguration.PLAYER_DEFAULT_SPEED);
                break;
            case "left":
                endPosition = room.getPixel(startPosition.getX() - GameConfiguration.PLAYER_DEFAULT_SPEED, startPosition.getY());
                break;
            case "down":
                endPosition = room.getPixel(startPosition.getX(), startPosition.getY() + GameConfiguration.PLAYER_DEFAULT_SPEED);
                break;
            case "right":
                endPosition = room.getPixel(startPosition.getX() + GameConfiguration.PLAYER_DEFAULT_SPEED, startPosition.getY());
                break;
            default:
                endPosition = startPosition; // Sinon, on garde la même position
                break;
        }
        if ( ( GameConfiguration.ROOM_LEFT_LIMITATION < endPosition.getX() && endPosition.getX() < GameConfiguration.ROOM_RIGHT_LIMITATION ) && ( GameConfiguration.ROOM_UPPER_LIMITATION < endPosition.getY() && endPosition.getY() < GameConfiguration.ROOM_LOWER_LIMITATION ) )
            player.setPosition(endPosition);
    }

}
