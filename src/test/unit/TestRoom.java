package test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import engine.process.GameBuilder;
import engine.dungeon.Room;
import engine.Entity;
import engine.characters.Enemy;
import engine.dungeon.Position;


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
        int nbBefore = room.getEntities().size();
        Position p = new Position(500, 500);
        Entity ent = new Enemy(p);
        room.addEntity(ent);
        int nbAfter = room.getEntities().size();
        assertEquals(nbBefore,nbAfter-1);
    }
    
    /**
     * Teste la suppression d'une entité à la room
     */
    @Test
    public void testRmEntity() {
        Position p = new Position(500, 500);
        Entity ent = new Enemy(p);
        room.addEntity(ent);
        int nbBefore = room.getEntities().size();
        room.removeEntity(ent);
        int nbAfter = room.getEntities().size();
        assertEquals(nbBefore,nbAfter+1);
    }


}
