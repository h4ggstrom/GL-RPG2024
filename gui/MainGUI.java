package gui;

import config.GameConfiguration;
import engine.dungeon.Room;
import engine.process.CharacterManager;
import engine.process.GameBuilder;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    @Override
    public void run(){
        while (true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

            manager.nextRound();
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

}
