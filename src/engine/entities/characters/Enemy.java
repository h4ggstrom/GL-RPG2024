package engine.entities.characters;

import engine.dungeon.Position;
import engine.process.visitor.EntityVisitor;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe contient les données liées aux ennemis.
 * Pour les valeurs par défaut, voir {@link config.GameConfiguration}
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Enemy extends GameCharacter {

    /*
     * sightDistance : distance à laquelle l'ennemi peut voir le joueur
     * @see engine.process.manager.EntityManager#moveEnemies()
     */
    private int sightDistance = 0;

    /**
     * Constructeur par défaut. Crée une nouvelle instance d'Enemy en utilisant le constructeur de {@link engine.entities.characters.GameCharacter}
     * 
     * @param position la position de départ de l'ennemi
     */
    public Enemy(Position position, String enemyName, String enemyType) {
        super(position, enemyName, enemyType);
    }

    public int getSightDistance() {
        return sightDistance;
    }

    public void setSightDistance(int sightDistance) {
        this.sightDistance = sightDistance;
    }


    @Override
	public <E> E accept(EntityVisitor<E> visitor) {
		return visitor.visit(this);
	}
}
