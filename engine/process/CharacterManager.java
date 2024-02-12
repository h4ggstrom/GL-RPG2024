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

        if(position.getLine() > 0){ // Il ne peut aller en haut que si il n'est pas déjà tout en haut (coordonnée de ligne = 0)
            Block newPosition = room.getBlock(position.getLine() - 1, position.getColumn());
            player.setPosition(newPosition);
        }
    }

    public void movePlayerDown(){
        Block position = player.getPosition();

        if(position.getLine() < GameConfiguration.LINE_COUNT - 1){ // Il ne peut aller en bas que si il n'est pas déjà tout en bas (coordonnée de ligne = nombre de lignes - 1)
            Block newPosition = room.getBlock(position.getLine() + 1, position.getColumn());
            player.setPosition(newPosition);
        }
    }

    public void movePlayerLeft(){
        Block position = player.getPosition();

        if(position.getColumn() > 0){ // Il ne peut aller à gauche que si il n'est pas déjà tout à gauche (coordonnée de colonne = 0)
            Block newPosition = room.getBlock(position.getLine(), position.getColumn() - 1);
            player.setPosition(newPosition);
        }
    }

    public void movePlayerRight(){
        Block position = player.getPosition();

        if(position.getColumn() < GameConfiguration.COLUMN_COUNT - 1){ // Il ne peut aller à droite que si il n'est pas déjà tout à droite (coordonnée de colonne = nombre de colonnes - 1)
            Block newPosition = room.getBlock(position.getLine(), position.getColumn() + 1);
            player.setPosition(newPosition);
        }
    }

    public void generateEnemies() {
        for (int i = 0; i < 3; i++) {
            int randomRow = getRandomNumber(0, GameConfiguration.LINE_COUNT - 1);
            int randomColumn = getRandomNumber(0, GameConfiguration.COLUMN_COUNT - 1);
            Block position = new Block(randomRow, randomColumn);
            Enemy enemy = new Enemy(position);
            add(enemy);
        }
	}

    private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}

    private void moveEnemies() {
        Block PlayerPosition = player.getPosition(); //On détermine la position du joueur, les ennemies vont logiquement se diriger vers player 

		for (Enemy enemy : enemies) {
			Block EnemyPosition = enemy.getPosition();
            
            if (PlayerPosition.getLine() > EnemyPosition.getLine()) { // Si l'ennemi est au dessus du joueur, déplacer vers le bas
                Block newPosition = room.getBlock(EnemyPosition.getLine() + 1, EnemyPosition.getColumn());
                enemy.setPosition(newPosition);
            } else if (PlayerPosition.getLine() < EnemyPosition.getLine()) { // Si l'ennemi est en dessous du joueur, déplacer vers le bas
                Block newPosition = room.getBlock(EnemyPosition.getLine() - 1, EnemyPosition.getColumn());
                enemy.setPosition(newPosition);
            }

            if (PlayerPosition.getColumn() > EnemyPosition.getColumn()) { // Si l'ennemi est à gauche du joueur, déplacer vers la droite
                Block newPosition = room.getBlock(EnemyPosition.getLine(), EnemyPosition.getColumn() + 1);
                enemy.setPosition(newPosition);
            } else if (PlayerPosition.getColumn() < EnemyPosition.getColumn()) { // Si l'ennemi est à droite du joueur, déplacer vers la gauche
                Block newPosition = room.getBlock(EnemyPosition.getLine(), EnemyPosition.getColumn() - 1);
                enemy.setPosition(newPosition);
            }
		}
	}

    public void nextRound() {
		moveEnemies();
	}
}
