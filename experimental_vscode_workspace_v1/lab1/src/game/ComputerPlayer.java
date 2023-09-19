package game;
import java.util.Random;

public class ComputerPlayer extends Player
{
    private Random rng;
    private static final int finalSum = 2;

    public ComputerPlayer(String compID)
    {
        super(compID);
        rng = new Random();
    }

    public @Override int takeSticks(Board board)
    {
        int potentialMove = Math.min(board.getSticks(), finalSum);
        return (rng.nextInt(potentialMove) + 1);  
    }
    
}
