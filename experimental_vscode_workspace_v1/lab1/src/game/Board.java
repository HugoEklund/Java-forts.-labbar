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
        if ((someSticks == 1 || someSticks == 2) && someSticks <= allSticks)
        {
            allSticks -= someSticks;
            return someSticks;
        }
        else if(someSticks == -2)
        {
            UserInterface.printMessage("Program terminated.");
            System.exit(0);
            return 0;
        }
        else
        {
            UserInterface.printMessage("Invalid input, try again.");
            return -1;
        }
    }

    public int getSticks()
    {
        return allSticks;
    }
}