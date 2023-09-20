package game;

public class HumanPlayer extends Player
{

    public HumanPlayer(String humanID)
    {
        super(humanID);
    }

    public @Override int takeSticks(Board board)
    {
        return board.takeSticks(UserInterface.askForInt(getUserId()));
    }
}