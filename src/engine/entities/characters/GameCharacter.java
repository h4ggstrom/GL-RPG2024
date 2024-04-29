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
    private int attackPossibility = 0;
    private int movePossibility = 0;
    private int mana = 0;

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

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void addMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void healCharacter(int heal) {
        int finalHealth = this.getHealth() + heal;
        if(finalHealth > this.getMaxHealth()) {
            finalHealth = this.getMaxHealth();
        }
        this.health = finalHealth;
    }

    public void hurtCharacter(int damage) {
        this.health = this.getHealth() - (int)(damage - (double)(damage * this.getArmor() / 100));
    }

    public int getArmor() {
        return this.getItemBonus(armor, equipment.getChestplate());
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void addArmor(int armor) {
        // Valeur maximale d'armure 85%
        if(this.armor + armor < 85) {
            this.armor += armor;
        } else {
            this.armor = 85;
        }
    }

    public int getAttackSpeed() {
        return this.getItemBonus(attackSpeed, equipment.getGloves());
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void addAttackSpeed(int attackSpeed) {
        this.attackSpeed += attackSpeed;
    }

    public int getAttackRange() {
        return attackRange + ((equipment.getWeapon() == null) ? 0 : ((Weapon)equipment.getWeapon()).getAttackRange());
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public void addAttackRange(int attackRange) {
        this.attackRange += attackRange;
    }

    public int getAttackDamage() {
        return attackDamage + ((equipment.getWeapon() == null) ? 0 : ((Weapon)equipment.getWeapon()).getAttackDamage());
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
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

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void addMoveSpeed(int moveSpeed) {
        this.moveSpeed += moveSpeed;
    }

    public int getAbilityCooldown() {
        return (this.getItemBonus(abilityCooldown, equipment.getHelmet()) < 500 ? 501 : this.getItemBonus(abilityCooldown, equipment.getHelmet()));
    }

    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public void addAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown += abilityCooldown;
    }

    public int getStunCooldown() {
        return this.getItemBonus(stunCooldown, equipment.getPants());
    }

    public void setStunCooldown(int stunCooldown) {
        this.stunCooldown = stunCooldown;
        this.movePossibility = stunCooldown;
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

    public int getAttackPossibility() {
        if(attackPossibility > getAttackSpeed()) {
            attackPossibility = getAttackSpeed();
        }
        return attackPossibility;
    }

    public void setAttackPossibility(int attackPossibility) {
        this.attackPossibility = attackPossibility;
    }

    public int getMovePossibility() {
        if(movePossibility > getStunCooldown()) {
            movePossibility = getStunCooldown();
        }
        return movePossibility;
    }

    public void setMovePossibility(int movePossibility) {
        this.movePossibility = movePossibility;
    }

    public int getMana() {
        if(mana > getAbilityCooldown()) {
            mana = getAbilityCooldown();
        }
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void addMana(int mana) {
        this.mana += mana;
        if(mana < 500) {
            this.mana = 500;
        }
    }

    public void useAbility() {
        mana = 0;
    }

    public void incrementPossibilities() {
        incrementAttackPossibility();
        incrementMovePossibility();
        incrementMana();
    }

    public void incrementAttackPossibility() {
        if(attackPossibility < getAttackSpeed()) {
            attackPossibility++;
        }
    }

    public void incrementMovePossibility() {
        if(movePossibility < getStunCooldown()) {
            movePossibility++;
        }
    }

    public void incrementMana() {
        if(mana < getAbilityCooldown()) {
            mana++;
        }
    }

    public boolean canMove() {
        return movePossibility >= getStunCooldown();
    }

    public boolean canAttack() {
        return attackPossibility >= getAttackSpeed();
    }

    public boolean canAbility() {
        return mana >= getAbilityCooldown();
    }
}
