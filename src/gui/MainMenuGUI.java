package gui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import config.GameConfiguration;
import engine.entities.characters.Player;
import engine.process.management.RessourceManager;

/**
 * Génie Logiciel - Projet RPG.
 *
 * Cette classe gère l'affichage du menu principal du jeu.
 *
 */
public class MainMenuGUI extends JFrame {

    private ImagePanel imagePanel;
    private JButton btnNewGame = new JButton("Nouvelle partie");
    private JButton btnLoadGame = new JButton("Charger une partie existante");
    private JButton btnQuitter = new JButton("Quitter");
    private JPanel buttonPanel = new JPanel();

    /**
     * Constructeur par défaut. Crée une nouvelle instance de MainMenuGUI, qui permet au joueur de choisir de commencer une nouvelle partie, de charger une partie existante ou de quitter le jeu.
     */
    public MainMenuGUI() {
        initLayout();
        initActions();
    }

    /**
     * Initialise le layout de la fenêtre.
     */
	private void initLayout() {


        getContentPane().setLayout(new BorderLayout());

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(
            Math.max(btnNewGame.getPreferredSize().width, Math.max(btnLoadGame.getPreferredSize().width, btnQuitter.getPreferredSize().width)),
            Math.max(btnNewGame.getPreferredSize().height, Math.max(btnLoadGame.getPreferredSize().height, btnQuitter.getPreferredSize().height))
        );

        // Créez un ImagePanel avec votre image de fond
        try {
            imagePanel = new ImagePanel("./src/ressources/title.png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        btnNewGame.setMaximumSize(buttonSize);
        btnLoadGame.setMaximumSize(buttonSize);
        btnQuitter.setMaximumSize(buttonSize);

        buttonPanel.add(btnNewGame);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnLoadGame);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnQuitter);

        btnNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLoadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuitter.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.add(Box.createVerticalGlue());
        southPanel.add(buttonPanel);
        southPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(southPanel, BorderLayout.SOUTH);

		setTitle("Menu Principal");
        setSize(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(imagePanel);
        setLocationRelativeTo(null);
		setVisible(true);
	}

    /**
     * Initialise les actions des boutons.
     */
    private void initActions() {
		btnNewGame.addActionListener(new NewGame());

        btnLoadGame.addActionListener(new LoadGame());

        btnQuitter.addActionListener(new Quitter());
	}

    /**
     * Classe interne qui gère l'action de commencer une nouvelle partie.
     */
    class NewGame implements ActionListener {
		public void actionPerformed(ActionEvent e){
			new PlayerSetup();
            dispose();
		}
	}

    /**
     * Classe interne qui gère l'action de charger une partie existante.
     */
    class LoadGame implements ActionListener {
		public void actionPerformed(ActionEvent e){
            // On récupère l'instance de joueur
            Player player = Player.getInstance();
            // On charge le fichier
            player.loadSave();
            MainGUI gameMainGUI = new MainGUI(GameConfiguration.GAME_TITLE);
        
            Thread gameThread = new Thread(gameMainGUI);
            gameThread.start();
            dispose();
		}
	}

    /**
     * Classe interne qui gère l'action de quitter le jeu.
     */
    class Quitter implements ActionListener {
		public void actionPerformed(ActionEvent e){
            System.exit(0);
		}
	}

    /**
     * Classe interne qui gère l'affichage d'une image en arrière-plan.
     */
    class ImagePanel extends JPanel {
        private BufferedImage backgroundImage;

        public ImagePanel(String imagePath) throws IOException {
            backgroundImage = RessourceManager.getInstance().getImage(imagePath);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
}
