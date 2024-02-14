package engine.process;

import config.GameConfiguration;
import engine.characters.Character;
import engine.characters.Enemy;
import engine.characters.Player;
import engine.dungeon.Pixel;
import engine.dungeon.Room;

public class GameBuilder {
    
    public static Room buildRoom() {
        return new Room();
    }

    public static CharacterManager buildInitCharacters (Room room) {
        CharacterManager manager = new CharacterManager(room);

        initializePlayer(room, manager);

        initializeEnemies(room, manager);

        return manager;
    }

    private static void initializePlayer (Room room, CharacterManager manager) {
        Pixel pixel = room.getPixel( (GameConfiguration.WINDOW_WIDTH - 1) / 2, (GameConfiguration.WINDOW_HEIGHT - 1) / 2);
        Player player = new Player(pixel);
        manager.set(player);
    }

    private static void initializeEnemies(Room room, CharacterManager manager) {
        for (int i = 0; i < GameConfiguration.ENEMIES_INIT_NUMBER; i++) {
            int enemyX = getRandomNumber(GameConfiguration.ROOM_LEFT_LIMITATION, GameConfiguration.ROOM_RIGHT_LIMITATION - GameConfiguration.PLAYER_WIDTH);
            int enemyY = getRandomNumber(GameConfiguration.ROOM_UPPER_LIMITATION, GameConfiguration.ROOM_LOWER_LIMITATION - GameConfiguration.PLAYER_HEIGHT);
            Pixel position = room.getPixel(enemyX, enemyY);
            Enemy enemy = new Enemy(position);
            manager.add(enemy); // On l'ajoute à la liste d'ennemis
        }
    }

    private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
    }

}
