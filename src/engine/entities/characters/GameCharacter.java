package engine.entities.characters;

import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.items.Inventory;
import engine.entities.items.Slot;
import engine.entities.items.armor.*;
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
    private int armor;
    private int attackSpeed;
    private int moveSpeed;
    private int abilityCooldown;
    private int stunCooldown;

    private Inventory inventory;

    private Slot weaponSlot = new Slot();
    private Slot helmetSlot = new Slot();
    private Slot glovesSlot = new Slot();
    private Slot chestplateSlot = new Slot();
    private Slot pantsSlot = new Slot();
    private Slot bootsSlot = new Slot();

    /**
     * Constructeur par défaut. Génère une nouvelle instance de personnage (gameCharacter) contenant sa position, sa hitbox, et ses PV
     * 
     * @param position la position du personnage
     * @param entityType le type de personnage
     * @param health le nombre de PV du personnage
     */
    public GameCharacter (Position position, String entityType, int health, int armor, int attackSpeed, int moveSpeed, int abilityCooldown, int stunCooldown) {
        super(position, entityType);
        this.health = health;
        this.armor = armor;
        this.attackSpeed = attackSpeed;
        this.moveSpeed = moveSpeed;
        this.abilityCooldown = abilityCooldown;
        this.stunCooldown = stunCooldown;
        weaponSlot.setItem((Sword)EntityFactory.createEntity("sword", null));
        helmetSlot.setItem((Helmet)EntityFactory.createEntity("helmet", null));
        glovesSlot.setItem((Gloves)EntityFactory.createEntity("gloves", null));
        chestplateSlot.setItem((Chestplate)EntityFactory.createEntity("chestplate", null));
        pantsSlot.setItem((Pants)EntityFactory.createEntity("pants", null));
        bootsSlot.setItem((Boots)EntityFactory.createEntity("boots", null));
        this.inventory = new Inventory();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        Chestplate equipedChestplate = (Chestplate)chestplateSlot.getItem();
        int armorBonus = 0;
        if(equipedChestplate != null) {
            armorBonus = equipedChestplate.getValue();
        }
        return armor + armorBonus;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getAttackSpeed() {
        Gloves equipedGloves = (Gloves)glovesSlot.getItem();
        int attackSpeedBonus = 0;
        if(equipedGloves != null) {
            attackSpeedBonus = equipedGloves.getValue();
        }
        return attackSpeed + attackSpeedBonus;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getMoveSpeed() {
        Boots equipedBoots = (Boots)bootsSlot.getItem();
        int moveSpeedBonus = 0;
        if(equipedBoots != null) {
            moveSpeedBonus = equipedBoots.getValue();
        }
        return moveSpeed + moveSpeedBonus;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
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

    public Slot getHelmetSlot() {
        return helmetSlot;
    }
    
    public Slot getGlovesSlot() {
        return glovesSlot;
    }
    
    public Slot getChestplateSlot() {
        return chestplateSlot;
    }
    
    public Slot getPantsSlot() {
        return pantsSlot;
    }
    
    public Slot getBootsSlot() {
        return bootsSlot;
    }

    public int getAbilityCooldown() {
        Helmet equipedHelmet = (Helmet)helmetSlot.getItem();
        int abilityCooldownBonus = 0;
        if(equipedHelmet != null) {
            abilityCooldownBonus = equipedHelmet.getValue();
        }
        return abilityCooldown + abilityCooldownBonus;
    }

    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public int getStunCooldown() {
        Pants equipedPants = (Pants)pantsSlot.getItem();
        int stunCooldownBonus = 0;
        if(equipedPants != null) {
            stunCooldownBonus = equipedPants.getValue();
        }
        return stunCooldown + stunCooldownBonus;
    }

    public void setStunCooldown(int stunCooldown) {
        this.stunCooldown = stunCooldown;
    }


    @Override
    public String toString() {
        return "GameCharacter [health=" + health + ", inventory=" + inventory + ", weaponSlot=" + weaponSlot + "]";
    }
    
}
