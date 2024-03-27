package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import engine.characters.Player;
import engine.items.Inventory;
import engine.items.Item;
import engine.items.Slot;
import engine.items.weapons.Sword;
import config.GameConfiguration;

public class InventoryGUI extends JFrame {

    private Inventory inventory = Player.getInstance().getInventory();
    private JButton btnReturn = new JButton("Return");
    private JPanel itemsPanel = new JPanel();
    private JPanel PlayerViewPanel = new JPanel();
    
    public InventoryGUI() {
        initLayout();
        initActions(); 
        InventoryItems(); 
        ShowPlayer();
    }

    private void initLayout(){
        btnReturn.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel());
        panel1.add(btnReturn);
        panel1.add(PlayerViewPanel);
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Items"));
        panel2.add(itemsPanel, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        splitPane.setDividerLocation(150); 

        itemsPanel.setLayout(new GridLayout(5, 1));

        add(splitPane);
        setSize(GameConfiguration.INVENTORY_WIDTH, GameConfiguration.INVENTORY_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Inventory");
		setVisible(true);
    }

    private void initActions(){
        btnReturn.addActionListener(new Return());
    }

    private void ShowPlayer(){
        PlayerViewPanel.setLayout(new BorderLayout()); 

        String playerFilePath = "src/ressources/bodyView.png";
        ImageIcon playerIcon = new ImageIcon(playerFilePath);
        JLabel nameLabel = new JLabel("Player", JLabel.CENTER);
        JLabel iconLabel = new JLabel(playerIcon, JLabel.CENTER);


        PlayerViewPanel.add(iconLabel, BorderLayout.CENTER);
        PlayerViewPanel.add(nameLabel, BorderLayout.PAGE_END);

        PlayerViewPanel.revalidate(); 
        PlayerViewPanel.repaint();

    }


    private void InventoryItems() {
        List<Slot> slots = inventory.getSlots();
        for (Slot slot : slots) {
            Item item = slot.getItem();
            if (item != null) {
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BorderLayout());
                itemPanel.setBackground(Color.WHITE);
                itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                String itemFilePath = "";
                if (item instanceof Sword) {
                    itemFilePath = "src/ressources/sword.png";
                }

                JLabel nameLabel = new JLabel("sword", JLabel.CENTER);
                ImageIcon itemIcon = new ImageIcon(itemFilePath);
                JLabel iconLabel = new JLabel(itemIcon, JLabel.CENTER);

                itemPanel.add(iconLabel, BorderLayout.CENTER);
                itemPanel.add(nameLabel, BorderLayout.PAGE_END);

                itemsPanel.add(itemPanel);
            }
        }
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    class Return implements ActionListener {
		public void actionPerformed(ActionEvent e){
            InventoryGUI.this.setVisible(false);
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InventoryGUI().setVisible(true);
            }
        });
    }
}
