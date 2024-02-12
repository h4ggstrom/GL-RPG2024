package engine.dungeon;

import engine.characters.Enemy;

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
	Enemy enemy; // Si c'est un ennemi on l'associe à la case

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

	/*
	 * Cette méthode permet de définir l'Enemy présent sur la case
	 */
	public void setEnemy(Enemy enemy){
		this.enemy = enemy;
	}

	/*
	 * Cette méthode permet de récupérer l'Enemy présent sur la case
	 */
	public Enemy getEnemy(){
		return this.enemy;
	}

	/**
	 * Cette méthode vérifie si l'instance du bloc est à côté du bloc comparé
	 * 
	 * @param block le bloc comparé
	 *
	 */
	public boolean sidesWith(Block block){
		return(
			this.column == block.getColumn() && this.line == block.getLine() - 1 || // Si notre bloc est au dessus du bloc comparé
			this.column == block.getColumn() && this.line == block.getLine() + 1 || // Si notre bloc est en dessous du bloc comparé
			this.line == block.getLine() && this.column == block.getColumn() - 1 || // Si notre bloc est à gauche du bloc comparé
			this.line == block.getLine() && this.column == block.getColumn() + 1 || // Si notre bloc est à droite du bloc comparé
			this.line == block.getLine() - 1 && this.column == block.getColumn() - 1 || // Si notre bloc est en haut à gauche du bloc comparé
			this.line == block.getLine() - 1 && this.column == block.getColumn() + 1 || // Si notre bloc est en haut à droite du bloc comparé
			this.line == block.getLine() + 1 && this.column == block.getColumn() - 1 || // Si notre bloc est en bas à gauche du bloc comparé
			this.line == block.getLine() + 1 && this.column == block.getColumn() + 1 // Si notre bloc est en bas à droite du bloc comparé
		);
	}

	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + ", occupied=" + occupied + ", enemy=" + enemy + "]";
	}

}