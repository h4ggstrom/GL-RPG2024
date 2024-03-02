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

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient toutes les processus liés aux déplacements et autres actions du joueur dans l'environnement de jeu.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class CharacterManager {

    // définition des attributs
    private Player player; // le contrôle
    private Room room; // la salle dans laquelle évolue le joueur 
    private ArrayList<Ability> abilities = new ArrayList<Ability>(); // liste des capacités du joueur

    
    /**
     * Constructeur par défaut. Génère une nouvelle instance de CharacterManager.
     * 
     * @param room la salle dans laquelle évoluera le joueur
     */
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

    /**
     * Cette méthode gère les déplacements du joueur
     * 
     * @param direction l'input envoyé par le joueur, au format String
     */
    public void movePlayer (String direction) {
        Position startPosition = player.getPosition();
        Position endPosition;
        Boolean canBeMoved = true;
        // Switch case pour calculer la nouvelle position
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

        // Si la position finale du joueur n'est pas dans les limites de la Room
        if ( ! ( ( GameConfiguration.ROOM_LEFT_LIMITATION < endPosition.getX() && endPosition.getX() < GameConfiguration.ROOM_RIGHT_LIMITATION ) && ( GameConfiguration.ROOM_UPPER_LIMITATION < endPosition.getY() && endPosition.getY() < GameConfiguration.ROOM_LOWER_LIMITATION ) ) ) {
            // Si la Room n'est pas ouverte
            if(!room.getCleaned()){
                canBeMoved = false; // Il ne peut pas être déplacé
            }
            // Si la Room est ouverte
            else {
                // Si il veut se déplacer derrière le mur de droite, entre le haut et le bas de la porte
                if ( ( GameConfiguration.ROOM_RIGHT_LIMITATION < endPosition.getX() ) && ( (GameConfiguration.GATE_UP.getY() < endPosition.getY()) && (endPosition.getY() < GameConfiguration.GATE_DOWN.getY() - GameConfiguration.PLAYER_HEIGHT)))
                    canBeMoved = true; // On peut le déplacer
                else {
                    canBeMoved = false; // Sinon, non
                }
            }
        }

        // On parcourt toutes les Hitbox d'Enemy de la Room
        for (Hitbox hitbox : room.getEnemyHitboxes()) {
            if ( finaleHitbox.isInCollision(hitbox) ) // Si la Hitbox finale du joueur est en collision avec une des Hitbox de la salle
                canBeMoved = false; // Il ne peut pas être déplacé
        }

        if (canBeMoved) // Si on a jugé que le joueur peut se déplacer
            player.setHitbox(finaleHitbox); // On associe la nouvelle Hitbox au joueur
    }

    /**
     * Cette méthode gère les attaques du joueur.
     * 
     * @param distance la distance du clic par rapport au joueur.
     * @param ability la capacité utilisée pour attaquer.
     */
    public void attack(int distance, Ability ability) {
        if(distance <= ability.getRange()) {
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

    /**
     * Cette méthode permet de calculer la distance entre deux objets.
     * 
     * @param p1 la position du premier objet
     * @param p2 la position du deuxieme objet
     * @return la distance entière entre l'objet p1 et p2
     */
    public int calculateDistance(Position p1, Position p2) {
        return ((int)(Math.sqrt(Math.pow(Math.abs(p1.getX() - p2.getX()), 2) + Math.pow(Math.abs(p1.getY()) - p2.getY(), 2))));
    }
}
