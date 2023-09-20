package game;
import javax.swing.JOptionPane;

public class UserInterface 
{
    public static void printMessage(String msg) 
    {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static int askForInt(String msg) 
    {
        String tempInput = JOptionPane.showInputDialog(null, msg);

        if (tempInput == null)
        {
            return -2;
        }
        else{
                    try 
        {
            int inputValue = Integer.parseInt(tempInput);
            return inputValue;
        } catch (NumberFormatException e) 
        {
            return -1;
        }
        }

    }   
}
