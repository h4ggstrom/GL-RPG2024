package gui;

import javax.swing.*;

import config.GameConfiguration;
import engine.entities.characters.Player;
import engine.process.EntityManager;

import java.awt.*;

public class LevelUpGUI extends JFrame {

    private Player player = Player.getInstance();
    
    public LevelUpGUI() {
        setTitle("Amélioration de personnage");
        setSize(400, 300); // Taille de la fenêtre
        setLocation(GameConfiguration.WINDOW_WIDTH, 0);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer seulement cette fenêtre à la fermeture
        setLayout(new GridLayout(0, 1)); // Grille avec une colonne et plusieurs lignes
        
        // Ajout des boutons pour chaque amélioration
        addUpgradeOption("Améliorer PV max", () -> player.setMaxHealth(player.getMaxHealth() + 10));
        addUpgradeOption("Améliorer armure", () -> player.setArmor(player.getArmor() + 1));
        addUpgradeOption("Améliorer vitesse d'attaque", () -> player.setAttackSpeed(player.getAttackSpeed() - 10));
        addUpgradeOption("Améliorer portée d'attaque", () -> player.setAttackRange(player.getAttackRange() + 10));
        addUpgradeOption("Améliorer vitesse de déplacement", () -> player.setMoveSpeed(player.getMoveSpeed() + 2));
        addUpgradeOption("Améliorer dégâts d'attaque", () -> player.setAttackDamage(player.getAttackDamage() + 5));
        addUpgradeOption("Réduire délai de récupération d'abilité", () -> player.setAbilityCooldown(player.getAbilityCooldown() - 10));
        addUpgradeOption("Réduire temps d'immobilisation", () -> player.setStunCooldown(player.getStunCooldown() - 10));

        setVisible(true); // Rendre la fenêtre visible
    }

    private void addUpgradeOption(String upgradeName, Runnable action) {
        JButton button = new JButton(upgradeName);
        button.addActionListener(e -> {
            action.run();
            // On rafraîchit la fenêtre d'inventaire si elle est ouverte
            EntityManager.refreshContainers();
            // On ferme la fenêtre
            dispose();
        });
        add(button);
    }
}
