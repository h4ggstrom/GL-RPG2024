package gui.containersGUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.entities.items.Item;
import engine.entities.items.consumables.Coin;
import engine.entities.items.consumables.Consumable;
import engine.entities.items.equipment.Clothe;
import engine.entities.items.equipment.Gloves;
import engine.entities.items.equipment.Helmet;
import engine.entities.items.weapons.Weapon;
import engine.process.management.ContainerRefreshListener;
import engine.process.management.EntityManager;

public abstract class ContainerGUI extends JFrame implements ContainerRefreshListener {

    public EntityManager manager = EntityManager.getInstance();

    public ContainerGUI () {

    }

    public void initItemSlot(JPanel panel, JPanel itemPanel, Item item, String slotName) {
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        itemPanel.setSize(30, 30);
        JLabel nameLabel = new JLabel(slotName, JLabel.CENTER);
        itemPanel.add(nameLabel, BorderLayout.PAGE_START);
        String tooltipText = "Slot vide";

        if(item != null) {
            String itemFilePath = "src/ressources/assets/entity/" + item.getEntityType() + ".png";
            ImageIcon itemIcon = new ImageIcon(itemFilePath);
            JLabel itemIconLabel = new JLabel(itemIcon, JLabel.CENTER);
            itemPanel.add(itemIconLabel, BorderLayout.CENTER);
            
            // Configurer le tooltip avec des informations de l'item
            if(item instanceof Weapon) {
                Weapon weapon = (Weapon)item;
                tooltipText = "<html>" + "Nom : " + weapon.getEntityName() + "<br>" + "Dégâts : " + weapon.getAttackDamage() + "<br>" + "Portée : " + weapon.getAttackRange() + "</html>";
            }
            else if(item instanceof Clothe) {
                Clothe clothe = (Clothe)item;
                tooltipText = "<html>" + "Nom : " + clothe.getEntityName() + "<br>" + "Effet : " + clothe.getEffect() + "<br>" + "Valeur : " + clothe.getValue() + "</html>";
                if(clothe instanceof Gloves || clothe instanceof Helmet) {
                    tooltipText = "<html>" + "Nom : " + clothe.getEntityName() + "<br>" + "Effet : " + clothe.getEffect() + "<br>" + "Valeur : " + clothe.getValue()/100 + "</html>";
                }
            }
            else if(item instanceof Coin) {
                Coin coin = (Coin)item;
                tooltipText = "<html>" + "Nom : " + coin.getEntityName() + "<br>" + "Valeur : " + coin.getConsumableValue() + "</html>";
            }
            else if(item instanceof Consumable) {
                Consumable consumable = (Consumable)item;
                tooltipText = "<html>" + "Nom : " + consumable.getEntityName() + "<br>" + "Effet : " + consumable.getConsumableEffect() + "<br>" + "Valeur : " + consumable.getConsumableValue() + "</html>";
            }
            else {
                tooltipText = "<html>" + item.getEntityName() + "</html>";
            }
            itemPanel.setToolTipText(tooltipText);
        }
        
        itemPanel.setToolTipText(tooltipText);
        panel.add(itemPanel);
    }
}
