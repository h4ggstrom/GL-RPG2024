package engine.dungeon;

import java.util.ArrayList;

import config.GameConfiguration;

public class Stage {
    
    private ArrayList<Room> rooms;
    private int number;

    public Stage(int number) {
        this.number = number;
        this.rooms = new ArrayList<Room>();
        for(int i = 0 ; i < GameConfiguration.NUMBER_OF_ROOMS ; i++) {
            this.rooms.add(new Room(i+1));
        }
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public int getNumber() {
        return number;
    }

}
