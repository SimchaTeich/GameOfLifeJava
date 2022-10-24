package GameOfLife;

import java.util.ArrayList;
import java.util.Iterator;

public class Game
{
    private boolean _done;
    private int _height;
    private int _width;
    private Cell[][] _board;
    private boolean[][] _tableOfChanges;
    private ArrayList<Cordinta> _initCoordinates;


    /**
     * C'TOR
     * @param height
     * @param width
     * @param initCoordinates
     */
    Game(int height, int width, ArrayList<Cordinta> initCoordinates)
    {
        this._done = true;
        this._height = height;
        this._width = width;
        this._initCoordinates = initCoordinates;

        _board = new Cell[height][];
        _tableOfChanges = new boolean[height][];
        for(int i = 0; i < this._height; i ++)
        {
            _board[i] = new Cell[this._width];
            _tableOfChanges[i] = new boolean[this._width];
        }

        for(int i = 0; i < this._height; i ++)
        {
            for(int j = 0; j < this._width; j ++)
            {
                _board[i][j] = new Cell();
            }
        }
    }

    /**
     * init the first generation of living cells
     * on the board.
     */
    private void initBoard()
    {
        Iterator it = this._initCoordinates.iterator();
        while(it.hasNext())
        {
            Cordinta c = (Cordinta)it.next();
            this._board[c.getX()][c.getY()].revive();
        }
    }

    /**
     * print the board of cells.
     */
    private void printBoard()
    {
        for(int i = 0; i < this._height; i++)
        {
            System.out.print("+");
            for(int j = 0; j < this._width; j++)
            {
                System.out.print("---+");
            }
            System.out.println();

            for(int j = 0; j < this._width; j++)
            {
                System.out.print("|");
                if(this._board[i][j].isAlive())
                {
                    System.out.print(" @ ");
                }
                else
                {
                    System.out.print("   ");
                }
            }
            System.out.println("|");
        }

        System.out.print("+");
        for(int j = 0; j < this._width; j++)
        {
            System.out.print("---+");
        }
        System.out.println();
    }

    /**
     * count the number of living neighbors
     * of cell at specific cordinta.
     * @param c: Cordinta of cell to check
     * @return: number of living neighbors
     */
    private int getNumOfLiveNeighbors(Cordinta c)
    {
        int count = 0;
        int heigh = c.getX();
        int width = c.getY();

        for(int i = heigh-1; i <= heigh+1; i++)
        {
            for(int j = width-1; j <= width+1; j++)
            {
                // avoid form get out of bound of the board.
                if(i < 0 || j < 0 || i >= this._height || j >= this._width)
                {
                    continue;
                }

                if(this._board[i][j].isAlive()) count++;
            }
        }

        // uncounter the cordinta itself.
        return this._board[heigh][width].isAlive() ? count - 1: count;
    }

    /**
     * update the _tableOfChanges for the next generation.
     */
    private void checkForChanges()
    {
        for(int i = 0; i < this._height; i++)
        {
            for(int j = 0; j < this._width; j++)
            {
                int numOfLiveNeighbor = getNumOfLiveNeighbors(new Cordinta(i, j));

                if(this._board[i][j].isAlive())
                {
                    if(numOfLiveNeighbor == 2 || numOfLiveNeighbor == 3)
                    {
                        this._tableOfChanges[i][j] = false;
                    }
                    else
                    {
                        this._tableOfChanges[i][j] = true;
                        this._done = false;
                    }
                }
                else
                {
                    if(numOfLiveNeighbor == 3)
                    {
                        this._tableOfChanges[i][j] = true;
                        this._done = false;
                    }
                    else
                    {
                        this._tableOfChanges[i][j] = false;
                    }
                }
            }
        }
    }

    /**
     * update the _board for next generation
     * according to the _tableOfChanges
     */
    private void runGeneration()
    {
        for(int i = 0; i < this._height; i++)
        {
            for(int j = 0; j < this._width; j++)
            {
                if(this._tableOfChanges[i][j])
                {
                    if(this._board[i][j].isAlive())
                    {
                        this._board[i][j].kill();
                    }
                    else
                    {
                        this._board[i][j].revive();
                    }
                }
            }
        }
    }

    public void startGame()
    {
        initBoard();

        while(true)
        {
            // clear screen:
            // but no in java consul:
            // https://intellij-support.jetbrains.com/hc/en-us/community/posts/115000585870-Clear-IntelliJ-console-from-java
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // print board at current generation.
            printBoard();
            checkForChanges();

            if(!this._done)
            {
                runGeneration();

                try
                {
                    Thread.sleep( 1000);
                }
                catch (InterruptedException ie)
                {
                    Thread.currentThread().interrupt();
                }
            }
            else
            {
                break;
            }
        }
    }
}
