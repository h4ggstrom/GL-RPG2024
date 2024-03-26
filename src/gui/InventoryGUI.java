package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Component;
import engine.characters.Player;
import engine.items.Inventory;
import config.GameConfiguration;

public class InventoryGUI extends JFrame {

    private Inventory inventory = Player.getInstance().getInventory();

    private JFrame frame = new JFrame("Inventory");
    private JButton btnReturn = new JButton("Return");
    
    public InventoryGUI() {
        initLayout();
        initActions();  
    }

    private void initLayout(){
        btnReturn.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Player"));
        panel1.add(btnReturn);
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Items"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        splitPane.setDividerLocation(150); 

        frame.add(splitPane);
        frame.setSize(GameConfiguration.INVENTORY_WIDTH, GameConfiguration.INVENTORY_HEIGHT);
        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
    }

    private void initActions(){
        btnReturn.addActionListener(new Return());
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
