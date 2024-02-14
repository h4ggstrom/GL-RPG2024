package config;

public class GameConfiguration {

    public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 720;
	
	public static final int GAME_SPEED = 1;

    public static final int PLAYER_DEFAULT_SPEED = 12; // 48 pixels par /10 de secondes ce qui fait 1920px en 4 secondes
    public static final int PLAYER_WIDTH = 20;
    public static final int PLAYER_HEIGHT = 40;

    public static final int ROOM_UPPER_LIMITATION = 52; // Le mur bloque le joueur avant cette valeur en Y
    public static final int ROOM_LOWER_LIMITATION = 624; // Le mur bloque le joueur après cette valeur en Y
    public static final int ROOM_LEFT_LIMITATION = 52; // Le mur bloque le joueur avant cette valeur en X
    public static final int ROOM_RIGHT_LIMITATION = 1007; // Le mur bloque le joueur après cette valeur en X

    public static final int ENEMIES_INIT_NUMBER = 3;
    public static final int ENEMY_WIDTH = 20;
    public static final int ENEMY_HEIGHT = 40;

}