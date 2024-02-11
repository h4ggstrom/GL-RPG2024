package engine.dungeon;

public class Room {
	private Block[][] blocks;

	private int lineCount;
	private int columnCount;

	public Room(int lineCount, int columnCount) {
		this.lineCount = lineCount;
		this.columnCount = columnCount;

		blocks = new Block[lineCount][columnCount];

		for (int lineIndex = 0; lineIndex < this.lineCount; lineIndex++) {
			for (int columnIndex = 0; columnIndex < this.columnCount; columnIndex++) {
				blocks[lineIndex][columnIndex] = new Block(lineIndex, columnIndex);
			}
		}
	}

    public Block[][] getBlocks(){
        return blocks;
    }

    public Block getBlock(int line, int column){
        return blocks[line][column];
    }

    public int getLineCount(){
        return lineCount;
    }

    public int getColumnCount(){
        return columnCount;
    }
}
