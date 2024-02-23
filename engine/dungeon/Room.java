package engine.dungeon;

import java.util.ArrayList;

import engine.characters.Enemy;
import engine.characters.Hitbox;

public class Room {
    
    private Boolean cleaned;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Hitbox> enemy_hitboxes = new ArrayList<Hitbox>();

    public Room () {
        this.cleaned = false; // Par défaut, une Room est remplie de monstres et doit-être nettoyée
    }

    /**
     * Cette méthode ajoute un personnage à la liste des personnages présents dans la room
     * @param character le personnage à ajouter
     */
    public void addEnemy (Enemy enemy) {
        enemies.add(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void addHitbox (Hitbox hitbox) {
        enemy_hitboxes.add(hitbox);
    }

    public ArrayList<Hitbox> getEnemyHitboxes() {
        return this.enemy_hitboxes;
    }

    public void removeEnemyHitbox(Hitbox hitbox) {
        enemy_hitboxes.remove(hitbox);
    }

    public void clean () {
        cleaned = true;
    }

    public Boolean getCleaned () {
        return this.cleaned;
    }

}
