package game;

public abstract class Player
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