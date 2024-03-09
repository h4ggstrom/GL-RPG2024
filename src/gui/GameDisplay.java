package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import engine.Entity;
import engine.characters.Enemy;
import engine.characters.Player;
import engine.dungeon.Room;
import engine.items.Item;
import engine.process.EntityManager;
import log.Gamelog;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe gère l'affichage des éléments de la fenêtre de jeu.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class GameDisplay extends JPanel {

    private static Logger logger = Gamelog.getLogger();
    
    // définition des attributs
    private Room room; //la salle dans laquelle évolue le joueur
    private EntityManager manager; // le processus de gestion des actions
    private PaintStrategy paintStrategy = new PaintStrategy();

    public GameDisplay (Room room, EntityManager manager) {
        this.room = room;
        this.manager = manager;
        logger.trace("New instance of GameDisplay");
    }
    
    @Override
    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        paintStrategy.paint(room, graphics);

        paintStrategy.paint(Player.getInstance(), graphics);

        for (Entity entity : manager.getRoom().getEntities()) {
            if(entity instanceof Enemy) {
                paintStrategy.paint((Enemy)entity, graphics);
            }
            else if (entity instanceof Item) {
                paintStrategy.paint((Item)entity, graphics);
            }
        }

        if(Player.getInstance().getInventory().isVisible()) {
            paintStrategy.paint(Player.getInstance().getInventory(), graphics);
        }
    }
   
}
