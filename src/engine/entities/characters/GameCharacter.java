package engine.entities.characters;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.items.Inventory;
import engine.entities.items.Slot;
import engine.entities.items.weapons.Sword;
import engine.process.EntityFactory;

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
public abstract class GameCharacter extends Entity {

    // définition des attributs
    private int health;
    private Inventory inventory;
    // On instancie directement l'épée dans le weaponSlot des personnages, la position de l'entité est null pour l'instant
    private Slot weaponSlot = new Slot((Sword)EntityFactory.createEntity("sword", null));

    /**
     * Constructeur par défaut. Génère une nouvelle instance de personnage (gameCharacter) contenant sa position, sa hitbox, et ses PV
     * 
     * @param position la position du personnage
     * @param entityType le type de personnage
     * @param health le nombre de PV du personnage
     */
    public GameCharacter (Position position, String entityType, int health) {
        super(position, entityType);
        this.health = health;
        this.inventory = new Inventory();
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
        return "GameCharacter [health=" + health + ", inventory=" + inventory + ", weaponSlot=" + weaponSlot + "]";
    }
    
}
