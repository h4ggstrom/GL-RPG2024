package engine.dungeon;

import java.util.ArrayList;

import config.GameConfiguration;

public class Stage {
    
    private ArrayList<Room> rooms;
    private int number;

    public Stage(int number) {
        this.number = number;
        this.rooms = new ArrayList<Room>();
        for(int i = 1 ; i <= GameConfiguration.NUMBER_OF_ROOMS ; i++) {
            Room room = new Room(i);
            // Toutes les 5 salles on place un magasin
            if(i%5==0) {
                room.setShop(true);
            }
            this.rooms.add(room);
        }
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public int getNumber() {
        return number;
    }

    public int setNumber(int number) {
        return this.number=number;
    }

}
