package gui;

import javax.swing.JDialog;

import engine.characters.Player;

public class InventoryDisplay extends JDialog {

    private Player player;
    
    public InventoryDisplay(Player player) {
        super();
        this.player = player;
        setSize(300, 200);
        setVisible(false);
    }

    public void updateInventory() {
        
    }

}
