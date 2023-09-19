package game;
import javax.swing.JOptionPane;

public class UserInterface 
{
    public static void printMessage(String msg) 
    {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static int askForInt(String msg, Board board) 
    {
        String tempInput = JOptionPane.showInputDialog(null, msg);

        if (tempInput == null) 
        {
            return -2;
        }
        try 
        {
            int inputValue = Integer.parseInt(tempInput);

            if ((inputValue == 1 || inputValue == 2) && inputValue <= board.getSticks())
            {
                return inputValue;
            } 
            else 
            {
                printMessage("Invalid input, try again.");
                return -1;
            }
        } catch (NumberFormatException e) 
        {
            printMessage("Invalid input, try again.");
            return -1;
        }
    }   
}
