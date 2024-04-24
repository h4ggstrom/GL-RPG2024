package gui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import engine.entities.Entity;
import engine.process.management.EntityManager;
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
    private static EntityManager manager = EntityManager.getInstance();
    
    // définition des attributs
    private PaintStrategy paintStrategy = new PaintStrategy();

    public GameDisplay () {
        logger.trace("New instance of GameDisplay");
    }
    
    @Override
    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        paintStrategy.paint(manager.getCurrentRoom(), graphics);

        ArrayList<Entity> entitiesToDraw = manager.getCurrentRoom().getStaticEntities();
        for (Entity entity : entitiesToDraw) {
            paintStrategy.paint(entity, graphics);
        }

        paintStrategy.paintLevelInfo(graphics);
        paintStrategy.paintPlayerInfo(graphics);
    }
   
}
