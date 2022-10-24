package GameOfLife;

public class Cell
{
    private boolean _isAlive;

    /**
     * C'TOR
     */
    public Cell()
    {
        this._isAlive = false;
    }

    // getter
    public boolean isAlive()
    {
        return this._isAlive;
    }

    /**
     * revive the cell
     * */
    public void revive()
    {
        this._isAlive = true;
    }

    /**
     * kill the cell
     * */
    public void kill()
    {
        this._isAlive = false;
    }
}
