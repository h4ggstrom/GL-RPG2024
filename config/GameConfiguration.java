package config;

public class GameConfiguration {

    public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 720;
	
	public static final int GAME_SPEED = 1;

    public static final int PLAYER_DEFAULT_SPEED = 10;
    public static final int PLAYER_WIDTH = 20;
    public static final int PLAYER_HEIGHT = 40;
    public static final int PLAYER_HEALTH = 50;

    public static final int ROOM_UPPER_LIMITATION = 52; // Le mur bloque le joueur avant cette valeur en Y
    public static final int ROOM_LOWER_LIMITATION = 624; // Le mur bloque le joueur après cette valeur en Y
    public static final int ROOM_LEFT_LIMITATION = 52; // Le mur bloque le joueur avant cette valeur en X
    public static final int ROOM_RIGHT_LIMITATION = 1007; // Le mur bloque le joueur après cette valeur en X

    public static final int ENEMIES_INIT_NUMBER = 4;
    public static final int ENEMY_WIDTH = 20;
    public static final int ENEMY_HEIGHT = 40;
    public static final int ENEMY_HEALTH = 30;

    public static final int CORRECTCLICKSHIFT_X = -8; // Le MouseListener séléctionne une coordonnée en X incorrecte de 8 pixels en trop
    public static final int CORRECTCLICKSHIFT_Y = -32; // Le MouseListener séléctionne une coordonnée en Y incorrecte de 31 pixels en trop

}