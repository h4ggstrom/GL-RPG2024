package engine.characters;

import engine.dungeon.Position;
import engine.items.Inventory;
import engine.items.Slot;
import engine.items.weapons.Sword;

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

    // définition des attributs
    private Hitbox hitbox;
    private int health;
    private Inventory inventory;
    private Slot weaponSlot = new Slot(new Sword());

    /**
     * Constructeur par défaut. Génère une nouvelle instance de personnage (gameCharacter) contenant sa position, sa hitbox, et ses PV
     * 
     * @param position la position du personnage
     * @param characterType le type de personnage
     * @param health le nombre de PV du personnage
     */
    public GameCharacter (Position position, String characterType, int health) {
        this.hitbox = new Hitbox(position, characterType, this);
        this.health = health;
        this.inventory = new Inventory();
    }

    public void setPosition (Position position) {
        this.hitbox.setPosition(position);
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Slot getWeaponSlot() {
        return weaponSlot;
    }

    public void setWeaponSlot(Slot weaponSlot) {
        this.weaponSlot = weaponSlot;
    }

    @Override
    public String toString() {
        return "GameCharacter [hitbox=" + hitbox + ", health=" + health + "]";
    }
    
    
}
