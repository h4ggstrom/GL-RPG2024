package engine.dungeon;

import config.GameConfiguration;

public class Room {
    
    private Pixel[][] pixels;

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

}
