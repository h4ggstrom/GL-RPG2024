package engine.process.management;

import engine.entities.characters.Player;
import engine.entities.items.Slot;
import engine.entities.items.consumables.Consumable;

/**
 * Cette classe permet l'utilisation de tout objet consommable par le joueur
 */
public class ConsumableManager {

    private static EntityManager manager = EntityManager.getInstance();

    /**
     * Méthode permettant de consommer un objet de l'inventaire
     * 
     * @param slotNumber le numéro du slot de l'objet à consommer
     */
    public static void consumeInventoryItem(int slotNumber) {
        // On récupère le slot d'inventaire concerné
        Slot slot = Player.getInstance().getInventory().getSlots().get(slotNumber);
        // On récupère le consommable
        Consumable consumable = (Consumable)slot.getItem();
        // On supprime l'item de l'inventaire
        Player.getInstance().getInventory().removeItem(slotNumber);
        slot.setItem(null);
        // On vérifie son effet et on fait en fonction
        switch (consumable.getConsumableEffect()) {
            case "Régénération":
                Player.getInstance().healCharacter(consumable.getConsumableValue());
                break;
            case "Mana":
                Player.getInstance().addMana(consumable.getConsumableValue());
                break;
            case "money":
                Player.getInstance().addCoins(consumable.getConsumableValue());
                break;
            default:
                throw new IllegalArgumentException("Effet du consommable inconnu : " + consumable.getConsumableEffect());
        }
        manager.refreshContainers();
    }
}
