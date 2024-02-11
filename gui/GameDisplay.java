package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import engine.dungeon.Room;

public class GameDisplay extends JPanel {
    
    private static final long serialVersionUID = 1L;

    private Room room;
    private PaintStrategy paintStrategy = new PaintStrategy();

    public GameDisplay(Room room){
        this.room = room;
    }

    @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		paintStrategy.paint(room, g);
	}
}
