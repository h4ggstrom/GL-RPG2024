package engine.process.factories;

import engine.dungeon.Position;
import engine.entities.characters.Enemy;

/**
 * Cette classe permet la création des multiples type d'ennemis
 * Les vitesses (sauf déplacement) sont exprimées en tick (10 millisecondes)
 */
public class EnemyFactory {

    /**
     * Crée un ennemi de type Enemy.
     * 
     * @param enemyType le type de l'ennemi
     * @param position la position de l'ennemi
     * @return l'ennemi créé
     */
    public static Enemy createEnemy(String enemyType, Position position) {
        switch (enemyType) {
            case "rat_fistule":
                Enemy ratFistuled = new Enemy(position, "Rat fistulé", "rat_fistule");
                ratFistuled.setMaxHealth(10);
                ratFistuled.setHealth(10);
                ratFistuled.setArmor(1);
                ratFistuled.setAttackDamage(5);
                ratFistuled.setAttackRange(45);
                ratFistuled.setAttackSpeed(100);
                ratFistuled.setMoveSpeed(1);
                ratFistuled.setStunCooldown(500);
                ratFistuled.setSightDistance(100);
                return ratFistuled;
            case "rocky_blateboa":
                Enemy rockyBlateboa = new Enemy(position, "Rocky Blateboa", "rocky_blateboa");
                rockyBlateboa.setMaxHealth(30);
                rockyBlateboa.setHealth(30);
                rockyBlateboa.setArmor(10);
                rockyBlateboa.setAttackDamage(12);
                rockyBlateboa.setAttackRange(55);
                rockyBlateboa.setAttackSpeed(250);
                rockyBlateboa.setMoveSpeed(2);
                rockyBlateboa.setStunCooldown(700);
                rockyBlateboa.setSightDistance(200);
                return rockyBlateboa;
            case "abomination_des_egouts":
                Enemy abominationDesEgouts = new Enemy(position, "Abomination des égouts", "abomination_des_egouts");
                abominationDesEgouts.setMaxHealth(200);
                abominationDesEgouts.setHealth(200);
                abominationDesEgouts.setArmor(30);
                abominationDesEgouts.setAttackDamage(20);
                abominationDesEgouts.setAttackRange(120);
                abominationDesEgouts.setAttackSpeed(700);
                abominationDesEgouts.setMoveSpeed(1);
                abominationDesEgouts.setStunCooldown(1000);
                abominationDesEgouts.setSightDistance(500);
                return abominationDesEgouts;
            case "crackhead":
                Enemy crackhead = new Enemy(position, "Crackhead", "crackhead");
                crackhead.setMaxHealth(100);
                crackhead.setHealth(100);
                crackhead.setArmor(2);
                crackhead.setAttackDamage(3);
                crackhead.setAttackRange(55);
                crackhead.setAttackSpeed(150);
                crackhead.setMoveSpeed(2);
                crackhead.setStunCooldown(600);
                crackhead.setSightDistance(300);
                return crackhead;
            case "chevre":
                Enemy chevre = new Enemy(position, "Chèvre", "chevre");
                chevre.setMaxHealth(80);
                chevre.setHealth(80);
                chevre.setArmor(1);
                chevre.setAttackDamage(2);
                chevre.setAttackRange(45);
                chevre.setAttackSpeed(100);
                chevre.setMoveSpeed(3);
                chevre.setStunCooldown(500);
                chevre.setSightDistance(55);
                return chevre;
            case "gobelin_malefique":
                Enemy gobelinMalefique = new Enemy(position, "Gobelin maléfique", "gobelin_malefique");
                gobelinMalefique.setMaxHealth(300);
                gobelinMalefique.setHealth(300);
                gobelinMalefique.setArmor(3);
                gobelinMalefique.setAttackDamage(4);
                gobelinMalefique.setAttackRange(40);
                gobelinMalefique.setAttackSpeed(150);
                gobelinMalefique.setMoveSpeed(1);
                gobelinMalefique.setStunCooldown(600);
                gobelinMalefique.setSightDistance(600);
                return gobelinMalefique;
            case "professor":
                Enemy professor = new Enemy(position, "Professor", "professor");
                professor.setMaxHealth(60);
                professor.setHealth(60);
                professor.setArmor(4);
                professor.setAttackDamage(6);
                professor.setAttackRange(40);
                professor.setAttackSpeed(200);
                professor.setMoveSpeed(3);
                professor.setStunCooldown(700);
                professor.setSightDistance(200);
                return professor;
            case "secretaire":
                Enemy secretaire = new Enemy(position, "Secrétaire", "secretaire");
                secretaire.setMaxHealth(25);
                secretaire.setHealth(25);
                secretaire.setArmor(2);
                secretaire.setAttackDamage(3);
                secretaire.setAttackRange(40);
                secretaire.setAttackSpeed(150);
                secretaire.setMoveSpeed(4);
                secretaire.setStunCooldown(600);
                secretaire.setSightDistance(200);
                return secretaire;
            case "derdoudiable":
                Enemy derdoudiable = new Enemy(position, "Derdoudiable", "derdoudiable");
                derdoudiable.setMaxHealth(500);
                derdoudiable.setHealth(500);
                derdoudiable.setArmor(50);
                derdoudiable.setAttackDamage(8);
                derdoudiable.setAttackRange(400);
                derdoudiable.setAttackSpeed(250);
                derdoudiable.setMoveSpeed(1);
                derdoudiable.setStunCooldown(800);
                derdoudiable.setSightDistance(700);
                return derdoudiable;
            default:
                throw new IllegalArgumentException("Type d'ennemi inconnu : " + enemyType);
        }
    }
}
