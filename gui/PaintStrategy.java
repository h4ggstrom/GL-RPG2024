package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import config.GameConfiguration;
import engine.characters.Enemy;
import engine.characters.Player;
import engine.dungeon.Pixel;
import engine.dungeon.Room;
import engine.process.Utility;

/*
 * Dans cette classe, on implémente une stratégie d'affichage pour chaque élement du jeu.
 */
public class PaintStrategy {
    
    // Stratégie d'affichage pour la salle
    public void paint (Room room, Graphics graphics) {
        graphics.drawImage(Utility.readImage("./ressources/room.png"), 0, 0, null);
    }

    // Stratégie d'affichage pour le joueur
    public void paint (Player player, Graphics graphics) {
        Pixel position = player.getPosition();

        graphics.setColor(Color.MAGENTA);
        graphics.fillRect(position.getX(), position.getY(), GameConfiguration.PLAYER_WIDTH, GameConfiguration.PLAYER_HEIGHT); // Le corps
        graphics.setFont(new Font("Dialog", Font.PLAIN, 10));
        graphics.drawString("Player", position.getX() - 5, position.getY() - 5); // Le nom
        graphics.fillRect(position.getX() - 15, position.getY() + GameConfiguration.PLAYER_HEIGHT + 5, player.getHealth(), 2); // La barre de vie
    }

    public void paint(Enemy enemy, Graphics graphics) {
		Pixel position = enemy.getPosition();

		graphics.setColor(Color.RED);
		graphics.fillRect(position.getX(), position.getY(), GameConfiguration.ENEMY_WIDTH, GameConfiguration.ENEMY_HEIGHT); // Le corps
        graphics.drawString("Enemy", position.getX() - 5, position.getY() - 5); // Le nom
        graphics.fillRect(position.getX() - 5, position.getY() + GameConfiguration.ENEMY_HEIGHT + 5, enemy.getHealth(), 2); // La barre de vie
	}
}
