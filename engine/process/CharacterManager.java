package engine.process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfiguration;
import engine.characters.Player;
import engine.dungeon.Pixel;
import engine.dungeon.Room;
import engine.characters.Enemy;
import engine.characters.GameCharacter;
import engine.characters.Hitbox;

public class CharacterManager {
    
    private Room room;
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Hitbox> hitboxes = new ArrayList<Hitbox>();

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
    
    public void add (GameCharacter enemy) {
		if(enemy instanceof Enemy)
            enemies.add((Enemy) enemy);
	}

    public void add (Hitbox hitbox) {
        hitboxes.add(hitbox);
    }

    public void movePlayer (String direction) {
        Pixel startPosition = player.getPosition();
        Pixel endPosition;
        Boolean canBeMoved = true;
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

        Hitbox finaleHitbox = new Hitbox(room, endPosition, "player"); // On instancie la Hitbox sur l'emplacement final

        // Si le joueur est au limites de la room
        if ( ! ( ( GameConfiguration.ROOM_LEFT_LIMITATION < endPosition.getX() && endPosition.getX() < GameConfiguration.ROOM_RIGHT_LIMITATION ) && ( GameConfiguration.ROOM_UPPER_LIMITATION < endPosition.getY() && endPosition.getY() < GameConfiguration.ROOM_LOWER_LIMITATION ) ) )
            canBeMoved = false; // Il ne peut pas être déplacé

        // On parcourt toutes les Hitbox de la Room
        for (Hitbox hitbox : hitboxes) {
            if ( finaleHitbox.isInCollision(hitbox) ) // Si la Hitbox finale du joueur est en collision avec une des Hitbox de la salle
                canBeMoved = false; // Il ne peut pas être déplacé
        }

        if (canBeMoved) // Si on a jugé que le joueur peut se déplacer
            player.setPosition(endPosition); // On le déplace
            hitboxes.remove(player.getHitbox()); // On retire la Hitbox précédente de notre liste de Hitbox
            player.setHitbox(finaleHitbox); // On créé la nouvelle Hitbox et on l'associe au joueur
    }
}
