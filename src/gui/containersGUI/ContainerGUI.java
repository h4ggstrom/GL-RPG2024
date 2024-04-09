package gui.containersGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.entities.items.Item;
import engine.entities.items.Slot;
import engine.process.ContainerRefreshListener;
import engine.process.EntityManager;

public abstract class ContainerGUI extends JFrame implements ContainerRefreshListener {

    public EntityManager manager;

    public ContainerGUI (EntityManager manager) {
        this.manager = manager;
        this.manager.setContainerRefreshListener(this);
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

    /**
     * Méthode permettant de dessiner les panels correspondant aux slots des containers (avec nom et image de l'item, bouton utiliser)
     * @param basePanel
     * @param slots
     */
    public void initSlotsPanels(JPanel basePanel, ArrayList<Slot> slots) {
        // On boucle autant de fois qu'il y a de slots en gardant le compte
        for (int i = 0; i < slots.size(); i++) {
            // On garde le compte
            final int slotNumber = i;
            // On récupère l'Item du Slot
            Item item = slots.get(i).getItem();
            // On créé le Panel qui recevra l'Item
            JPanel itemPanel = new JPanel();
    
            // Initialiser et ajouter le panel de l'item à inventoryPanel
            initItemSlot(basePanel, itemPanel, item, (item == null) ? "" : item.getEntityType());
            basePanel.add(itemPanel);
    
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
}
