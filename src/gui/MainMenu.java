package gui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Component;
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


        getContentPane().setLayout(new BorderLayout());

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(
            Math.max(btnNewGame.getPreferredSize().width, Math.max(btnLoadGame.getPreferredSize().width, btnSettings.getPreferredSize().width)),
            Math.max(btnNewGame.getPreferredSize().height, Math.max(btnLoadGame.getPreferredSize().height, btnSettings.getPreferredSize().height))
        );

        btnNewGame.setMaximumSize(buttonSize);
        btnLoadGame.setMaximumSize(buttonSize);
        btnSettings.setMaximumSize(buttonSize);

        buttonPanel.add(btnNewGame);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnLoadGame);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnSettings);

        btnNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLoadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);

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
