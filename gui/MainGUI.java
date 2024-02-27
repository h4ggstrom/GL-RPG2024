package gui;

import javax.swing.JFrame;

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

public class MainGUI extends JFrame implements Runnable {
        
    private static final long serialVersionUID = 1L;

    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

    private Room room;

    private GameDisplay dashboard;

    private CharacterManager manager;

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
            int x = e.getX() + GameConfiguration.CORRECTCLICKSHIFT_X;
            int y = e.getY() + GameConfiguration.CORRECTCLICKSHIFT_Y;

            // On récupère les coordonnées du centre de la hitbox du joueur
            Position playerCenter = manager.getPlayer().getHitbox().getCenter();
            Position click = new Position(x, y);

            // On calcule la distance entre ce pixel et notre centre avec pythagore
            int distance = manager.calculateDistance(playerCenter, click);

            Ability ability = new Ability(manager.getPlayer(), GameConfiguration.WEAPON_DAMAGE, GameConfiguration.WEAPON_RANGE, new Position(x, y));
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
