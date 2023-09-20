package game;
import java.util.Random;

public class ComputerPlayer extends Player
{
    private Random rng;

    public ComputerPlayer(String compID)
    {
        super(compID);
        rng = new Random();
    }

    public @Override int takeSticks(Board board)
    {
        int potentialMove = Math.min(board.getSticks(), 2);
        return board.takeSticks(rng.nextInt(potentialMove) + 1);  
    }
    
}
