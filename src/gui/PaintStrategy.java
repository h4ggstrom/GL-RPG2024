package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.entities.Coin;
import engine.entities.characters.Enemy;
import engine.entities.characters.GameCharacter;
import engine.entities.characters.Player;
import engine.entities.items.Item;
import engine.process.Utility;

/*
 * Dans cette classe, on implémente une stratégie d'affichage pour chaque élement du jeu.
 */
public class PaintStrategy {
      
    // Stratégie d'affichage pour la salle
    public void paint (Room room, Graphics graphics) {
        graphics.drawImage(Utility.readImage("./src/ressources/"+ room.getFileName() +".png"), 0, 0, null);
        graphics.setFont(new Font("Dialog", Font.PLAIN, 10)); // Le nom
        graphics.drawString("Etage : " + Player.getInstance().getCurrentStage(), 30,30);
        graphics.drawString("Salle : " + Player.getInstance().getCurrentRoom(), 100,30);        
        if(Player.getInstance().getHealth() <= 0){
            Position gameoverPosition = GameConfiguration.GAME_OVER_POSITION;
            graphics.setColor(Color.BLACK);
            graphics.fillRect(gameoverPosition.getX(), gameoverPosition.getY(), GameConfiguration.GAME_OVER_WIDTH, GameConfiguration.GAME_OVER_HEIGHT);
            graphics.setFont(new Font("Serif", Font.BOLD, GameConfiguration.GAME_OVER_TITLEFONTSIZE));
            graphics.setColor(Color.RED);
            graphics.drawString("GAME OVER", 470 , 360);
        }
    }

    public void paint(GameCharacter character, Graphics graphics) {
        Position position = character.getHitbox().getUpperLeft();
        String name = "";
        int lifebar_xshift = 0;
        String filePath = "";
        int height = 0;
        if (character instanceof Enemy) {
            graphics.setColor(Color.RED);
            name = "Enemy";
            lifebar_xshift = GameConfiguration.ENEMY_LIFEBAR_XSHIFT;
            filePath = "./src/ressources/enemy.png";
            height = GameConfiguration.ENEMY_HEIGHT;
        }
        else if (character instanceof Player) {
            graphics.setColor(Color.MAGENTA);;
            name = "Player";
            lifebar_xshift = GameConfiguration.PLAYER_LIFEBAR_XSHIFT;
            filePath = "./src/ressources/player0.png";
            height = GameConfiguration.PLAYER_HEIGHT;
        }

        // Partie corps
		graphics.drawImage(Utility.readImage(filePath), position.getX(), position.getY(), null);
        graphics.setFont(new Font("Dialog", Font.PLAIN, 10)); // Le nom
        graphics.drawString(name, position.getX() + GameConfiguration.CHARACTER_NAMETAG_XSHIFT, position.getY() + GameConfiguration.CHARACTER_NAMETAG_YSHIFT);
        graphics.fillRect(position.getX() + lifebar_xshift, position.getY() + height + GameConfiguration.CHARACTER_LIFEBAR_YSHIFT, character.getHealth(), 2); // La barre de vie

        if(character instanceof Player) {
            // Partie portée d'attaque
            Position hitbox_center = character.getHitbox().getCenter();
            int x_center = hitbox_center.getX();
            int y_center = hitbox_center.getY();
            graphics.drawOval(x_center - character.getAttackRange(), y_center - character.getAttackRange(), character.getAttackRange() * 2, character.getAttackRange() * 2);
        }
    }

    public void paint(Item item, Graphics graphics) {
        String itemFilePath = "./src/ressources/" + item.getEntityType() + ".png";
        // on prend le coin haut gauche pour des soucis d'affichage
        Position position = item.getHitbox().getUpperLeft();
        graphics.drawImage(Utility.readImage(itemFilePath), position.getX(), position.getY(), null);
    }

    public void paint(Coin coin , Graphics graphics) {
        String itemFilePath = "./src/ressources/" + coin.getEntityType() + ".png";
        // on prend le coin haut gauche pour des soucis d'affichage
        Position position = coin.getHitbox().getUpperLeft();
        graphics.drawImage(Utility.readImage(itemFilePath), position.getX(), position.getY(), null);
    }
}
