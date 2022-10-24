package GameOfLife;

import static org.junit.jupiter.api.Assertions.*;

class CordintaTest {

    @org.junit.jupiter.api.Test
    void getX()
    {
        Cordinta c1 = new Cordinta(0,0);
        assert c1.getX() == 0;

        Cordinta c2 = new Cordinta(1,2);
        assert c2.getX() != 2;
    }

    @org.junit.jupiter.api.Test
    void getY()
    {
        Cordinta c1 = new Cordinta(0,0);
        assert c1.getY() == 0;

        Cordinta c2 = new Cordinta(1,2);
        assert c2.getY() == 2;
    }
}