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
import engine.Abilities.Ability;
import engine.dungeon.Position;
import engine.dungeon.Room;
import engine.process.CharacterManager;
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

    private CharacterManager manager; // processus de gestion des actions

    private Logger logger = Gamelog.getLogger(); // récupération du logger

    public MainGUI (String title){
        super(title);
        init();
    }

    private void init() {

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
    }

    public void run () {
        while (true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				logger.fatal("game crashed");
			}

            if(room.getExited()) {
                manager.nextRoom();
            }
            
			dashboard.repaint();
		}
    }

    /*
     * Inner-class permettant d'écouter les touches
     */
    private class KeyControls implements KeyListener {
        
        @Override
        public void keyPressed(KeyEvent event){
            char keyChar = event.getKeyChar(); // On récupère le charactère associé à la touche enfoncée
            switch (keyChar) {
                case 'z':
                    manager.movePlayer("up");
                    break;
                case 'q':
                    manager.movePlayer("left");
                    break;
                case 's':
                    manager.movePlayer("down");
                    break;
                case 'd':
                    manager.movePlayer("right");
                    break;
                default:
                    manager.emptyAbilities();
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent event){
            
        }

        @Override
        public void keyReleased(KeyEvent event){
            
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

            // On récupère les coordonnées du centre de la hitbox du joueur
            Position playerCenter = manager.getPlayer().getHitbox().getCenter();
            Position click = new Position(x, y);

            // On récupère la distance entre ce pixel et notre centre
            int distance = manager.calculateDistance(playerCenter, click);
            logger.trace("distance to click = " + distance); 

            Ability ability = new Ability(manager.getPlayer(), new Position(x, y));
            manager.attack(distance, ability);
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
