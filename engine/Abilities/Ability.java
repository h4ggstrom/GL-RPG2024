package engine.Abilities;

import engine.characters.GameCharacter;
import engine.dungeon.Position;
import engine.items.weapons.Weapon;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient les données liés aux capacités des personnages.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Ability {
    
    // définition des attributs
    private GameCharacter caster;
    private int damage;
    private int range;
    private Position target;


    /**
     * Constructeur par défaut. Crée une nouvelle capacité utilisé par un personnage du jeu.
     * 
     * @param caster le personnage utilisant la capacité
     * @param damage les dégats de la capacité
     * @param range la portée de la capacité
     * @param target la position de la cible
     */
    public Ability(GameCharacter caster, Position target) {
        this.caster = caster;
        Weapon weapon = (Weapon)caster.getWeaponSlot().getItem();
        this.damage = weapon.getAttackDamage();
        this.range = weapon.getAttackRange();
        this.target = target;
    }

    public GameCharacter getCaster() {
        return caster;
    }

    public void setCaster(GameCharacter caster) {
        this.caster = caster;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }
  
}
