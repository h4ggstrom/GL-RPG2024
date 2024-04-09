package gui.containersGUI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.entities.items.Slot;
import engine.entities.items.containers.Bag;
import engine.process.EntityManager;

public class BagGUI extends ContainerGUI {

    private Bag bag;

    public BagGUI(EntityManager manager, Bag bag) {
        super(manager);
        this.bag = bag;

        initOverallView();

        setSize(400, 225);
        setLocationRelativeTo(null);
        setTitle("Bag");
		setVisible(true);
    }

    public void initOverallView() {
        // On veut 2 lignes, on mettra 6 slots sur chaque ligne
        this.getContentPane().setLayout(new GridLayout(2, 1));
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(1, 6));
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(1, 6));
        // On récupère la liste des slots du sac
        ArrayList<Slot> slots = bag.getSlots();
        // On découpe les slots en deux moitiés
        ArrayList<Slot> firstHalf = new ArrayList<Slot>(slots.subList(0, 5));
        ArrayList<Slot> secondHalf = new ArrayList<Slot>(slots.subList(6, 11));
        // On dessine les deux parties
        initSlotsPanels(upperPanel, firstHalf);
        initSlotsPanels(lowerPanel, secondHalf);
        // On ajoute ces deux parties à notre JFrame
        add(upperPanel);
        add(lowerPanel);
    }

    public void refreshContainer() {

    }
    
}
