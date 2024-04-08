package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import engine.entities.characters.Player;
import engine.entities.items.Inventory;
import engine.entities.items.Item;
import engine.entities.items.Slot;
import engine.entities.items.weapons.*;
import engine.entities.items.armor.*;
import engine.process.EntityManager;
import engine.process.InventoryRefreshListener;

public class InventoryGUI extends JFrame implements InventoryRefreshListener {

    private EntityManager manager;
    private Player player = Player.getInstance();
    private Inventory inventory = player.getInventory();
    private JPanel inventoryPanel = new JPanel();
    private JPanel playerViewPanel = new JPanel();
    private JPanel equipedItemsPanel = new JPanel();
    private JPanel playerStatisticsPanel = new JPanel();
    
    public InventoryGUI(EntityManager manager) {
        this.manager = manager;
        this.manager.setInventoryRefreshListener(this);

        initOverallView();

        pack();
        setLocationRelativeTo(null);
        setTitle("Inventory");
		setVisible(true);
    }

    public void initOverallView() {
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
    }

    public void initInventoryPanel() {
        // La vue de l'inventaire sera comme une ligne avec une colonne par item
        inventoryPanel.setLayout(new GridLayout(1, 7));
        ArrayList<Slot> slots = inventory.getSlots();
    
        // Itérer sur chaque slot de l'inventaire
        for (int i = 0; i < slots.size(); i++) {
            final int slotNumber = i;
            Item item = slots.get(i).getItem();
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
            // Initialiser et ajouter le panel de l'item à inventoryPanel
            initItemSlot(inventoryPanel, itemPanel, item, ((item == null) ? "" : item.getEntityType()));
            inventoryPanel.add(itemPanel);
    
            // Créer et ajouter un bouton "Use" au panel de l'item
            JButton useButton = new JButton("Use");
            itemPanel.add(useButton, BorderLayout.PAGE_END);
    
            // Attacher un ActionListener au bouton, avec l'indice du slot comme paramètre
            useButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    manager.inventorySlotUsed(slotNumber);
                }
            });
        }
    }

    public void initPlayerViewPanel() {
        playerViewPanel.setLayout(new GridLayout(1, 2));

        playerViewPanel.add(equipedItemsPanel);
        initEquipedItemsPanel();

        String playerFilePath = "src/ressources/mainCharacter.png";
        ImageIcon playerIcon = new ImageIcon(playerFilePath);
        JLabel iconLabel = new JLabel(playerIcon, JLabel.CENTER);
        playerViewPanel.add(iconLabel);
    }

    public void initEquipedItemsPanel() {
        // Une ligne pour le JLabel puis des lignes pour les items équipés
        equipedItemsPanel.setLayout(new GridLayout(7, 1));
        JLabel equipedItemsPanelLabel = new JLabel("Items équipés", JLabel.CENTER);
        equipedItemsPanel.add(equipedItemsPanelLabel);

        Weapon weapon = (Weapon)player.getWeaponSlot().getItem();
        Helmet helmet = (Helmet)player.getHelmetSlot().getItem();
        Gloves gloves = (Gloves)player.getGlovesSlot().getItem();
        Chestplate chestplate = (Chestplate)player.getChestplateSlot().getItem();
        Pants pants = (Pants)player.getPantsSlot().getItem();
        Boots boots = (Boots)player.getBootsSlot().getItem();
        JPanel weaponPanel = new JPanel();
        JPanel helmetPanel = new JPanel();
        JPanel chestplatePanel = new JPanel();
        JPanel glovesPanel = new JPanel();
        JPanel pantsPanel = new JPanel();
        JPanel bootsPanel = new JPanel();
        initItemSlot(equipedItemsPanel, weaponPanel, weapon, "Arme");
        initItemSlot(equipedItemsPanel, helmetPanel, helmet, "Casque");
        initItemSlot(equipedItemsPanel, chestplatePanel, gloves, "Plastron");
        initItemSlot(equipedItemsPanel, glovesPanel, chestplate, "Gants");
        initItemSlot(equipedItemsPanel, pantsPanel, pants, "Jambières");
        initItemSlot(equipedItemsPanel, bootsPanel, boots, "Bottes");
    }

    public void initItemSlot(JPanel panel, JPanel itemPanel, Item item, String slotName) {
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel nameLabel = new JLabel(slotName, JLabel.CENTER);
        itemPanel.add(nameLabel, BorderLayout.PAGE_START);

        if(item != null) {
            String itemFilePath = "src/ressources/" +  item.getEntityType() + ".png";
            ImageIcon itemIcon = new ImageIcon(itemFilePath);
            JLabel itemIconLabel = new JLabel(itemIcon, JLabel.CENTER);

            itemPanel.add(itemIconLabel, BorderLayout.CENTER);
        }

        panel.add(itemPanel);
    }

    public void initPlayerStatisticsPanel() {
        // Une colonne et une ligne par statistique
        playerStatisticsPanel.setLayout(new GridLayout(6, 1));
        JTextField healthTextField = new JTextField();
        JTextField armorTextField = new JTextField();
        JTextField attackSpeedTextField = new JTextField();
        JTextField moveSpeedTextField = new JTextField();
        JTextField abilityCooldownTextField = new JTextField();
        JTextField stunCooldownTextField = new JTextField();

        healthTextField.setEditable(false);
        armorTextField.setEditable(false);
        attackSpeedTextField.setEditable(false);
        moveSpeedTextField.setEditable(false);
        abilityCooldownTextField.setEditable(false);
        stunCooldownTextField.setEditable(false);

        healthTextField.setText("Nombre de points de vie actuels : " + player.getHealth());
        armorTextField.setText("Nombre de points d'armure : " + player.getArmor());
        attackSpeedTextField.setText("Vitesse d'attaque : " + player.getAttackSpeed());
        moveSpeedTextField.setText("Vitesse de déplacement : " + player.getMoveSpeed());
        abilityCooldownTextField.setText("Délai de récupération des abilités : " + player.getAbilityCooldown() + "ms");
        stunCooldownTextField.setText("Réduction de la durée d'immobilisation : " + player.getStunCooldown() + "ms");

        playerStatisticsPanel.add(healthTextField);
        playerStatisticsPanel.add(armorTextField);
        playerStatisticsPanel.add(attackSpeedTextField);
        playerStatisticsPanel.add(moveSpeedTextField);
        playerStatisticsPanel.add(abilityCooldownTextField);
        playerStatisticsPanel.add(stunCooldownTextField);
    }

    /**
     * Méthode permettant de rafraîchir l'affichage des 3 panels principaux de la fenêtre
     */
    @Override
    public void refreshInventory() {
        inventoryPanel.removeAll(); // Supprime tous les composants du panel
        initInventoryPanel(); // Reconstruit le panel
        inventoryPanel.revalidate(); // Indique que le layout manager doit recalculer le layout
        inventoryPanel.repaint(); // Demande la redessin du panel

        equipedItemsPanel.removeAll();
        initEquipedItemsPanel();
        equipedItemsPanel.revalidate();
        equipedItemsPanel.repaint();

        playerStatisticsPanel.removeAll();
        initPlayerStatisticsPanel();
        playerStatisticsPanel.revalidate();
        playerStatisticsPanel.repaint();
    }

    class Return implements ActionListener {
		public void actionPerformed(ActionEvent e){
            InventoryGUI.this.setVisible(false);
		}
	}
}
