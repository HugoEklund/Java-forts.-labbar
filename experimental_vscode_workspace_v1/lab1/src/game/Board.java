package game;

public class Board
{
    private int allSticks;

    public void setUp(int tempSum)
    {
        this.allSticks = tempSum;
    }

    public int takeSticks(int someSticks)
    {
        allSticks -= someSticks;

        return someSticks;
    }

    public int getSticks()
    {
        return allSticks;
    }
}