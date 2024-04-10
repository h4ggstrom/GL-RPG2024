package gui.containersGUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.entities.items.Item;
import engine.process.ContainerRefreshListener;
import engine.process.EntityManager;

public abstract class ContainerGUI extends JFrame implements ContainerRefreshListener {

    public EntityManager manager;

    public ContainerGUI (EntityManager manager) {
        this.manager = manager;
    }

    public void initItemSlot(JPanel panel, JPanel itemPanel, Item item, String slotName) {
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel nameLabel = new JLabel(slotName, JLabel.CENTER);
        itemPanel.add(nameLabel, BorderLayout.PAGE_START);

        if(item != null) {
            String itemFilePath = "src/ressources/" + item.getEntityType() + ".png";
            ImageIcon itemIcon = new ImageIcon(itemFilePath);
            JLabel itemIconLabel = new JLabel(itemIcon, JLabel.CENTER);
            itemPanel.add(itemIconLabel, BorderLayout.CENTER);
            
            // Configurer le tooltip avec des informations de l'item
            String tooltipText = "<html>" + "Nom : " + item.getEntityName() + "<br>" + "Effet : " + "<br>" + "Valeur : " + "</html>";
            itemPanel.setToolTipText(tooltipText);
        } else {
            // Configurer un texte de tooltip par défaut si l'item est null
            itemPanel.setToolTipText("Slot vide");
        }
    
        panel.add(itemPanel);
    }
}
