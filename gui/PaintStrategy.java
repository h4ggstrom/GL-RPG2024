package gui;

import java.awt.Graphics;
import java.awt.Color;

import config.GameConfiguration;
import engine.dungeon.Block;
import engine.dungeon.Room;

public class PaintStrategy {
    
    public void paint(Room room, Graphics graphics){
        int blockSize = GameConfiguration.BLOCK_SIZE;
        Block[][] blocks = room.getBlocks();

        for (int lineIndex = 0; lineIndex < room.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < room.getColumnCount(); columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

				if ((lineIndex + columnIndex) % 2 == 0) {
					graphics.setColor(Color.GRAY);
					graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				}
			}
		}
    }

}
