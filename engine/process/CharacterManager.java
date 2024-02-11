package engine.process;

import config.GameConfiguration;
import engine.characters.Player;
import engine.dungeon.Block;
import engine.dungeon.Room;

public class CharacterManager {
    private Room room;

    private Player player;

    public CharacterManager(Room room){
        this.room = room;
    }

    public void set(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
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
}
