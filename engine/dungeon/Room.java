package engine.dungeon;

import java.util.ArrayList;

import config.GameConfiguration;
import engine.characters.GameCharacter;

public class Room {
    
    private Pixel[][] pixels;
    private ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();

    public Room() {
        pixels = new Pixel[GameConfiguration.WINDOW_WIDTH][GameConfiguration.WINDOW_HEIGHT];
        
        for (int x = 0 ; x < GameConfiguration.WINDOW_WIDTH ; x++) {
            for (int y = 0 ; y < GameConfiguration.WINDOW_HEIGHT ; y++) {
                pixels[x][y] = new Pixel(x, y);
            }
        }
    }

    public Pixel[][] getPixels() {
        return this.pixels;
    }

    public Pixel getPixel(int x, int y) {
        return pixels[x][y];
    }

    /**
     * Cette méthode ajoute un personnage à la liste des personnages présents dans la room
     * @param character le personnage à ajouter
     */
    public void addCharacter (GameCharacter character) {
        this.characters.add(character);
    }

}
