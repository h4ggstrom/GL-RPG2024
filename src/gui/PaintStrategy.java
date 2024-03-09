package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import config.GameConfiguration;
import engine.characters.Enemy;
import engine.characters.GameCharacter;
import engine.characters.Hitbox;
import engine.characters.Player;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.items.Inventory;
import engine.items.Item;
import engine.items.Slot;
import engine.items.weapons.Sword;
import engine.items.weapons.Weapon;
import engine.process.Utility;

/*
 * Dans cette classe, on implémente une stratégie d'affichage pour chaque élement du jeu.
 */
public class PaintStrategy {
      
    // Stratégie d'affichage pour la salle
    public void paint (Room room, Graphics graphics) {
        graphics.drawImage(Utility.readImage("./src/ressources/"+ room.getFileName() +".png"), 0, 0, null);
    }

    public void paint(GameCharacter character, Graphics graphics) {
        Position position = character.getHitbox().getUpperLeft();
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

        // Partie Hitbox (à des fins de débuggage)
        Hitbox hitbox = character.getHitbox();
        Position ul = hitbox.getUpperLeft();
        Position ur = hitbox.getUpperRight();
        Position ct = hitbox.getCenter();
        Position bl = hitbox.getBottomLeft();
        Position br = hitbox.getBottomLeft();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(ul.getX(), ul.getY(),1, 1);
        graphics.fillRect(ur.getX(), ur.getY(),1, 1);
        graphics.fillRect(ct.getX(), ct.getY(),1, 1);
        graphics.fillRect(bl.getX(), bl.getY(),1, 1);
        graphics.fillRect(br.getX(), br.getY(),1, 1);
    }

    public void paint(Item item, Graphics graphics) {
        String itemFilePath = "";
        // on prend le coin haut gauche pour des soucis d'affichage
        Position position = item.getHitbox().getUpperLeft();
        if (item instanceof Sword) {
            itemFilePath = "./src/ressources/sword.png";
        }
        graphics.drawImage(Utility.readImage(itemFilePath), position.getX(), position.getY(), null);

        // Partie Hitbox (à des fins de débuggage)
        Hitbox hitbox = item.getHitbox();
        Position ul = hitbox.getUpperLeft();
        Position ur = hitbox.getUpperRight();
        Position ct = hitbox.getCenter();
        Position bl = hitbox.getBottomLeft();
        Position br = hitbox.getBottomLeft();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(ul.getX(), ul.getY(),1, 1);
        graphics.fillRect(ur.getX(), ur.getY(),1, 1);
        graphics.fillRect(ct.getX(), ct.getY(),1, 1);
        graphics.fillRect(bl.getX(), bl.getY(),1, 1);
        graphics.fillRect(br.getX(), br.getY(),1, 1);
    }

    public void paint(Inventory inventory, Graphics graphics) {

        Position inventoryPosition = GameConfiguration.INVENTORY_POSITION;
        graphics.setColor(GameConfiguration.INVENTORY_COLOR);
        graphics.fillRect(inventoryPosition.getX(), inventoryPosition.getY(), GameConfiguration.INVENTORY_WIDTH, GameConfiguration.INVENTORY_HEIGHT);
        graphics.setFont(new Font("Dialog", Font.PLAIN, GameConfiguration.INVENTORY_TITLEFONTSIZE));
        graphics.setColor(Color.BLACK);
        graphics.drawString("INVENTORY", inventoryPosition.getX() + GameConfiguration.INVENTORY_WIDTH/4, inventoryPosition.getY() + GameConfiguration.INVENTORY_HEIGHT/7);
        
        String itemFilePath = "";
        String name = "";
        int slotX = inventoryPosition.getX() + 20;
        int slotY = inventoryPosition.getY() + GameConfiguration.INVENTORY_WIDTH/4;
        for(Slot slot : inventory.getSlots()) {
            Item item = slot.getItem();
            if (item instanceof Sword) {
                itemFilePath = "./src/ressources/sword.png";
                name = "Sword";
            }

            // partie slot et visuel de l'item
            graphics.setColor(Color.WHITE);
            graphics.fillRect(slotX, slotY, GameConfiguration.SLOT_DIMENSION, GameConfiguration.SLOT_DIMENSION);
            graphics.drawImage(Utility.readImage(itemFilePath), slotX, slotY, null);

            // partie nom de l'item
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Dialog", Font.PLAIN, 10));
            graphics.drawString(name, slotX + GameConfiguration.ITEM_NAMETAG_XSHIFT, slotY + GameConfiguration.ITEM_NAMETAG_YSHIFT);

            slotX += GameConfiguration.SLOT_DIMENSION + 20;
        }

    }
}
