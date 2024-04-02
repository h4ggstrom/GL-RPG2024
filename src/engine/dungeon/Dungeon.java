package engine.dungeon;

import java.util.ArrayList;

import config.GameConfiguration;

public class Dungeon {
    
    private ArrayList<Stage> stages;

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
