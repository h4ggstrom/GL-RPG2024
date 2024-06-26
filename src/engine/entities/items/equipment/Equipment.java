package engine.entities.items.equipment;

import java.io.Serializable;
import java.util.ArrayList;

import engine.entities.items.Item;
import engine.entities.items.weapons.Weapon;

/**
 * Classe permettant de créer des entités de type Equipment.
 * Un équipement contient de la place pour une Weapon, un Helmet, un Gloves, un Chestplate, un Pants et un Boots.
 * 
 * @see Item
 */
public class Equipment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Weapon weapon;
    private Helmet helmet;
    private Gloves gloves;
    private Chestplate chestplate;
    private Pants pants;
    private Boots boots;
    
    public Equipment() {
        
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public Gloves getGloves() {
        return gloves;
    }

    public void setGloves(Gloves gloves) {
        this.gloves = gloves;
    }

    public Chestplate getChestplate() {
        return chestplate;
    }

    public void setChestplate(Chestplate chestplate) {
        this.chestplate = chestplate;
    }

    public Pants getPants() {
        return pants;
    }

    public void setPants(Pants pants) {
        this.pants = pants;
    }

    public Boots getBoots() {
        return boots;
    }

    public void setBoots(Boots boots) {
        this.boots = boots;
    }

    /**
     * Permet de récupérer la liste des équipements
     * @return ArrayList<Item>
     */
    public ArrayList<Item> getEquipmentList () {
        ArrayList<Item> equipmentList = new ArrayList<Item>();
        equipmentList.add(weapon);
        equipmentList.add(helmet);
        equipmentList.add(gloves);
        equipmentList.add(chestplate);
        equipmentList.add(pants);
        equipmentList.add(boots);
        return equipmentList;
    }

}
