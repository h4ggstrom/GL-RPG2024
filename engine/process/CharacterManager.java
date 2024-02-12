package engine.process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfiguration;
import engine.characters.Enemy;
import engine.characters.Player;
import engine.dungeon.Block;
import engine.dungeon.Room;

public class CharacterManager {
    private Room room;

    private Player player;
    private List<Enemy> enemies = new ArrayList<Enemy>();

    public CharacterManager(Room room){
        this.room = room;
    }

    public void set(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public List<Enemy> getEnemies() {
		return enemies;
	}
    
    public void add(Enemy enemy) {
		enemies.add(enemy);
	}

    public void movePlayerUp(){
        Block position = player.getPosition();
        Block newPosition = room.getBlock(position.getLine() - 1, position.getColumn()); // On récupère la case du dessus

        if(position.getLine() > 0 && !newPosition.isOccupied()){ // Il ne peut aller en haut que si il n'est pas déjà tout en haut (coordonnée de ligne = 0) et si la case n'est pas déjà occupée
            player.setPosition(newPosition);
        }
    }

    public void movePlayerDown(){
        Block position = player.getPosition();
        Block newPosition = room.getBlock(position.getLine() + 1, position.getColumn()); // On récupère la case du dessous

        if(position.getLine() < GameConfiguration.LINE_COUNT - 1 && !newPosition.isOccupied()){ // Il ne peut aller en bas que si il n'est pas déjà tout en bas (coordonnée de ligne = nombre de lignes - 1) et si la case n'est pas déjà occupée
            player.setPosition(newPosition);
        }
    }

    public void movePlayerLeft(){
        Block position = player.getPosition();
        Block newPosition = room.getBlock(position.getLine(), position.getColumn() - 1);

        if(position.getColumn() > 0 && !newPosition.isOccupied()){ // Il ne peut aller à gauche que si il n'est pas déjà tout à gauche (coordonnée de colonne = 0) et si la case n'est pas déjà occupée
            player.setPosition(newPosition);
        }
    }

    public void movePlayerRight(){
        Block position = player.getPosition();
        Block newPosition = room.getBlock(position.getLine(), position.getColumn() + 1);

        if(position.getColumn() < GameConfiguration.COLUMN_COUNT - 1 && !newPosition.isOccupied()){ // Il ne peut aller à droite que si il n'est pas déjà tout à droite (coordonnée de colonne = nombre de colonnes - 1) et si la case n'est pas déjà occupée
            player.setPosition(newPosition);
        }
    }

    public void generateEnemies() {
        for (int i = 0; i < 3; i++) {
            int randomRow = getRandomNumber(0, GameConfiguration.LINE_COUNT - 1); // On génère des valeurs aléatoires pour les coordonnées de spawn de l'ennemi
            int randomColumn = getRandomNumber(0, GameConfiguration.COLUMN_COUNT - 1);
            Block position = room.getBlock(randomRow, randomColumn); // On récupère la cellule existante obtenue
            if(!position.isOccupied()){ // Si la cellule n'est pas déjà occupée par quelque-chose
                Enemy enemy = new Enemy(position); // On génère une instance de l'ennemi sur cette case
                add(enemy); // On l'ajoute à la liste d'ennemis
            }
        }
	}

    private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
    }
}
