package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import config.GameConfiguration;
import engine.Abilities.Ability;
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
        // Si la Room n'a pas été nettoyée de tous ses monstres
        if (!room.getCleaned())
            graphics.drawImage(Utility.readImage("./ressources/room.png"), 0, 0, null);
        // Sinon
        else
            graphics.drawImage(Utility.readImage("./ressources/room_open.png"), 0, 0, null);
    }

    // Stratégie d'affichage pour le joueur
    public void paint (Player player, Graphics graphics) {
        Pixel position = player.getPosition();
        int x = position.getX();
        int y = position.getY();

        Pixel hitbox_center = player.getHitbox().getCenter();
        int x_center = hitbox_center.getX();
        int y_center = hitbox_center.getY();

        Pixel hitbox_UL = player.getHitbox().getUpperLeft();
        int x_UL = hitbox_UL.getX();
        int y_UL = hitbox_UL.getY();

        Pixel hitbox_UR = player.getHitbox().getUpperRight();
        int x_UR = hitbox_UR.getX();
        int y_UR = hitbox_UR.getY();

        Pixel hitbox_BL = player.getHitbox().getBottomLeft();
        int x_BL = hitbox_BL.getX();
        int y_BL = hitbox_BL.getY();

        Pixel hitbox_BR = player.getHitbox().getBottomRight();
        int x_BR = hitbox_BR.getX();
        int y_BR = hitbox_BR.getY();

        // Partie Hitbox
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x_UL, y_UL, 1, 1);
        graphics.fillRect(x_UR, y_UR, 1, 1);
        graphics.fillRect(x_BL, y_BL, 1, 1);
        graphics.fillRect(x_BR, y_BR, 1, 1);
        graphics.fillRect(x_center, y_center, 1, 1);

        // Partie joueur
        graphics.setColor(Color.MAGENTA);
        graphics.fillRect(x, y, GameConfiguration.PLAYER_WIDTH, GameConfiguration.PLAYER_HEIGHT); // Le corps
        graphics.setFont(new Font("Dialog", Font.PLAIN, 10));
        graphics.drawString("Player", x - 5, y - 5); // Le nom
        graphics.fillRect(x - 15, y + GameConfiguration.PLAYER_HEIGHT + 5, player.getHealth(), 2); // La barre de vie
        graphics.drawOval(x_center - GameConfiguration.WEAPON_RANGE, y_center - GameConfiguration.WEAPON_RANGE, GameConfiguration.WEAPON_RANGE * 2, GameConfiguration.WEAPON_RANGE * 2);
    }

    // Stratégie d'affichage pour les ennemis
    public void paint(Enemy enemy, Graphics graphics) {
		Pixel position = enemy.getPosition();

		graphics.setColor(Color.RED);
		graphics.fillRect(position.getX(), position.getY(), GameConfiguration.ENEMY_WIDTH, GameConfiguration.ENEMY_HEIGHT); // Le corps
        graphics.drawString("Enemy", position.getX() - 5, position.getY() - 5); // Le nom
        graphics.fillRect(position.getX() - 5, position.getY() + GameConfiguration.ENEMY_HEIGHT + 5, enemy.getHealth(), 2); // La barre de vie
	}

    // Stratégie d'affichage pour les attaques
    public void paint(Ability ability, Graphics graphics) {
        Pixel startPosition = ability.getCaster().getHitbox().getCenter();
        Pixel finishPosition = ability.getDirection();

        int xstart = startPosition.getX();
        int ystart = startPosition.getY();

        int xfinish = finishPosition.getX();
        int yfinish = finishPosition.getY();

        graphics.setColor(Color.ORANGE);
        graphics.drawLine(xstart, ystart, xfinish, yfinish);
    }
}
