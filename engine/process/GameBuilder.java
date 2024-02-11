package engine.process;

import engine.characters.Player;
import engine.dungeon.Block;
import engine.dungeon.Room;

import config.GameConfiguration;

public class GameBuilder {
    
    public static Room buildRoom() {
        return new Room(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
    }

    public static CharacterManager buildInitCharacters(Room room){
        CharacterManager manager = new CharacterManager(room);

        initializePlayer(room, manager);

        return manager;
    }

    private static void initializePlayer(Room room, CharacterManager manager){
        Block block = room.getBlock((GameConfiguration.LINE_COUNT - 1) / 2, (GameConfiguration.COLUMN_COUNT - 1) / 2);
        Player player = new Player(block);
        manager.set(player);
    }

}
