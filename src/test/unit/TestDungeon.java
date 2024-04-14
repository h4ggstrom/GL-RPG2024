package test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import engine.process.EntityManager;
import engine.process.GameBuilder;
import engine.dungeon.Dungeon;
import engine.dungeon.Position;
import engine.entities.Entity;
import engine.entities.characters.Enemy;


public class TestDungeon {
    
    private Dungeon dungeon;
    private EntityManager manager;

    /**
     * set le donjon avant d'effectuer les tests dessus.
     */
    @Before
    public void prepareDungeon() {
        this.dungeon = GameBuilder.buildDungeon();
    }

    /**
     * set le manager avant d'effectuer les tests dessus
     */
    @Before
    public void prepareManager() {
        this.manager = GameBuilder.buildInitCharacters(dungeon);
    }

    /**
     * Test vérifiant l'instance dungeon. Si elle est nulle, marque le test comme échoué. 
     */
    @Test
    public void testDungeon() {
        assertNotNull(dungeon);
    }

    /**
     * Teste l'ajout d'entité à la room
     */
    @Test
    public void testAddEntity() {
        int nbBefore = manager.getRoom().getEntities().size();
        Position p = new Position(500, 500);
        Entity ent = new Enemy(p, null);
        manager.getRoom().addEntity(ent);
        int nbAfter = manager.getRoom().getEntities().size();
        assertEquals(nbBefore,nbAfter-1);
    }
    
    /**
     * Teste la suppression d'une entité à la room
     */
    @Test
    public void testRmEntity() {
        Position p = new Position(500, 500);
        Entity ent = new Enemy(p, null);
        manager.getRoom().addEntity(ent);
        int nbBefore = manager.getRoom().getEntities().size();
        manager.getRoom().removeEntity(ent);
        int nbAfter = manager.getRoom().getEntities().size();
        assertEquals(nbBefore,nbAfter+1);
    }


}
