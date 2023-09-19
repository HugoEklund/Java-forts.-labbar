package game;

public class HumanPlayer extends Player
{

    public HumanPlayer(String humanID)
    {
        super(humanID);
    }

    public @Override int takeSticks(Board board)
    {
        return UserInterface.askForInt("\nDo you want to remove 1 or 2 sticks?", board);
    }
}
