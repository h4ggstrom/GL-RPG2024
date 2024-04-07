package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.entities.characters.Player;
import engine.entities.items.Inventory;
import engine.entities.items.Item;
import engine.entities.items.Slot;

public class InventoryGUI extends JFrame {

    private Inventory inventory = Player.getInstance().getInventory();
    private JPanel inventoryPanel = new JPanel();
    private JPanel playerViewPanel = new JPanel();
    private JPanel playerStatisticsPanel = new JPanel();
    
    public InventoryGUI() {
        // On veut séparer notre première fenêtre en deux parties, gauche et droite
        this.getContentPane().setLayout(new GridLayout(1, 2));
        // À gauche on ajoute la vue du joueur
        this.add(playerViewPanel);
        initPlayerViewPanel();
        // À droite on ajoute un autre Panel
        JPanel panelDroite = new JPanel();
        this.add(panelDroite);
        // Que l'on va aussi découper en 2 parties, haute et basse
        panelDroite.setLayout(new GridLayout(2, 1));
        // En haut on met la partie inventaire
        panelDroite.add(inventoryPanel);
        initInventoryPanel();
        // En bas on met les statistiques du joueur
        panelDroite.add(playerStatisticsPanel);
        initPlayerStatisticsPanel();

        pack();
        setLocationRelativeTo(null);
        setTitle("Inventory");
		setVisible(true);
    }

    public void initInventoryPanel() {
        // La vue de l'inventaire sera comme une ligne avec une colonne par item
        inventoryPanel.setLayout(new GridLayout(1, 7));
        for(Slot slot : inventory.getSlots()) {
            Item item = slot.getItem();
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            String itemFilePath = "src/ressources/" +  item.getEntityType() + ".png";
            JLabel nameLabel = new JLabel(item.getEntityType(), JLabel.CENTER);
            ImageIcon itemIcon = new ImageIcon(itemFilePath);
            JLabel itemIconLabel = new JLabel(itemIcon, JLabel.CENTER);

            itemPanel.add(itemIconLabel, BorderLayout.CENTER);
            itemPanel.add(nameLabel, BorderLayout.PAGE_START);

            JButton useButton = new JButton("Use");
            itemPanel.add(useButton, BorderLayout.PAGE_END);

            inventoryPanel.add(itemPanel);
        }
    }

    public void initPlayerViewPanel() {
        playerViewPanel.setLayout(new GridLayout(1, 1));
        String playerFilePath = "src/ressources/mainCharacter.png";
        ImageIcon playerIcon = new ImageIcon(playerFilePath);
        JLabel iconLabel = new JLabel(playerIcon, JLabel.CENTER);
        playerViewPanel.add(iconLabel);
    }

    public void initPlayerStatisticsPanel() {

    }

    class Return implements ActionListener {
		public void actionPerformed(ActionEvent e){
            InventoryGUI.this.setVisible(false);
		}
	}
}
