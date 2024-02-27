package engine.process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfiguration;
import engine.characters.Player;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.Abilities.Ability;
import engine.characters.Enemy;
import engine.characters.Hitbox;

public class CharacterManager {
     
    private Player player;
    private Room room;
    private ArrayList<Ability> abilities = new ArrayList<Ability>();

    public CharacterManager (Room room) {
        this.room = room;
    }

    public void set (Player player) {
        this.player = player;
    }

    public void add (Ability ability) {
        this.abilities.add(ability);
    }

    public void emptyAbilities () {
        this.abilities.clear();
    }

    public Player getPlayer () {
        return this.player;
    }

    public Room getRoom() {
        return this.room;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void movePlayer (String direction) {
        Position startPosition = player.getPosition();
        Position endPosition;
        Boolean canBeMoved = true;
        switch (direction) {
            case "up":
                endPosition = new Position(startPosition.getX(), startPosition.getY() - GameConfiguration.PLAYER_DEFAULT_SPEED);
                break;
            case "left":
                endPosition = new Position(startPosition.getX() - GameConfiguration.PLAYER_DEFAULT_SPEED, startPosition.getY());
                break;
            case "down":
                endPosition = new Position(startPosition.getX(), startPosition.getY() + GameConfiguration.PLAYER_DEFAULT_SPEED);
                break;
            case "right":
                endPosition = new Position(startPosition.getX() + GameConfiguration.PLAYER_DEFAULT_SPEED, startPosition.getY());
                break;
            default:
                endPosition = startPosition; // Sinon, on garde la même position
                break;
        }

        Hitbox finaleHitbox = new Hitbox(endPosition, "player", player); // On instancie la Hitbox sur l'emplacement final

        // Si la Room n'est pas ouverte et que le joueur est aux limites de la room
        if ( !room.getCleaned() && ! ( ( GameConfiguration.ROOM_LEFT_LIMITATION < endPosition.getX() && endPosition.getX() < GameConfiguration.ROOM_RIGHT_LIMITATION ) && ( GameConfiguration.ROOM_UPPER_LIMITATION < endPosition.getY() && endPosition.getY() < GameConfiguration.ROOM_LOWER_LIMITATION ) ) )
            canBeMoved = false; // Il ne peut pas être déplacé

        // Si la Room est ouverte alors il pourra sortir par la porte
        if ( room.getCleaned() && ! ( ( GameConfiguration.ROOM_LEFT_LIMITATION < endPosition.getX() && endPosition.getX() < GameConfiguration.ROOM_RIGHT_LIMITATION ) && ( GameConfiguration.ROOM_UPPER_LIMITATION < endPosition.getY() && endPosition.getY() < GameConfiguration.ROOM_LOWER_LIMITATION ) ) && !(GameConfiguration.GATE_UP.getY() <= endPosition.getY() && endPosition.getY() <= GameConfiguration.GATE_DOWN.getY() - GameConfiguration.PLAYER_HEIGHT) )
            canBeMoved = false;

        // On parcourt toutes les Hitbox d'Enemy de la Room
        for (Hitbox hitbox : room.getEnemyHitboxes()) {
            if ( finaleHitbox.isInCollision(hitbox) ) // Si la Hitbox finale du joueur est en collision avec une des Hitbox de la salle
                canBeMoved = false; // Il ne peut pas être déplacé
        }

        if (canBeMoved) // Si on a jugé que le joueur peut se déplacer
            player.setPosition(endPosition); // On le déplace
            player.setHitbox(finaleHitbox); // On associe la nouvelle Hitbox au joueur
    }

    public void attack(int distance, Ability ability) {
        if(distance <= GameConfiguration.WEAPON_RANGE) {
            List<Enemy> eliminatedEnemies = new ArrayList<Enemy>();

            // On parcourt toutes les Hitbox d'Enemy
            for (Hitbox hitbox : room.getEnemyHitboxes()) {
                // Si la Hitbox contient le pixel visé par l'attaque
                if (hitbox.isContaining(ability.getTarget())) {
                    Enemy enemy = hitbox.getEnemy();
                    enemy.setHealth(enemy.getHealth() - ability.getDamage());

                    // Si la vie de l'Enemy atteint 0 (ou moins)
                    if (enemy.getHealth() <= 0)
                        // On l'ajoute à la liste d'Enemy éliminés
                        eliminatedEnemies.add(enemy);
                    }
                }

            // On parcourt les Enemy éliminés pour les retirer du jeu
            for (Enemy enemy : eliminatedEnemies) {
                room.removeEnemyHitbox(enemy.getHitbox());
                room.removeEnemy(enemy);
                }

            // On vérifie si il ne reste plus aucun ennemis
            if (room.getEnemies().size() == 0) {
                // Si c'est le cas la Room à été nettoyée
                room.clean();

                abilities.remove(ability);
                }
        }
    }
}
