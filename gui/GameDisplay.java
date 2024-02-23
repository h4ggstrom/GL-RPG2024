package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import engine.characters.Enemy;
import engine.characters.Player;
import engine.dungeon.Room;
import engine.process.CharacterManager;

public class GameDisplay extends JPanel {
    
    private Room room;
    private CharacterManager manager;
    private PaintStrategy paintStrategy = new PaintStrategy();

    public GameDisplay (Room room, CharacterManager manager) {
        this.room = room;
        this.manager = manager;
    }

    @Override
    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        paintStrategy.paint(room, graphics);

        Player player = manager.getPlayer();
        paintStrategy.paint(player, graphics);

        for (Enemy enemy : manager.getRoom().getEnemies()) {
            paintStrategy.paint(enemy, graphics);
        }
    }

}
