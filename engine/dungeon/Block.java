package engine.dungeon;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */

public class Block {
	private int line;
	private int column;
	private boolean occupied = false; // Définit si la case est occupée par quelque-chose (vide par défaut)

	public Block(int line, int column) {
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	/*
	 * Cette méthode permet de changer l'état d'une cellule à "occupée"
	 */
	public void occupy() {
		this.occupied = true;
	}

	/*
	 * Cette méthode permet de changer l'état d'une cellule à "libre"
	 */
	public void free() {
		this.occupied = false;
	}

	/*
	 * Cette méthode retourne l'état de la cellule (occupée ou libre)
	 */
	public boolean isOccupied() {
		return this.occupied;
	}

	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + "]";
	}

}