package gui.containersGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.entities.npc.Vendor;
import engine.entities.characters.Player;
import engine.entities.items.Item;

/**
 * Classe permettant de créer une interface graphique pour un vendeur.
 * 
 * @see ContainerGUI
 */
public class VendorGUI extends ContainerGUI {
    private Vendor vendor;
    private Player player = Player.getInstance();
    private JLabel balanceLabel; // Affiche les fonds disponibles du joueur
    private JPanel vendorPanel = new JPanel(new GridLayout(4, 1));

    /**
     * Constructeur de la classe VendorGUI.
     * 
     * @param vendor le vendeur
     */
    public VendorGUI(Vendor vendor) {
        super.manager.setVendorRefreshListener(this);
        this.vendor = vendor;
        setTitle("Magasin de " + vendor.getEntityName());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        setSize(600, 300);

        add(vendorPanel);
        init();

        setVisible(true);
    }

    /**
     * Méthode permettant d'initialiser l'interface graphique du vendeur.
     */
    public void init() {
        // Afficher les fonds du joueur
        balanceLabel = new JLabel("Fonds disponibles : " + player.getCoinCount() + " pièces");
        vendorPanel.add(balanceLabel);

        // Pour chaque item à vendre, ajouter un JPanel avec le nom de l'item, son prix et un bouton pour acheter
        for (Item item : vendor.getItemsForSale().keySet()) {
            int price = vendor.getItemsForSale().get(item);
            JPanel sellPanel = new JPanel();
            sellPanel.setLayout(new GridLayout(1, 2));
            JPanel itemPanel = new JPanel();
            initItemSlot(sellPanel, itemPanel, item, (item == null) ? "":item.getEntityName());
            sellPanel.add(new JLabel(" Prix: " + price + " pièces"));

            JButton buyButton = new JButton("Acheter");
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player.getCoinCount() >= price && !player.getInventory().isFull()) {
                        JOptionPane.showMessageDialog(VendorGUI.this, "Vous avez acheté " + item.getEntityName() + " pour " + price + " pièces.", "Achat réussi", JOptionPane.INFORMATION_MESSAGE);
                        player.removeCoins(price);
                        vendor.removeSellingItem(item);
                        player.getInventory().addItem(item);
                        refreshContainer();
                    } else {
                        JOptionPane.showMessageDialog(VendorGUI.this, "Fonds insuffisants pour acheter " + item.getEntityName() + ".", "Achat échoué", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            sellPanel.add(buyButton);
            vendorPanel.add(sellPanel);
        }
    }

    /**
     * Méthode permettant de rafraîchir l'affichage des 3 panels principaux de la fenêtre
     */
    @Override
    public void refreshContainer() {
        vendorPanel.removeAll();
        init();
        vendorPanel.revalidate();
        vendorPanel.repaint();
    }
}
