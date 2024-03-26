package gui;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import config.GameConfiguration;
import engine.characters.Player;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.process.EntityManager;
import engine.process.GameBuilder;
import log.Gamelog;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Classe principale de la GUI. gère l'affichage du jeu dans son entiereté
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class MainGUI extends JFrame implements Runnable {

    // définition des attributs    
    private static final long serialVersionUID = 1L; // définit un numéro de version pour la serialisation

    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT); // définit la taille de la fenêtre

    private Room room; // la salle dans laquelle évolue le joueur

    private GameDisplay dashboard; // ATH à intégrer dans la fenêtre

    private EntityManager manager; // processus de gestion des actions

    private Logger logger = Gamelog.getLogger(); // récupération du logger

    public MainGUI (String title){
        super(title);
        init();
    }

    public void init() {

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        KeyControls keyControls = new KeyControls();
        addKeyListener(keyControls); // On ajoute le KeyListener à notre JFrame
        MouseControls mouseControls = new MouseControls();
        addMouseListener(mouseControls);

        room = GameBuilder.buildRoom();
        manager = GameBuilder.buildInitCharacters(room);
        dashboard = new GameDisplay(room, manager);

        dashboard.setPreferredSize(preferredSize);
        contentPane.add(dashboard, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
        setLocationRelativeTo(null);

        new Thread(this).start();
    }

    public void run () {
        int compteur = 0;
        while (true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				logger.fatal("game crashed");
			}

            compteur++;

            if(room.getExited()) {
                manager.nextRoom();
            }
            
            if(compteur%100 == 0){
               manager.moveEnemies();
            }

            if(compteur%1000 == 0){
                manager.attackforEnemy();
             }
			dashboard.repaint();
            if(Player.getInstance().getHealth() <= 0){
                manager.gameOver();
            }
		}
    }

    /*
     * Inner-class permettant d'écouter les touches
     */
    private class KeyControls implements KeyListener {
        
        @Override
        public void keyPressed(KeyEvent event){
            char keyChar = event.getKeyChar(); // On récupère le caractère associé à la touche enfoncée
            switch (keyChar) {
                case 'z':
                    manager.moveCharacter(Player.getInstance(),"up");
                    break;
                case 'q':
                    manager.moveCharacter(Player.getInstance(),"left");
                    break;
                case 's':
                    manager.moveCharacter(Player.getInstance(),"down");
                    break;
                case 'd':
                    manager.moveCharacter(Player.getInstance(),"right");
                    break;
                case 'e':
                    this.toggleInventory();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent event){
            
        }

        @Override
        public void keyReleased(KeyEvent event){
            
        }

        // Méthode pour afficher/cacher l'inventaire
        private void toggleInventory() {
            Player.getInstance().getInventory().switchVisible();
        }
    }

    /*
     * Inner-class permettant d'écouter les clics-souris
     */
    private class MouseControls implements MouseListener {
  
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            
            // On récupère les coordonnées du clic en corrigeant l'incertitude du MouseListener
            int x = e.getX() + GameConfiguration.CORRECTCLICK_XSHIFT;
            int y = e.getY() + GameConfiguration.CORRECTCLICK_YSHIFT;
            logger.trace("mouse clicked at coords" + x + " ; " + y);

            Position click = new Position(x, y);

            manager.interact(click);
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            
        }
        
    }

}
