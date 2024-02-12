package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import engine.characters.Enemy;
import engine.characters.Player;
import engine.dungeon.Room;
import engine.process.CharacterManager;

public class GameDisplay extends JPanel {
    
    private static final long serialVersionUID = 1L;

    private Room room;
    private CharacterManager manager;
    private PaintStrategy paintStrategy = new PaintStrategy();

    public GameDisplay(Room room, CharacterManager manager){
        this.room = room;
        this.manager = manager;
    }

    @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		paintStrategy.paint(room, g);

        Player player = manager.getPlayer();
        paintStrategy.paint(player, g);

        for (Enemy enemy : manager.getEnemies()) {
			paintStrategy.paint(enemy, g);
		}
	}
}
