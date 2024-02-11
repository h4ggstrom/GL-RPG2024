package engine.process;

import engine.dungeon.Room;

import config.GameConfiguration;

public class GameBuilder {
    
    public static Room buildRoom() {
        return new Room(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
    }

}
