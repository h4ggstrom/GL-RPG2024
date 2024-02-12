package gui;

import config.GameConfiguration;
import engine.dungeon.Block;
import engine.dungeon.Room;
import engine.process.CharacterManager;
import engine.process.GameBuilder;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MainGUI extends JFrame implements Runnable {
    
    private static final long serialVersionUID = 1L;

    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

    private Room room;

    private GameDisplay dashboard;

    private CharacterManager manager;

    public MainGUI(String title){
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
        manager.generateEnemies(); // On génère une seule fois les ennemis par salle
		dashboard = new GameDisplay(room, manager);

		dashboard.setPreferredSize(preferredSize);
		contentPane.add(dashboard, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
	}

    @Override
    public void run(){
        while (true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
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
                    manager.movePlayerUp();
                    break;
                case 'q':
                    manager.movePlayerLeft();
                    break;
                case 's':
                    manager.movePlayerDown();
                    break;
                case 'd':
                    manager.movePlayerRight();
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
    }

    /*
     * Inner-class permettant d'écouter les clics-souris
     */
    private class MouseControls implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e){
            int x = e.getX(); // On récupère le pixel x cliqué
            int y = e.getY(); // On récupère le pixel y cliqué
            
            int selectedLine = y / GameConfiguration.BLOCK_SIZE - 1;
            int selectedColumn = x / GameConfiguration.BLOCK_SIZE;

            Block cible = room.getBlock(selectedLine, selectedColumn); // On récupère le block cliqué
            manager.attack(cible); // On attaque la cible
        }

        @Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
    }
}
