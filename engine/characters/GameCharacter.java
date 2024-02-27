package engine.characters;

import engine.dungeon.Position;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe abstraite contient toutes les données liées aux personnages du jeu.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public abstract class GameCharacter {

    //définition des attributs
    private Position position;
    private Hitbox hitbox;
    private int health;


    /**
     * Constructeur par défaut. Génère une nouvelle instance de personnage (gameCharacter) contenant sa position, sa hitbox, et ses PV
     * 
     * @param position la position du personnage
     * @param characterType le type de personnage
     * @param health le nombre de PV du personnage
     */
    public GameCharacter (Position position, String characterType, int health) {
        this.position = position;
        this.hitbox = new Hitbox(position, characterType, this);
        this.health = health;
    }

    public Position getPosition () {
        return this.position;
    }

    public void setPosition (Position position) {
        this.position = position;
    }

    public Hitbox getHitbox () {
        return this.hitbox;
    }

    public void setHitbox (Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "GameCharacter [position=" + position + ", hitbox=" + hitbox + ", health=" + health + "]";
    }
    
    
}
