package gui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import engine.entities.Entity;
import engine.entities.characters.Enemy;
import engine.entities.characters.Player;
import engine.entities.items.Item;
import engine.process.EntityManager;
import engine.entities.Coin;
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
    private EntityManager manager; // le processus de gestion des actions
    private PaintStrategy paintStrategy = new PaintStrategy();

    public GameDisplay (EntityManager manager) {
        this.manager = manager;
        logger.trace("New instance of GameDisplay");
    }
    
    @Override
    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        paintStrategy.paint(manager.getRoom(), graphics);

        ArrayList<Entity> entitiesToDraw = manager.getRoom().getEntitiesToDraw();

        for (Entity entity : entitiesToDraw) {
            if(entity instanceof Enemy) {
                paintStrategy.paint((Enemy)entity, graphics);
            }
            else if(entity instanceof Player) {
                paintStrategy.paint((Player)entity, graphics);
            }
            else if (entity instanceof Item) {
                paintStrategy.paint((Item)entity, graphics);
            }
            else if (entity instanceof Coin) {
                paintStrategy.paint((Coin)entity, graphics);
            }
        }
    }
   
}
