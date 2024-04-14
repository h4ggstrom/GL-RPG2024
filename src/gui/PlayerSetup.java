package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

import config.GameConfiguration;
import engine.entities.characters.Player;

import org.apache.log4j.Logger;

import log.Gamelog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * Génie Logiciel - Projet RPG.
 *
 * Cette classe gère la sélection de classe du joueur au début d'une nouvelle partie, avant de lancer cette dernière.
 *
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 */
public class PlayerSetup extends JFrame{

    private Player player = Player.getInstance();

    public PlayerSetup() {
        new ClassSelection();
    }

    private class ClassSelection extends JFrame {

        /* definition des variables, ici ce sera majoritairement des éléments d'affichage */
        private JButton heavyButton;
        private JButton fastButton;
        private JButton sorcererButton;
        private JButton cancelButton;
        private JButton validateButton;

        private ChartPanel chart;
        
        private int[] values = {0, 0, 0, 0}; // valeurs par défaut, elles seront modifiées lors de la séléction d'un élément
        private DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        private static Logger logger = Gamelog.getLogger();

        private ImageIcon heavyIcon = new ImageIcon("src/ressources/costaud.png");
        private ImageIcon sorcererIcon = new ImageIcon("src/ressources/sorcier.png");
        private ImageIcon fastIcon = new ImageIcon("src/ressources/rapide.png");

        private JLabel playerDisplay = new JLabel(heavyIcon);

        private JPanel ClassButtonsPanel;
        private JPanel ControlButtonsPanel;
        private JPanel finalButtonPanel;
        private JPanel statsPanel;
        
        public ClassSelection() {

            super("Sélectionnez votre classe");

            heavyButton = new JButton("Costaud");
            fastButton = new JButton("rapide");
            sorcererButton = new JButton("sorcier");
            cancelButton = new JButton("annuler");
            validateButton = new JButton("valider");

            chart = createChart();

            /* on désactive la validation tant que le joueur n'a pas choisi de classe */
            validateButton.setEnabled(false);

            /* on crée un panel pour gérer l'affichage des boutons */
            this.finalButtonPanel = new JPanel(new GridLayout(2,1));
            this.ClassButtonsPanel = new JPanel(new GridLayout(1,0));
            this.ControlButtonsPanel = new JPanel(new GridLayout(1, 0));
            this.ClassButtonsPanel.add(this.heavyButton);
            this.ClassButtonsPanel.add(this.fastButton);
            this.ClassButtonsPanel.add(this.sorcererButton);
            this.ControlButtonsPanel.add(this.cancelButton);
            this.ControlButtonsPanel.add(this.validateButton);
            this.finalButtonPanel.add(this.ClassButtonsPanel);
            this.finalButtonPanel.add(this.ControlButtonsPanel);

            /* on crée un panel pour l'affichage des stats et de l'avatar */
            this.statsPanel = new JPanel(new GridLayout(1, 2));
            this.statsPanel.add(playerDisplay);
            this.statsPanel.add(chart);

            /* on ajoute le tout au main panel */
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(statsPanel, BorderLayout.CENTER);
            getContentPane().add(finalButtonPanel, BorderLayout.SOUTH);

            // Set the window settings, in order to be visible.
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setMinimumSize(new Dimension(720,480));
            setSize(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
            setLocationRelativeTo(null);
            setVisible(true);

            heavyButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    updateClass("heavy");
                };
            });

            fastButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    updateClass("fast");
                };
            });

            sorcererButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    updateClass("sorcerer");
                };
            });

            cancelButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                    new MainMenuGUI();
                };
            });

            validateButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    logger.info("class selected : " + player.getPlayerClass());
                    logger.info("game is starting");
                    MainGUI gameMainGUI = new MainGUI("RPG");
        
                    Thread gameThread = new Thread(gameMainGUI);
                    gameThread.start();
                    dispose();
                };
            });
        }

        private ChartPanel createChart() {
            this.dataset.setValue(this.values[0],"series","Points de vie");
            this.dataset.setValue(this.values[1],"series","Vitesse de déplacement");
            this.dataset.setValue(this.values[2],"series","Vitesse d'attaque");
            this.dataset.addValue(this.values[3],"series","Mana");
            JFreeChart chartPanel = ChartFactory.createBarChart("Aptitudes","","",dataset,PlotOrientation.HORIZONTAL,false,false,false);
            return new ChartPanel(chartPanel);
        }

        private void updateClass(String className) {
            player.setPlayerClass(className);
            values[0] = player.getHealth();
            values[1] = player.getMoveSpeed();
            values[2] = player.getAttackSpeed()/10;
            values[3] = player.getMana();
            chart = createChart();
            validateButton.setEnabled(true);
    
            switch(className) {
                case "heavy":
                    playerDisplay.setIcon(heavyIcon);
                    break;
                case "fast":
                    playerDisplay.setIcon(fastIcon);
                    break;
                case "sorcerer":
                    playerDisplay.setIcon(sorcererIcon);
                    break;
            }
        }
    }
}

