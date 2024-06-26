package gui;

import javax.swing.*;

import config.GameConfiguration;
import engine.entities.characters.Player;
import engine.process.management.EntityManager;

import java.awt.*;

/**
 * Fenêtre permettant au joueur d'améliorer son personnage.
 */
public class LevelUpGUI extends JFrame {

    private Player player = Player.getInstance();
    private EntityManager manager = EntityManager.getInstance();
    
    /**
     * Constructeur par défaut. Crée une nouvelle instance de LevelUpGUI, qui permet au joueur d'améliorer son personnage.
     */
    public LevelUpGUI() {
        setTitle("Amélioration de personnage");
        setSize(400, 300); // Taille de la fenêtre
        setLocation(GameConfiguration.WINDOW_WIDTH, 0);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer seulement cette fenêtre à la fermeture
        setLayout(new GridLayout(0, 1)); // Grille avec une colonne et plusieurs lignes
        
        // Ajout des boutons pour chaque amélioration
        addUpgradeOption("Améliorer PV max", () -> player.addMaxHealth(20));
        addUpgradeOption("Améliorer armure", () -> player.addArmor(10));
        addUpgradeOption("Améliorer vitesse d'attaque", () -> player.addAttackSpeed(-50));
        addUpgradeOption("Améliorer portée d'attaque", () -> player.addAttackRange(10));
        addUpgradeOption("Améliorer vitesse de déplacement", () -> player.addMoveSpeed(3));
        addUpgradeOption("Améliorer dégâts d'attaque", () -> player.addAttackDamage(5));
        addUpgradeOption("Réduire délai de récupération d'abilité", () -> player.addAbilityCooldown(-200));
        addUpgradeOption("Réduire temps d'immobilisation", () -> player.addStunCooldown(-100));

        setVisible(true); // Rendre la fenêtre visible
    }

    /**
     * Ajoute un bouton pour améliorer une caractéristique du joueur.
     * @param upgradeName Nom de l'amélioration
     * @param action Action à effectuer lors de l'amélioration
     */
    private void addUpgradeOption(String upgradeName, Runnable action) {
        JButton button = new JButton(upgradeName);
        button.addActionListener(e -> {
            action.run();
            // On rafraîchit la fenêtre d'inventaire si elle est ouverte
            manager.refreshContainers();
            // On ferme la fenêtre
            dispose();
        });
        add(button);
    }
}
