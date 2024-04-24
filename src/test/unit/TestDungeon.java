package test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import engine.dungeon.Dungeon;
import engine.dungeon.Position;
import engine.entities.Entity;
import engine.process.management.EnemyFactory;
import engine.process.management.EntityManager;
import engine.process.management.GameBuilder;


public class TestDungeon {
    
    private Dungeon dungeon;

    /**
     * set le donjon avant d'effectuer les tests dessus.
     */
    @Before
    public void prepareDungeon() {
        this.dungeon = GameBuilder.buildDungeon();
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
        int nbBefore = EntityManager.getCurrentRoom().getEntities().size();
        Position p = new Position(500, 500);
        Entity ent = EnemyFactory.createEnemy("rat", p);
        EntityManager.getCurrentRoom().addEntity(ent);
        int nbAfter = EntityManager.getCurrentRoom().getEntities().size();
        assertEquals(nbBefore,nbAfter-1);
    }
    
    /**
     * Teste la suppression d'une entité à la room
     */
    @Test
    public void testRmEntity() {
        Position p = new Position(500, 500);
        Entity ent = EnemyFactory.createEnemy("rat", p);
        EntityManager.getCurrentRoom().addEntity(ent);
        int nbBefore = EntityManager.getCurrentRoom().getEntities().size();
        EntityManager.getCurrentRoom().removeEntity(ent);
        int nbAfter = EntityManager.getCurrentRoom().getEntities().size();
        assertEquals(nbBefore,nbAfter+1);
    }


}
