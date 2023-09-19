package game;

public class TakePinsGame extends Board
{
    public static void main(String[] args) throws InterruptedException
    {
        Board board = new Board();
        boolean playerTurn = true;
        int sticksTaken;
        board.setUp(10);

        Player humanPlayer = new HumanPlayer("Human");
        Player newCompPlayer = new NewCompPlayer("New Computer");

        while (board.getSticks() > 0)
        {
            UserInterface.printMessage("There are " + board.getSticks() + " sticks left on the board.");

            if (playerTurn)
            {
                sticksTaken = humanPlayer.takeSticks(board);

                if (sticksTaken == -2)
                {
                    UserInterface.printMessage("Program terminated.");
                    System.exit(0);
                }

                while (sticksTaken == -1)
                {
                    sticksTaken = humanPlayer.takeSticks(board);
                }
                board.takeSticks(sticksTaken);
                UserInterface.printMessage(humanPlayer.getUserId() + " took " + sticksTaken + " stick(s).");
            }

            else
            {
                sticksTaken = newCompPlayer.takeSticks(board);
                board.takeSticks(sticksTaken);
                UserInterface.printMessage(newCompPlayer.getUserId() + " took " + sticksTaken + " stick(s).");
            }

            if(board.getSticks() == 0)
            {
                if (playerTurn)
                {
                    UserInterface.printMessage(humanPlayer.getUserId() + " wins!\n");
                }
                else
                {
                    UserInterface.printMessage(newCompPlayer.getUserId() + " wins!\n");
                }
            }
            playerTurn = !playerTurn;
        }  
    }
}