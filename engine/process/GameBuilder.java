package engine.process;

import config.GameConfiguration;
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

        initializePlayer(manager);

        initializeEnemies(manager);

        return manager;
    }

    private static void initializePlayer (CharacterManager manager) {
        Pixel pixel = new Pixel( GameConfiguration.ROOM_CENTER_X - ( GameConfiguration.PLAYER_WIDTH / 2 ) , GameConfiguration.ROOM_CENTER_Y - ( GameConfiguration.PLAYER_HEIGHT / 2 ) );
        Player player = new Player(pixel);
        manager.set(player);
    }

    private static void initializeEnemies(CharacterManager manager) {
        for (int i = 0; i < GameConfiguration.ENEMIES_INIT_NUMBER; i++) {
            int enemyX = getRandomNumber(GameConfiguration.ROOM_LEFT_LIMITATION, GameConfiguration.ROOM_RIGHT_LIMITATION - GameConfiguration.ENEMY_WIDTH);
            int enemyY = getRandomNumber(GameConfiguration.ROOM_UPPER_LIMITATION, GameConfiguration.ROOM_LOWER_LIMITATION - GameConfiguration.ENEMY_HEIGHT);
            Pixel position = new Pixel(enemyX, enemyY); // On instancie sa position
            Enemy enemy = new Enemy(position); // On instancie l'Enemy
            manager.getRoom().addEnemy(enemy); // On l'ajoute à la liste d'ennemis de la Room
            manager.getRoom().addHitbox(enemy.getHitbox()); // On ajoute sa Hitbox à la liste de Hitboxes de la Room
        }
    }

    private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
    }

}
