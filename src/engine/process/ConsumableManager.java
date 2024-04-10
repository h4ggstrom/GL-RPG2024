package engine.process;

import engine.entities.characters.Player;
import engine.entities.items.Slot;
import engine.entities.items.consumables.Consumable;

/**
 * Cette classe permet l'utilisation de tout objet consommable par le joueur
 */
public class ConsumableManager {

    public static void consumeItem(Slot slot) {
        // On récupère le consommable
        Consumable consumable = (Consumable)slot.getItem();
        // On le supprime du slot
        slot.setItem(null);
        // On vérifie son effet et on fait en fonction
        switch (consumable.getConsumableEffect()) {
            case "heal":
                Player.getInstance().healCharacter(consumable.getConsumableValue());
            default:
                throw new IllegalArgumentException("Effet du consommable inconnu : " + consumable.getConsumableEffect());
        }
    }
}
