package game;

public class NewCompPlayer extends Player
{
    public NewCompPlayer(String compID)
    {
        super(compID);
    }

    public @Override int takeSticks(Board board)
    {
        return 1;
    }
    
}