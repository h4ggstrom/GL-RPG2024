package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.entities.Entity;
import engine.entities.Hitbox;
import engine.entities.characters.Enemy;
import engine.entities.characters.GameCharacter;
import engine.entities.characters.Player;
import engine.process.Utility;

/*
 * Dans cette classe, on implémente une stratégie d'affichage pour chaque élement du jeu.
 */
public class PaintStrategy {
      
    // Stratégie d'affichage pour la salle
    public void paint (Room room, Graphics graphics) {
        graphics.drawImage(Utility.readImage("./src/ressources/assets/room/"+ room.getFileName() +".png"), 0, 0, null);
        graphics.setFont(new Font("Dialog", Font.PLAIN, 10)); // Le nom
        graphics.drawString("Etage : " + Player.getInstance().getCurrentStage(), 30,30);
        graphics.drawString("Salle : " + Player.getInstance().getCurrentRoom(), 100,30); 
        graphics.setColor(Color.RED);
        graphics.drawString("Difficulté : " + room.getDifficulty(), 30, 45);
        graphics.setColor(Color.BLACK);    
        if(Player.getInstance().getHealth() <= 0){
            Position gameoverPosition = GameConfiguration.GAME_OVER_POSITION;
            graphics.setColor(Color.BLACK);
            graphics.fillRect(gameoverPosition.getX(), gameoverPosition.getY(), GameConfiguration.GAME_OVER_WIDTH, GameConfiguration.GAME_OVER_HEIGHT);
            graphics.setFont(new Font("Serif", Font.BOLD, GameConfiguration.GAME_OVER_TITLEFONTSIZE));
            graphics.setColor(Color.RED);
            graphics.drawString("GAME OVER", 470 , 360);
        }
    }

    public void paint(Entity entity, Graphics graphics) {
        Position position = entity.getHitbox().getUpperLeft();
        String filePath = "./src/ressources/assets/entity/" + entity.getEntityType() + ".png";
        graphics.drawImage(Utility.readImage(filePath), position.getX(), position.getY(), null);
        
        if(entity instanceof GameCharacter) {
            GameCharacter character = (GameCharacter)entity;
            int lifebar_xshift = 0;
            if (character instanceof Enemy) {
                graphics.setColor(Color.RED);
                lifebar_xshift = GameConfiguration.ENEMY_LIFEBAR_XSHIFT;
            }
            else if (character instanceof Player) {
                graphics.setColor(Color.MAGENTA);
                lifebar_xshift = GameConfiguration.PLAYER_LIFEBAR_XSHIFT;

                // Partie portée d'attaque pour le joueur
                Position hitbox_center = character.getHitbox().getCenter();
                int x_center = hitbox_center.getX();
                int y_center = hitbox_center.getY();
                graphics.drawOval(x_center - character.getAttackRange(), y_center - character.getAttackRange(), character.getAttackRange() * 2, character.getAttackRange() * 2);
            }
            
            // Nom du personnage
            graphics.setFont(new Font("Dialog", Font.PLAIN, 10));
            graphics.drawString(character.getEntityName(), position.getX() + GameConfiguration.CHARACTER_NAMETAG_XSHIFT, position.getY() + GameConfiguration.CHARACTER_NAMETAG_YSHIFT);

            // Barre de vie du personnage
            graphics.fillRect(position.getX() + lifebar_xshift, entity.getHitbox().getBottomLeft().getY() + GameConfiguration.CHARACTER_LIFEBAR_YSHIFT, character.getHealth(), 2);
        }

        // Partie Hitbox (à des fins de débuggage)
        Hitbox hitbox = entity.getHitbox();
        Position ul = hitbox.getUpperLeft();
        Position ur = hitbox.getUpperRight();
        Position ct = hitbox.getCenter();
        Position bl = hitbox.getBottomLeft();
        Position br = hitbox.getBottomRight();
        graphics.setColor(Color.BLACK);
        graphics.drawLine(ul.getX(), ul.getY(), ur.getX(), ur.getY());
        graphics.drawLine(ul.getX(), ul.getY(), bl.getX(), bl.getY());
        graphics.drawLine(bl.getX(), bl.getY(), br.getX(), br.getY());
        graphics.drawLine(ur.getX(), ur.getY(), br.getX(), br.getY());

        graphics.drawLine(ul.getX(), ul.getY(), ct.getX(), ct.getY());
        graphics.drawLine(bl.getX(), bl.getY(), ct.getX(), ct.getY());
        graphics.drawLine(ur.getX(), ur.getY(), ct.getX(), ct.getY());
        graphics.drawLine(br.getX(), br.getY(), ct.getX(), ct.getY());
    }
}
