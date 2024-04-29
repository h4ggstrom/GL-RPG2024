package engine.dungeon;

import java.util.ArrayList;

import config.GameConfiguration;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe est un étage du donjon
 * 
 * @author Thibault TERRIÉ
 */
public class Stage {
    
    private ArrayList<Room> rooms;
    private int number;

    /**
     * Constructeur de la classe Stage.
     * Il s'occupe de générer toutes les salles de l'étage.
     * 
     * @see Room
     * 
     * @param number le numéro de l'étage
     */
    public Stage(int number) {
        this.number = number;
        this.rooms = new ArrayList<Room>();
        for(int i = 1 ; i <= GameConfiguration.NUMBER_OF_ROOMS ; i++) {
            Room room = new Room(i);
            // Toutes les 5 salles on place un magasin
            if(i%5==0) {
                room.setShop(true);
            }
            // La room du boss à chaque fin d'étage
            if(i==GameConfiguration.NUMBER_OF_ROOMS) {
                room.setBoss(true);
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
