package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import config.GameConfiguration;
import engine.characters.Enemy;
import engine.characters.GameCharacter;
import engine.characters.Player;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.items.Item;
import engine.items.weapons.Weapon;
import engine.process.CharacterManager;
import engine.process.Utility;

/*
 * Dans cette classe, on implémente une stratégie d'affichage pour chaque élement du jeu.
 */
public class PaintStrategy {
      
    // Stratégie d'affichage pour la salle
    public void paint (CharacterManager manager,Room room, Graphics graphics) {
        // Si la Room n'a pas été nettoyée de tous ses monstres
        if (!room.getCleaned())
            graphics.drawImage(Utility.readImage("./ressources/room.png"), 0, 0, null);
        // Sinon
        else
            graphics.drawImage(Utility.readImage("./ressources/room_open.png"), 0, 0, null);
            //if (manager.changeRoom()){
                //graphics.drawImage(Utility.readImage("./ressources/new_room.png"), 0, 0, null);
            //}
    }

    public void paint(GameCharacter character, Graphics graphics) {
        Position position = character.getPosition();
        int height = 0;
        int width = 0;
        String name = "";
        int lifebar_xshift = 0;
        if (character instanceof Enemy) {
            graphics.setColor(Color.RED);
            height = GameConfiguration.ENEMY_HEIGHT;
            width = GameConfiguration.ENEMY_WIDTH;
            name = "Enemy";
            lifebar_xshift = GameConfiguration.ENEMY_LIFEBAR_XSHIFT;
        }
        else if (character instanceof Player) {
            graphics.setColor(Color.MAGENTA);
            height = GameConfiguration.ENEMY_HEIGHT;
            width = GameConfiguration.ENEMY_WIDTH;
            name = "Player";
            lifebar_xshift = GameConfiguration.PLAYER_LIFEBAR_XSHIFT;
        }

        // Partie corps
		graphics.fillRect(position.getX(), position.getY(), width, height); // Le corps
        graphics.setFont(new Font("Dialog", Font.PLAIN, 10)); // Le nom
        graphics.drawString(name, position.getX() + GameConfiguration.CHARACTER_NAMETAG_XSHIFT, position.getY() + GameConfiguration.CHARACTER_NAMETAG_YSHIFT);
        graphics.fillRect(position.getX() + lifebar_xshift, position.getY() + height + GameConfiguration.CHARACTER_LIFEBAR_YSHIFT, character.getHealth(), 2); // La barre de vie

        // Partie portée d'attaque
        Position hitbox_center = character.getHitbox().getCenter();
        int x_center = hitbox_center.getX();
        int y_center = hitbox_center.getY();
        Weapon weapon = (Weapon)character.getWeaponSlot().getItem();
        graphics.drawOval(x_center - weapon.getAttackRange(), y_center - weapon.getAttackRange(), weapon.getAttackRange() * 2, weapon.getAttackRange() * 2);
    }

    public void paint(Position position, Item item, Graphics graphics) {
        String itemFilePath = "";
        if (item instanceof Weapon)  itemFilePath = "./ressources/sword.png";
        graphics.drawImage(Utility.readImage(itemFilePath), position.getX(), position.getY(), null);
    }

}