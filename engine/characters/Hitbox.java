package engine.characters;

import config.GameConfiguration;
import engine.dungeon.Pixel;
import engine.dungeon.Room;

/**
 * Une Hitbox est un amas de Pixel intraversable et touchables avec des attaques
 */
public class Hitbox {
    
    private Pixel[][] hitbox;

    public Hitbox (Room room, Pixel position) {
        hitbox = new Pixel[GameConfiguration.ENEMY_WIDTH][GameConfiguration.ENEMY_HEIGHT]; // On va réunir les pixels de la box dans ce tableau de dimension à la taille de l'ennemi

        int i = 0; // On initialise les valeurs de parcourt de hitbox
        int j = 0;
        for (int x = position.getX() ; x < position.getX() + GameConfiguration.ENEMY_WIDTH ; x++) { // On parcout tous les pixels x de la largeur de l'ennemi
            for (int y = position.getY() ; y < position.getY() + GameConfiguration.ENEMY_HEIGHT ; y++) { // On parcout tous les pixels y de la longueur de l'ennemi
                Pixel pixel = room.getPixel(x, y);
                hitbox[i][j] = pixel;
                j++;
            }
            i++;
        }
    }

}
