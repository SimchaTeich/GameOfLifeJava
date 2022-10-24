package GameOfLife;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Cordinta c1 = new Cordinta(2,1);
        Cordinta c2 = new Cordinta(2,2);
        Cordinta c3 = new Cordinta(2,3);
        ArrayList<Cordinta> initCoordinates = new ArrayList<Cordinta>();
        initCoordinates.add(c1);
        initCoordinates.add(c2);
        initCoordinates.add(c3);
        Game g = new Game(5, 5, initCoordinates);
        g.startGame();
    }
}