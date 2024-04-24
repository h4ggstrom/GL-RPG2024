package gui.containersGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import config.GameConfiguration;
import engine.entities.items.Item;
import engine.entities.items.Slot;
import engine.entities.containers.Chest;

public class ChestGUI extends ContainerGUI {

    private Chest chest;
    private JPanel panel = new JPanel();

    public ChestGUI(Chest chest) {
        this.chest = chest;
        super.manager.setChestRefreshListener(this);

        initOverallView();

        pack();
        setLocationRelativeTo(null);
        setTitle("Chest");
		setVisible(true);
    }

    public void initOverallView() {
        // On veut 1 ligne et 1 colonne
        this.getContentPane().setLayout(new GridLayout(1, 1));
        add(panel);
        initChestSlot();
    }

    public void initChestSlot() {
        // On récupère la liste des slots du sac
        ArrayList<Slot> slots = chest.getSlots();
        for(int i = 0 ; i < GameConfiguration.CHEST_MAX ; i++) {
            // On garde le compte
            final int slotNumber = i;
            // On récupère l'Item du Slot
            Item item = slots.get(i).getItem();
            // On créé le Panel qui recevra l'Item
            JPanel itemPanel = new JPanel();
            initItemSlot(panel, itemPanel, item, (item == null) ? "" : item.getEntityType());

            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem pickupItem = new JMenuItem("Ramasser");
                    popupMenu.add(pickupItem);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());

                    pickupItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            manager.pickupBagItem(chest.getSlots().get(slotNumber));
                        }
                    });
                }
            });
        }
    }

    public void refreshContainer() {
        panel.removeAll();
        initOverallView();
        panel.repaint();
        panel.revalidate();

    }
    
}
