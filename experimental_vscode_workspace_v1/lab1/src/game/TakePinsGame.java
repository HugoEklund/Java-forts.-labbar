package game;

import javax.swing.JOptionPane;

public class TakePinsGame
{
    public static void main(String[] args)
    {
        Board board = new Board();
        boolean playerTurn = true;
        int sticksTaken;
        board.setUp(10);

        Player humanPlayer = new HumanPlayer("Human");
        Player newCompPlayer = new NewCompPlayer("New Computer");

        while (board.getSticks() > 0)
        {
            if (playerTurn)
            {
                sticksTaken = humanPlayer.takeSticks(board);

                while (sticksTaken == -1)
                {
                    sticksTaken = humanPlayer.takeSticks(board);
                }
                UserInterface.printMessage(humanPlayer.getUserId() + " took " + sticksTaken + " stick(s).");
            }
            else
            {
                sticksTaken = newCompPlayer.takeSticks(board);

                UserInterface.printMessage(newCompPlayer.getUserId() + " took " + sticksTaken + " stick(s).");
                UserInterface.printMessage("There are " + board.getSticks() + " sticks left on the board.");
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