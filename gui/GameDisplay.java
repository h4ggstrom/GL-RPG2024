package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import engine.characters.Enemy;
import engine.characters.Player;
import engine.dungeon.Room;
import engine.process.CharacterManager;

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
    
    // définition des attributs
    private Room room; //la salle dans laquelle évolue le joueur
    private CharacterManager manager; // le processus de gestion des actions
    private PaintStrategy paintStrategy = new PaintStrategy();

    public GameDisplay (Room room, CharacterManager manager) {
        this.room = room;
        this.manager = manager;
    }

    @Override
    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        paintStrategy.paint(room, graphics);

        Player player = manager.getPlayer();
        paintStrategy.paint(player, graphics);

        for (Enemy enemy : manager.getRoom().getEnemies()) {
            paintStrategy.paint(enemy, graphics);
        }
    }
   
}
