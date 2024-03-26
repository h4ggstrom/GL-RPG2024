package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Component;
import engine.characters.Player;
import engine.items.Inventory;
import config.GameConfiguration;

public class InventoryGUI extends JFrame {

    Inventory inventory = Player.getInstance().getInventory();

    private JButton btnReturn = new JButton("Return");
    
    public InventoryGUI() {
        initLayout();
        initActions();  
    }

    private void initLayout(){
        btnReturn.setAlignmentX(Component.LEFT_ALIGNMENT);

        setTitle("Inventory");
        setSize(GameConfiguration.INVENTORY_WIDTH, GameConfiguration.INVENTORY_HEIGHT);
        setLocationRelativeTo(null);
		setVisible(true);
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
