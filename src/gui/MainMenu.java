package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import config.GameConfiguration;

public class MainMenu extends JFrame {

    private JButton btnNewGame = new JButton("nouvelle partie");
    private JButton btnLoadGame = new JButton("Charger une partie existante");
    private JButton btnSettings = new JButton("Accéder aux paramètres");
    private JPanel buttonPanel = new JPanel();

    public MainMenu() {
        initLayout();
        initActions();
    }

	protected void initLayout() {

        buttonPanel.add(btnNewGame);
        buttonPanel.add(btnLoadGame);
        buttonPanel.add(btnSettings);

        add(buttonPanel); 

		setTitle("Fenêtre Principal");
        setSize(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
		setVisible(true);
	}

    protected void initActions() {
		btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainGUI mainGUI = new MainGUI("Le Donjon de Cergy-Préfecture");
                mainGUI.init();

                MainMenu.this.setVisible(false);
            }
        });

        btnLoadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}
