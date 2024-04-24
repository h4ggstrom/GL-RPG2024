package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import config.GameConfiguration;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.entities.Entity;
import engine.entities.Hitbox;
import engine.entities.characters.Enemy;
import engine.entities.characters.GameCharacter;
import engine.entities.characters.Player;
import engine.entities.environment.Environment;
import engine.entities.environment.GateEnv;
import engine.entities.environment.WallEnv;
import engine.process.management.RessourceManager;

/*
 * Dans cette classe, on implémente une stratégie d'affichage pour chaque élement du jeu.
 */
public class PaintStrategy {

    public RessourceManager ressourceManager = RessourceManager.getInstance();
      
    // Stratégie d'affichage pour la salle
    public void paint(Room room, Graphics graphics) {
        // On dessine le sol
        graphics.drawImage(ressourceManager.getImage("./src/ressources/assets/ground.png"), 0, 0, null);
        
        // Écran de fin de partie
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
        String extension = ".png";
        if(entity instanceof GameCharacter) {
            extension = ".gif";
        }
        String filePath = "./src/ressources/assets/entity/" + entity.getEntityType() + extension;

        if(entity instanceof WallEnv || entity instanceof GateEnv) {
            Environment environment = (Environment)entity;
            Position upperLeft = environment.getHitbox().getUpperLeft();
            int width = environment.getHitbox().getWidth();
            int height = environment.getHitbox().getHeight();

            // Charger l'image de texture de pierre
            BufferedImage stoneImage = ressourceManager.getImage(filePath);
            // Redimensionner l'image pour qu'elle corresponde à la hitbox du mur
            BufferedImage scaledImage = stoneImage.getSubimage(0, 0, width, height);

            // Dessiner l'image redimensionnée à la position spécifiée par la hitbox
            graphics.drawImage(scaledImage, upperLeft.getX(), upperLeft.getY(), null);
        }

        else {
            // On peut afficher l'image telle quelle à la position de l'entité
            graphics.drawImage(ressourceManager.getGif(filePath).getImage(), position.getX(), position.getY(), null);

            // Cas si l'entité est un personnage
            if(entity instanceof Enemy) {
                graphics.setColor(Color.RED);
                GameCharacter character = (GameCharacter)entity;
                int lifebar_xshift = GameConfiguration.ENEMY_LIFEBAR_XSHIFT;
                if (character instanceof Enemy) {
                    graphics.setColor(Color.RED);
                    lifebar_xshift = GameConfiguration.ENEMY_LIFEBAR_XSHIFT;
                }
                
                // Nom du personnage
                graphics.setFont(new Font("Dialog", Font.PLAIN, 10));
                graphics.drawString(character.getEntityName(), position.getX() + GameConfiguration.CHARACTER_NAMETAG_XSHIFT, position.getY() + GameConfiguration.CHARACTER_NAMETAG_YSHIFT);

                // Barre de vie du personnage
                graphics.fillRect(position.getX() + lifebar_xshift, entity.getHitbox().getBottomLeft().getY() + GameConfiguration.CHARACTER_LIFEBAR_YSHIFT, character.getHealth(), 2);
            }
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

    public void paintLevelInfo(Graphics graphics) {
        // Informations sur la salle
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Dialog", Font.PLAIN, 10));
        graphics.drawString("Etage : " + Player.getInstance().getstageNumber(), 30,30);
        graphics.drawString("Salle : " + Player.getInstance().getRoomNumber(), 100,30);
    }

    public void paintPlayerInfo(Graphics graphics) {
        Player player = Player.getInstance();

        // Barre de vie
        graphics.setColor(Color.WHITE);
        graphics.fillRect(30, 680, 5*player.getMaxHealth(), 30);
        graphics.setColor(Color.RED);
        graphics.fillRect(32, 684, 5*player.getHealth(), 26);
        graphics.setColor(Color.BLACK);
        graphics.drawString("PV : " + player.getHealth() + "/" + player.getMaxHealth(), 30, 695);

        // Peut-il attaquer ?
        String filePath = "";
        if(player.canAttack()) {
            filePath = "./src/ressources/assets/canAttack.png";
        }
        else {
            filePath = "./src/ressources/assets/cannotAttack.png";
        }
        graphics.drawImage(ressourceManager.getImage(filePath), 5*player.getMaxHealth() + 50, 670, null);

        // Partie portée d'attaque pour le joueur
        Position hitbox_center = player.getHitbox().getCenter();
        int x_center = hitbox_center.getX();
        int y_center = hitbox_center.getY();
        graphics.drawOval(x_center - player.getAttackRange(), y_center - player.getAttackRange(), player.getAttackRange() * 2, player.getAttackRange() * 2);
    }
}
