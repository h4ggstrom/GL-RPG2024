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
import engine.dungeon.Dungeon;
import engine.dungeon.Position;
import engine.entities.characters.Player;
import engine.process.management.EntityManager;
import engine.process.management.GameBuilder;
import gui.containersGUI.InventoryGUI;
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

    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT); // définit la taille de la fenêtre

    private Dungeon dungeon; // le donjon dans lequel le joueur évolue

    private GameDisplay dashboard; // ATH à intégrer dans la fenêtre

    private EntityManager manager = EntityManager.getInstance(); // processus de gestion des actions

    private boolean pause;

    private MainGUI instance = this;

    private Logger logger = Gamelog.getLogger(); // récupération du logger

    /**
     * Constructeur par défaut. Crée une nouvelle instance de MainGUI
     * 
     * @param title le titre de la fenêtre
     */
    public MainGUI (String title){
        super(title);
        init();
    }
    
    /**
     * Méthode d'initialisation de la fenêtre
     */
    public void init() {

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        KeyControls keyControls = new KeyControls();
        addKeyListener(keyControls); // On ajoute le KeyListener à notre JFrame
        MouseControls mouseControls = new MouseControls();
        addMouseListener(mouseControls);

        dungeon = GameBuilder.buildDungeon();
        GameBuilder.buildInitEntities(dungeon);
        dashboard = new GameDisplay();

        dashboard.setPreferredSize(preferredSize);
        contentPane.add(dashboard, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
        setLocationRelativeTo(null);

    }

    /**
     * Méthode implémentant la boucle de jeu
     */
    public void run () {
        while (!pause) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				logger.fatal("game crashed");
			}

            if(Player.getInstance().getHealth() <= 0){
                manager.gameOver();
            }

            // Si le joueur se trouve dans la dernière salle
            if(Player.getInstance().getStageNumber() == 3 && Player.getInstance().getRoomNumber() == 7){
                // et que la salle est vidée du boss
                if(manager.getCurrentRoom().getEnemies().size() == 0){
                    manager.gameOver();
                }
            }

            manager.moveEnemies();
            manager.incrementPossibilities();
            manager.attackforEnemy();

            dashboard.repaint();
		}
    }

    /*
     * Inner-class permettant d'écouter les touches
     */
    private class KeyControls implements KeyListener {
        private boolean upPressed = false;
        private boolean downPressed = false;
        private boolean leftPressed = false;
        private boolean rightPressed = false;

        @Override
        public void keyPressed(KeyEvent event){
            switch (event.getKeyCode()) {
                case KeyEvent.VK_Z:
                    upPressed = true;
                    break;
                case KeyEvent.VK_Q:
                    leftPressed = true;
                    break;
                case KeyEvent.VK_S:
                    downPressed = true;
                    break;
                case KeyEvent.VK_D:
                    rightPressed = true;
                    break;
                case KeyEvent.VK_P:
                    if(!pause) {
                        pause = true;
                    }
                    else {
                        pause = false;
                        Thread game = new Thread(instance);
                        game.start();
                    }
                    break;
                case KeyEvent.VK_E:
                    new InventoryGUI();
                    break;
                case KeyEvent.VK_R:
                    manager.playerUseAbility();
                    break;
                default:
                    break;
            }

            // Si la touche échap est pressée
            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                new EscapeGUI();
            }

            updateMovement();
        }
    
        @Override
        public void keyReleased(KeyEvent event){
            switch (event.getKeyCode()) {
                case KeyEvent.VK_Z:
                    upPressed = false;
                    break;
                case KeyEvent.VK_Q:
                    leftPressed = false;
                    break;
                case KeyEvent.VK_S:
                    downPressed = false;
                    break;
                case KeyEvent.VK_D:
                    rightPressed = false;
                    break;
                    default:
                    break;
            }
            updateMovement();
        }
        
        /**
         * Méthode permettant de mettre à jour le mouvement du joueur
         */
        private void updateMovement() {
            if (upPressed && rightPressed) {
                manager.moveCharacter(Player.getInstance(), "up-right");
            } else if (upPressed && leftPressed) {
                manager.moveCharacter(Player.getInstance(), "up-left");
            } else if (downPressed && rightPressed) {
                manager.moveCharacter(Player.getInstance(), "down-right");
            } else if (downPressed && leftPressed) {
                manager.moveCharacter(Player.getInstance(), "down-left");
            } else if (upPressed) {
                manager.moveCharacter(Player.getInstance(), "up");
            } else if (downPressed) {
                manager.moveCharacter(Player.getInstance(), "down");
            } else if (leftPressed) {
                manager.moveCharacter(Player.getInstance(), "left");
            } else if (rightPressed) {
                manager.moveCharacter(Player.getInstance(), "right");
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
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
