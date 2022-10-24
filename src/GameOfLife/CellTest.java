package GameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void isAlive()
    {
        Cell c = new Cell();
        assertFalse(c.isAlive());
    }

    @Test
    void revive()
    {
        Cell c = new Cell();
        c.revive();
        assert c.isAlive();
    }

    @Test
    void kill()
    {
        Cell c = new Cell();
        c.revive();
        c.kill();
        assertFalse(c.isAlive());
    }
}