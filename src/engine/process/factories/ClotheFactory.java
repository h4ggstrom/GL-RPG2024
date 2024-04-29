package engine.process.factories;

import engine.dungeon.Position;
import engine.entities.items.equipment.*;

public class ClotheFactory {
    
    public static Clothe createClothe(String clotheType, Position position) {
        switch(clotheType) {
            case "chapeau_de_rat":
                return new Helmet(-200, "Chapeau de rat", "chapeau_de_rat", position);
            case "casque_de_chevalier":
                return new Helmet(-400, "Casque de chevalier", "casque_de_chevalier", position);
            case "casque_des_terres_arides":
                return new Helmet(-600, "Casque des terres arides", "casque_des_terres_arides", position);
            case "gants_d_aventurier":
                return new Gloves(-1000, "Gants d'aventurier", "gants_d_aventurier", position);
            case "pantalon_de_troubadour":
                return new Pants(-500, "Pantalon de troubadour", "pantalon_de_troubadour", position);
            case "plastron_de_chevalier":
                return new Chestplate(10, "Plastron de chevalier", "plastron_de_chevalier", position);
            case "plastron_des_terres_arides":
                return new Chestplate(15, "Plastron des terres arides", "plastron_des_terres_arides", position);
            case "bottes_remplies_de_blattes":
                return new Boots(1, "Bottes remplies de blattes", "bottes_remplies_de_blattes", position);
            case "bottes_d_aventurier":
                return new Boots(2, "Bottes d'aventurier", "bottes_d_aventurier", position);
            case "nike_de_clignancourt":
                return new Boots(4, "Nike de Clignancourt", "nike_de_clignancourt", position);
            default:
                throw new IllegalArgumentException("Vêtement inconnu : " + clotheType);
        }
    }

}
