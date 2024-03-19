package test.unit;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import engine.process.GameBuilder;
import engine.dungeon.Room;
import engine.Entity;
import java.util.ArrayList;

public class TestRoom {
    
    private Room room;

    /**
     * set la room avant d'effectuer les tests dessus.
     */
    @Before
    public void prepareRoom() {
        this.room = GameBuilder.buildRoom();
    }

    /**
     * Test vérifiant l'instance room. Si elle est nulle, marque le test comme échoué. 
     */
    @Test
    public void testRoom() {
        assertNotNull(room);
    }

    /**
     * Teste l'ajout d'entité à la room
     */
    @Test
    public void testAddEntity() {
        ArrayList<Entity> entities = room.getEntities();
        // @TODO
        
        
    }


}
