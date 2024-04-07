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
import engine.entities.items.weapons.Weapon;
import engine.process.EntityManager;

public class InventoryGUI extends JFrame {

    private EntityManager manager;
    private Player player = Player.getInstance();
    private Inventory inventory = player.getInventory();
    private JPanel inventoryPanel = new JPanel();
    private JPanel playerViewPanel = new JPanel();
    private JPanel equipedItems = new JPanel();
    private JPanel playerStatisticsPanel = new JPanel();
    
    public InventoryGUI(EntityManager manager) {
        this.manager = manager;
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
        ArrayList<Slot> slots = inventory.getSlots();
        Slot slot1 = slots.get(0);
        Slot slot2 = slots.get(1);
        Slot slot3 = slots.get(2);
        Slot slot4 = slots.get(3);
        Slot slot5 = slots.get(4);
        Slot slot6 = slots.get(5);
        Slot slot7 = slots.get(6);
        Item item1 = slot1.getItem();
        Item item2 = slot2.getItem();
        Item item3 = slot3.getItem();
        Item item4 = slot4.getItem();
        Item item5 = slot5.getItem();
        Item item6 = slot6.getItem();
        Item item7 = slot7.getItem();
        JPanel item1Panel = new JPanel();
        JPanel item2Panel = new JPanel();
        JPanel item3Panel = new JPanel();
        JPanel item4Panel = new JPanel();
        JPanel item5Panel = new JPanel();
        JPanel item6Panel = new JPanel();
        JPanel item7Panel = new JPanel();
        initItemSlot(inventoryPanel, item1Panel, item1, (item1 == null) ? "" : item1.getEntityType());
        initItemSlot(inventoryPanel, item2Panel, item2, (item2 == null) ? "" : item2.getEntityType());
        initItemSlot(inventoryPanel, item3Panel, item3, (item3 == null) ? "" : item3.getEntityType());
        initItemSlot(inventoryPanel, item4Panel, item4, (item4 == null) ? "" : item4.getEntityType());
        initItemSlot(inventoryPanel, item5Panel, item5, (item5 == null) ? "" : item5.getEntityType());
        initItemSlot(inventoryPanel, item6Panel, item6, (item6 == null) ? "" : item6.getEntityType());
        initItemSlot(inventoryPanel, item7Panel, item7, (item7 == null) ? "" : item7.getEntityType());
        JButton item1Button = new JButton("Use");
        JButton item2Button = new JButton("Use");
        JButton item3Button = new JButton("Use");
        JButton item4Button = new JButton("Use");
        JButton item5Button = new JButton("Use");
        JButton item6Button = new JButton("Use");
        JButton item7Button = new JButton("Use");
        item1Panel.add(item1Button, BorderLayout.PAGE_END);
        item2Panel.add(item2Button, BorderLayout.PAGE_END);
        item3Panel.add(item3Button, BorderLayout.PAGE_END);
        item4Panel.add(item4Button, BorderLayout.PAGE_END);
        item5Panel.add(item5Button, BorderLayout.PAGE_END);
        item6Panel.add(item6Button, BorderLayout.PAGE_END);
        item7Panel.add(item7Button, BorderLayout.PAGE_END);
        item1Button.addActionListener(new ActionItem1());
    }

    class ActionItem1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            manager.inventorySlotUsed(0);
            
        }
    }

    class ActionItem2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            manager.inventorySlotUsed(1);
        }
    }

    class ActionItem3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            manager.inventorySlotUsed(2);
        }
    }

    class ActionItem4 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            manager.inventorySlotUsed(3);
        }
    }

    class ActionItem5 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            manager.inventorySlotUsed(4);
        }
    }

    class ActionItem6 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            manager.inventorySlotUsed(5);
        }
    }

    class ActionItem7 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            manager.inventorySlotUsed(6);
        }
    }

    public void initPlayerViewPanel() {
        playerViewPanel.setLayout(new GridLayout(1, 2));

        playerViewPanel.add(equipedItems);
        initEquipedItems();

        String playerFilePath = "src/ressources/mainCharacter.png";
        ImageIcon playerIcon = new ImageIcon(playerFilePath);
        JLabel iconLabel = new JLabel(playerIcon, JLabel.CENTER);
        playerViewPanel.add(iconLabel);
    }

    public void initEquipedItems() {
        // Une ligne pour le JLabel puis des lignes pour les items équipés
        equipedItems.setLayout(new GridLayout(7, 1));
        JLabel equipedItemsLabel = new JLabel("Items équipés", JLabel.CENTER);
        equipedItems.add(equipedItemsLabel);

        Weapon weapon = (Weapon)player.getWeaponSlot().getItem();
        JPanel weaponPanel = new JPanel();
        JPanel helmetPanel = new JPanel();
        JPanel chestplatePanel = new JPanel();
        JPanel glovesPanel = new JPanel();
        JPanel pantsPanel = new JPanel();
        JPanel bootsPanel = new JPanel();
        initItemSlot(equipedItems, weaponPanel, weapon, "Arme");
        initItemSlot(equipedItems, helmetPanel, null, "Casque");
        initItemSlot(equipedItems, chestplatePanel, null, "Plastron");
        initItemSlot(equipedItems, glovesPanel, null, "Gants");
        initItemSlot(equipedItems, pantsPanel, null, "Jambières");
        initItemSlot(equipedItems, bootsPanel, null, "Bottes");
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
        playerStatisticsPanel.setLayout(new GridLayout(4, 1));
        JTextField healthTextField = new JTextField();
        JTextField armorTextField = new JTextField();
        JTextField attackSpeedTextField = new JTextField();
        JTextField moveSpeedTextField = new JTextField();

        healthTextField.setEditable(false);
        armorTextField.setEditable(false);
        attackSpeedTextField.setEditable(false);
        moveSpeedTextField.setEditable(false);

        healthTextField.setText("Nombre de points de vie actuels : " + player.getHealth());
        armorTextField.setText("Nombre de points d'armure : " + player.getArmor());
        attackSpeedTextField.setText("Vitesse d'attaque : " + player.getAttackSpeed());
        moveSpeedTextField.setText("Vitesse de déplacement : " + player.getMoveSpeed());

        playerStatisticsPanel.add(healthTextField);
        playerStatisticsPanel.add(armorTextField);
        playerStatisticsPanel.add(attackSpeedTextField);
        playerStatisticsPanel.add(moveSpeedTextField);
    }

    class Return implements ActionListener {
		public void actionPerformed(ActionEvent e){
            InventoryGUI.this.setVisible(false);
		}
	}
}
