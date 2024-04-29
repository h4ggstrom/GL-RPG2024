package engine.process.factories;

import engine.dungeon.Position;
import engine.entities.items.weapons.Weapon;

public class WeaponFactory {
    
    public static Weapon createWeapon(String weaponType, Position position) {
        switch(weaponType) {
            case "epee_de_rat":
                return new Weapon(5,20,"Épée de rat","epee_de_rat",position);
            case "epee_de_chevalier":
                return new Weapon(10,35,"Épée de chevalier","epee_de_chevalier",position);
            case "epee_legendaire_des_egouts":
                return new Weapon(20,50,"Épée légendaire des égouts","epee_legendaire_des_egouts",position);
            case "arc_de_chevre":
                return new Weapon(10,100,"Arc de chèvre","arc_de_chevre",position);
            case "arc_des_terres_arides":
                return new Weapon(15,100,"Arc des terres arides","arc_des_terres_arides",position);
            case "dagues_de_crackhead":
                return new Weapon(15,0,"Dagues de crackhead","dagues_de_crackhead",position);
            case "arc_mystique_de_gobelin":
                return new Weapon(25,100,"Arc mystique de gobelin","arc_mystique_de_gobelin",position);
            case "dagues_de_secretaire":
                return new Weapon(20,0,"Dagues de secrétaire","dagues_de_secretaire",position);
            case "sceptre_des_terres_arides":
                return new Weapon(20,200,"Sceptre des terres arides","sceptre_des_terres_arides",position);
            case "sceptre_de_professeur":
                return new Weapon(25,200,"Sceptre de professeur","sceptre_de_professeur",position);
            case "tampon_legendaire_de_derdoudiable":
                return new Weapon(30,0,"Tampon légendaire de Derdoudiable","tampon_legendaire_de_derdoudiable",position);
            case "ak_47":
                return new Weapon(47,500,"AK-47","ak_47",position);
            default:
                throw new IllegalArgumentException("Type d'arme inconnu : " + weaponType);
        }
    }

}
