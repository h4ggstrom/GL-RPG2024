package engine.process;

import engine.characters.Player;
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
}
