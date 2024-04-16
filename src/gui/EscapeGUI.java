package gui;

import javax.swing.*;

import engine.entities.characters.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EscapeGUI extends JFrame {
    public EscapeGUI() {
        // Configuration initiale de la fenêtre
        setTitle("Panique à Cergy-Préfecture");
        setSize(400, 200); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer l'application à la fermeture de la fenêtre

        // Création du panneau principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Ajout d'un label pour le titre du jeu
        JLabel titleLabel = new JLabel("Panique à Cergy-Préfecture");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Création des boutons
        JButton saveButton = new JButton("Sauvegarder la partie");
        JButton quitButton = new JButton("Quitter");
        
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ajout des actions aux boutons
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // On sauvegarde l'instance du joueur (sa position dans le donjon et son équipement pour résumer)
                Player player = Player.getInstance();
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/save/save.dat"))) {
                    oos.writeObject(player);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quitte l'application
            }
        });

        // Ajout des composants au panel
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(saveButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(quitButton);

        // Ajouter le panel à la fenêtre
        add(panel);

        // Rendre la fenêtre visible
        setVisible(true);
    }
}
