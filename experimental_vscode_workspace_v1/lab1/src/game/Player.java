package game;

public abstract class Player extends Board
{
    private String userId;

    public Player(String tempPlayer)
    {
        this.userId = tempPlayer;
    }

    public String getUserId()
    {
        return userId;
    }
    
    public abstract int takeSticks(Board board);
}