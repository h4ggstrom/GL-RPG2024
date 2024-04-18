package engine.entities.characters;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.items.Item;
import engine.entities.containers.Inventory;
import engine.entities.items.equipment.*;
import engine.entities.items.weapons.Weapon;

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
    private int maxHealth = 0;
    private int health = 0;
    private int armor = 0;
    private int attackSpeed = 0;
    private int attackRange = 0;
    private int attackDamage = 0;
    private int moveSpeed = 0;
    private int abilityCooldown = 0;
    private int stunCooldown = 0;

    private boolean canAttack = true;

    private Inventory inventory;
    private Equipment equipment;

    /**
     * Constructeur par défaut. Génère une nouvelle instance de personnage (gameCharacter) contenant sa position, sa hitbox, et ses PV
     * 
     * @param position la position du personnage
     * @param entityType le type de personnage
     * @param health le nombre de PV du personnage
     */
    public GameCharacter (Position position, String characterName, String characterType) {
        super(position, characterName, characterType);
        this.equipment = new Equipment();
        this.inventory = new Inventory();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void addMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void healCharacter(int heal) {
        int finalHealth = this.getHealth() + heal;
        if(finalHealth > this.getMaxHealth()) {
            finalHealth = this.getMaxHealth();
        }
        this.health = finalHealth;
    }

    public void hurtCharacter(int damage) {
        this.health = this.getHealth() - damage;
    }

    public int getArmor() {
        return this.getItemBonus(armor, equipment.getChestplate());
    }

    public void addArmor(int armor) {
        this.armor += armor;
    }

    public int getAttackSpeed() {
        return this.getItemBonus(attackSpeed, equipment.getGloves());
    }

    public void addAttackSpeed(int attackSpeed) {
        this.attackSpeed += attackSpeed;
    }

    public int getAttackRange() {
        return attackRange + ((equipment.getWeapon() == null) ? 0 : ((Weapon)equipment.getWeapon()).getAttackRange());
    }

    public void addAttackRange(int attackRange) {
        this.attackRange += attackRange;
    }

    public int getAttackDamage() {
        return attackDamage + ((equipment.getWeapon() == null) ? 0 : ((Weapon)equipment.getWeapon()).getAttackDamage());
    }

    /**
     * Permet d'ajouter des dégâts d'attaque à la valeur de base
     * @param attackDamage
     */
    public void addAttackDamage(int attackDamage) {
        this.attackDamage += attackDamage;
    }

    public int getMoveSpeed() {
        return this.getItemBonus(moveSpeed, equipment.getBoots());
    }

    public void addMoveSpeed(int moveSpeed) {
        this.moveSpeed += moveSpeed;
    }

    public int getAbilityCooldown() {
        return this.getItemBonus(abilityCooldown, equipment.getHelmet());
    }

    public void addAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown += abilityCooldown;
    }

    public int getStunCooldown() {
        return this.getItemBonus(stunCooldown, equipment.getPants());
    }

    public void addStunCooldown(int stunCooldown) {
        this.stunCooldown += stunCooldown;
    }

    public int getItemBonus(int baseValue, Item item) {
        int itemBonus = 0;
        if(item != null) {
            if(item instanceof Clothe) {
                Clothe clothe = (Clothe)item;
                itemBonus = clothe.getValue();
            }
        }
        return baseValue + itemBonus;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Equipment getEquipment() {
        return equipment;
    }
    
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }
}
