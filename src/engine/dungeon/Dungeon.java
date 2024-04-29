package engine.dungeon;

import java.util.ArrayList;

import config.GameConfiguration;

/*
 * Génie Logiciel - Projet RPG
 * 
 * Cette classe contient les données relatives au donjon.
 */
public class Dungeon {
    
    private ArrayList<Stage> stages;

    /**
     * Constructeur par défaut. Crée une nouvelle instance de donjon en générant les étages.
     */
    public Dungeon() {
        this.stages = new ArrayList<Stage>();
        for(int i = 0 ; i < GameConfiguration.NUMBER_OF_STAGES ; i++) {
            this.stages.add(new Stage(i+1));
        }
    }

    public ArrayList<Stage> getStages() {
        return this.stages;
    }
}
