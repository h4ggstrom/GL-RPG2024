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
import engine.process.management.RessourceManager;

/*
 * Dans cette classe, on implémente une stratégie d'affichage pour chaque élement du jeu.
 */
public class PaintStrategy {

    public RessourceManager ressourceManager = RessourceManager.getInstance();
    public String folderPath = "./src/ressources/assets/";
    public String currentFolder = "stage1/";
      
    // Stratégie d'affichage pour la salle
    public void paint(Room room, Graphics graphics) {

        if(Player.getInstance().getStageNumber() == 1) {
            currentFolder = "stage1/";
        }
        else if(Player.getInstance().getStageNumber() == 2) {
            currentFolder = "stage2/";
        }
        else if(Player.getInstance().getStageNumber() == 3) {
            currentFolder = "stage3/";
        }

        // On dessine le sol
        graphics.drawImage(ressourceManager.getImage(folderPath + currentFolder + "ground.png"), 0, 0, null);
        
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

        if(entity.getEntityType().equals(GameConfiguration.WALL_ASSET_ENTITYTYPE) || entity.getEntityType().equals(GameConfiguration.GATE_ASSET_ENTITYTYPE)){
            Environment environment = (Environment)entity;
            filePath = folderPath + currentFolder + entity.getEntityType() + ".png";
            Position upperLeft = environment.getHitbox().getUpperLeft();
            int width = environment.getHitbox().getWidth();
            int height = environment.getHitbox().getHeight();

            // Charger l'image de texture
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
        graphics.drawString("Etage : " + Player.getInstance().getStageNumber(), 30,30);
        graphics.drawString("Salle : " + Player.getInstance().getRoomNumber(), 100,30);
    }

    public void paintPlayerInfo(Graphics graphics) {
        Player player = Player.getInstance();
    
        // Barre de vie
        graphics.setColor(Color.WHITE);
        graphics.fillRect(30, 680, 5 * player.getMaxHealth(), 30);
        graphics.setColor(Color.RED);
        graphics.fillRect(32, 684, 5 * player.getHealth(), 26);
        graphics.setColor(Color.BLACK);
        graphics.drawString("PV : " + player.getHealth() + "/" + player.getMaxHealth(), 30, 695);
    
        // Icone d'état d'attaque (peut attaquer ou non)
        String attackIconPath = player.canAttack() ? "./src/ressources/assets/canAttack.png" : "./src/ressources/assets/cannotAttack.png";
        BufferedImage attackIcon = ressourceManager.getImage(attackIconPath);
        int attackIconX = 5 * player.getMaxHealth() + 50;
        graphics.drawImage(attackIcon, attackIconX, 670, null);
    
        // Calculer la hauteur de la barre de chargement d'attaque basée sur le pourcentage d'attaque disponible
        double attackPercentage = ((double) player.getAttackPossibility() / player.getAttackSpeed()) * 100;
        int attackBarHeight = (int) (30 * (attackPercentage / 100));
    
        // Barre de chargement d'attaque
        int attackBarX = attackIconX + attackIcon.getWidth() + 10;
        graphics.setColor(Color.GRAY); // Couleur du fond de la barre de chargement
        graphics.fillRect(attackBarX, 680, 20, 30);
        graphics.setColor(Color.GREEN); // Couleur de la barre de progression
        graphics.fillRect(attackBarX, 680 + (30 - attackBarHeight), 20, attackBarHeight);
        graphics.setColor(Color.BLACK); // Bordure de la barre de chargement
        graphics.drawRect(attackBarX, 680, 20, 30);
    
        // Partie portée d'attaque pour le joueur
        Position hitbox_center = player.getHitbox().getCenter();
        int x_center = hitbox_center.getX();
        int y_center = hitbox_center.getY();
        graphics.drawOval(x_center - player.getAttackRange(), y_center - player.getAttackRange(), player.getAttackRange() * 2, player.getAttackRange() * 2);
    }
    
}
