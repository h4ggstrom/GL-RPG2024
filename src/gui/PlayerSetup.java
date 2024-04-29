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

    /**
     * Constructeur par défaut. Crée une nouvelle instance de PlayerSetup, qui permet au joueur de choisir sa classe.
     */
    public PlayerSetup() {
        new ClassSelection();
    }

    /**
     * Classe interne qui gère l'affichage de la sélection de classe du joueur
     */
    private class ClassSelection extends JFrame {

        /* definition des variables, ici ce sera majoritairement des éléments d'affichage */
        private JButton heavyButton;
        private JButton fastButton;
        private JButton sorcererButton;
        private JButton cancelButton;
        private JButton validateButton;

        private ChartPanel chart;
        
        private int[] values = {0, 0, 0, 0, 0, 0, 0}; // valeurs par défaut, elles seront modifiées lors de la séléction d'un élément
        private DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        private static Logger logger = Gamelog.getLogger();

        private ImageIcon initIcon = new ImageIcon("src/ressources/assets/choose.png");
        private ImageIcon heavyIcon = new ImageIcon("src/ressources/assets/entity/costaud.png");
        private ImageIcon sorcererIcon = new ImageIcon("src/ressources/assets/entity/sorcier.png");
        private ImageIcon fastIcon = new ImageIcon("src/ressources/assets/entity/rapide.png");

        private JLabel playerDisplay = new JLabel(initIcon);
        private JLabel specialAbilityLabel = new JLabel();

        private JPanel playerAndSpecialAbilityPanel = new JPanel(new GridLayout(2, 1));
        private JPanel ClassButtonsPanel;
        private JPanel ControlButtonsPanel;
        private JPanel finalButtonPanel;
        private JPanel statsPanel;
        
        /**
         * Constructeur par défaut. Crée une nouvelle instance de ClassSelection, qui permet au joueur de choisir sa classe.
         */
        public ClassSelection() {

            super("Sélectionnez votre classe");

            heavyButton = new JButton(GameConfiguration.STRONG_NAME);
            fastButton = new JButton(GameConfiguration.FAST_NAME);
            sorcererButton = new JButton(GameConfiguration.SORCERER_NAME);
            cancelButton = new JButton("Annuler");
            validateButton = new JButton("Valider");

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
            specialAbilityLabel.setHorizontalAlignment(JLabel.CENTER);
            playerAndSpecialAbilityPanel.add(playerDisplay);
            playerAndSpecialAbilityPanel.add(specialAbilityLabel);
            this.statsPanel.add(playerAndSpecialAbilityPanel);
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
                    MainGUI gameMainGUI = new MainGUI(GameConfiguration.GAME_TITLE);
        
                    Thread gameThread = new Thread(gameMainGUI);
                    gameThread.start();
                    dispose();
                };
            });
        }

        /**
         * Crée un graphique à barres pour afficher les stats du joueur
         * @return ChartPanel
         */
        private ChartPanel createChart() {
            this.dataset.setValue(this.values[0],"series","Points de vie (x10)");
            this.dataset.setValue(this.values[1],"series","Vitesse de déplacement");
            this.dataset.setValue(this.values[2],"series","Vitesse d'attaque");
            this.dataset.addValue(this.values[3],"series","Dégâts d'attaque");
            this.dataset.addValue(this.values[4],"series","Mana maximum");
            this.dataset.addValue(this.values[5],"series","Armure");
            this.dataset.addValue(this.values[6],"series","Armure");
            JFreeChart chartPanel = ChartFactory.createBarChart("Aptitudes","","",dataset,PlotOrientation.HORIZONTAL,false,false,false);
            return new ChartPanel(chartPanel);
        }

        /**
         * Met à jour les valeurs des stats du joueur en fonction de la classe choisie
         * @param className
         */
        private void updateClass(String className) {
            player.setPlayerClass(className);
            values[0] = player.getMaxHealth()/10;
            values[1] = player.getMoveSpeed();
            values[2] = player.getAttackSpeed()/100; // pour l'avoir en terme de secondes
            values[3] = player.getAttackDamage();
            values[4] = player.getAbilityCooldown()/100; // pour l'avoir en terme de secondes
            values[5] = player.getArmor();
            values[6] = player.getStunCooldown()/100; // pour l'avoir en terme de secondes
            chart = createChart();
            validateButton.setEnabled(true);
    
            switch(className) {
                case "heavy":
                    playerDisplay.setIcon(heavyIcon);
                    specialAbilityLabel.setText("Compétence spéciale (R) : Invincible pendant 5 secondes");
                    break;
                case "fast":
                    playerDisplay.setIcon(fastIcon);
                    specialAbilityLabel.setText("Compétence spéciale (R) : Peut se téléporter pendant 5 secondes");
                    break;
                case "sorcerer":
                    playerDisplay.setIcon(sorcererIcon);
                    specialAbilityLabel.setText("Compétence spéciale (R) : Immobilise les ennemis");
                    break;
            }
        }
    }
}

