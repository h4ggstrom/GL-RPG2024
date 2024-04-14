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
import engine.entities.characters.Player;
import engine.entities.items.Coin;
import engine.entities.items.Item;
import engine.entities.items.Slot;
import engine.entities.containers.Bag;
import engine.process.EntityManager;

public class BagGUI extends ContainerGUI {

    private Bag bag;
    private JPanel upperPanel = new JPanel();
    private JPanel lowerPanel = new JPanel();

    public BagGUI(EntityManager manager, Bag bag) {
        super(manager);
        this.bag = bag;
        EntityManager.bagRefreshListener = this;

        initOverallView();

        setSize(400, 225);
        setLocationRelativeTo(null);
        setTitle("Bag");
		setVisible(true);
    }

    public void initOverallView() {
        // On veut 2 lignes, on mettra 6 slots sur chaque ligne
        this.getContentPane().setLayout(new GridLayout(2, 1));
        upperPanel.setLayout(new GridLayout(1, 6));
        lowerPanel.setLayout(new GridLayout(1, 6));
        // On dessine les deux parties
        initBagSlotsPanels();
        // On ajoute ces deux parties à notre JFrame
        add(upperPanel);
        add(lowerPanel);
    }

    public void initBagSlotsPanels() {
        // On récupère la liste des slots du sac
        ArrayList<Slot> slots = bag.getSlots();
        for(int i = 0 ; i < GameConfiguration.BAG_MAX ; i++) {
            // On garde le compte
            final int slotNumber = i;
            // On récupère l'Item du Slot
            Item item = slots.get(i).getItem();
            // On créé le Panel qui recevra l'Item
            JPanel itemPanel = new JPanel();

            JPanel currentPanel = null;
            if(slotNumber < 6) {
                currentPanel = upperPanel;
            }
            else {
                currentPanel = lowerPanel;
            }
            initItemSlot(currentPanel, itemPanel, item, (item == null) ? "" : item.getEntityType());

            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem pickupItem = new JMenuItem("Ramasser");
                    popupMenu.add(pickupItem);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());

                    if(item instanceof Coin) {
                        pickupItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Slot currentSlot = bag.getSlots().get(slotNumber);
                                currentSlot.setItem(null); 
                                Player.getInstance().addCoins();
                            }
                        });
                    }

                    pickupItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            manager.pickupBagItem(bag.getSlots().get(slotNumber));
                        }
                    });
                }
            });
        }
    }

    public void refreshContainer() {
        upperPanel.removeAll();
        lowerPanel.removeAll();
        initOverallView();
        upperPanel.revalidate();
        lowerPanel.revalidate();
        upperPanel.repaint();
        lowerPanel.repaint();
    }
    
}
