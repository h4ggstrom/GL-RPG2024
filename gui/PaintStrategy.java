package gui;

import java.awt.Graphics;
import java.awt.Color;

import config.GameConfiguration;
import engine.characters.Enemy;
import engine.characters.Player;
import engine.dungeon.Block;
import engine.dungeon.Room;

/*
 * Dans cette classe, on implémente une stratégie d'affichage pour chaque élement.
 */
public class PaintStrategy {

    // Stratégie d'affichage pour la salle
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

    // Stratégie d'affichage pour le joueur
    public void paint(Player player, Graphics graphics){
        Block position = player.getPosition();
        int blockSize = GameConfiguration.BLOCK_SIZE;

        int column = position.getColumn();
        int line = position.getLine();

        // On récupère les coordonnées du pixel supérieur gauche de la case du joueur
        int x = column * blockSize; // La coordonnée x du pixel s'obtient en multipliant le numéro de colonne par la largeur d'une colonne
        int y = line * blockSize; // La coordonnée y du pixel s'obtient en multipliant le numéro de la ligne par la longueur d'une ligne

        graphics.setColor(Color.MAGENTA); // On définit la couleur de notre pinceau
        graphics.fillOval(x, y, 40, 40); // On dessine un disque dans un rectangle dont le coin supérieur gauche est aux coordonnées de notre pixel
    }

    public void paint(Enemy enemy, Graphics graphics) {
		Block position = enemy.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.RED);
		graphics.fillOval(x * blockSize, y * blockSize, blockSize, blockSize);
	}
}
