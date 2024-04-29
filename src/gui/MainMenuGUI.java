package gui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import config.GameConfiguration;
import engine.entities.characters.Player;

public class MainMenuGUI extends JFrame {

    private JButton btnNewGame = new JButton("Nouvelle partie");
    private JButton btnLoadGame = new JButton("Charger une partie existante");
    private JButton btnQuitter = new JButton("Quitter");
    private JPanel buttonPanel = new JPanel();

    public MainMenuGUI() {
        initLayout();
        initActions();
    }

	private void initLayout() {


        getContentPane().setLayout(new BorderLayout());

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(
            Math.max(btnNewGame.getPreferredSize().width, Math.max(btnLoadGame.getPreferredSize().width, btnQuitter.getPreferredSize().width)),
            Math.max(btnNewGame.getPreferredSize().height, Math.max(btnLoadGame.getPreferredSize().height, btnQuitter.getPreferredSize().height))
        );

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

     
        getContentPane().add(southPanel, BorderLayout.SOUTH);

		setTitle("Menu Principal");
        setSize(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
		setVisible(true);
	}

    private void initActions() {
		btnNewGame.addActionListener(new NewGame());

        btnLoadGame.addActionListener(new LoadGame());

        btnQuitter.addActionListener(new Quitter());
	}

    class NewGame implements ActionListener {
		public void actionPerformed(ActionEvent e){
			new PlayerSetup();
            dispose();
		}
	}

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

    class Quitter implements ActionListener {
		public void actionPerformed(ActionEvent e){
            System.exit(0);
		}
	}
}
