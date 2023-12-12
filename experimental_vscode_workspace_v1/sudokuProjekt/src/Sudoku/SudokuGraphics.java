package Sudoku;

import java.awt.*;
import javax.swing.*;

public class SudokuGraphics
{   
    private SudokuGame aGame;
    private JTextField[][] boardStrings;
    private int[][] boardInts;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new SudokuGraphics().createWindow ("Sudoku Game", 500, 500));
    }

    public SudokuGraphics() 
    {
        aGame = new SudokuGame();
        boardStrings = new JTextField[aGame.getBoard().length][aGame.getBoard()[0].length];
        boardInts = new int[aGame.getBoard().length][aGame.getBoard()[0].length];

    }

    private void createWindow(String title, int width, int height) 
    {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

        JPanel aBoard = createBoard(aGame.getBoard());
        
        JPanel viewPanel = new JPanel(new FlowLayout());

        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");

        solveButton.addActionListener(x ->
        {
            for (int i = 0; i < boardInts.length; i++)
            {
                for (int j = 0; j < boardInts.length; j++)
                {
                    try
                    {
                        if (boardStrings[i][j].getText().equals(""))
                        {
                            boardInts[i][j] = 0;
                        }
                        else
                        {
                            int tempParse = Integer.parseInt(boardStrings[i][j].getText());
                            if (tempParse > 0 && tempParse < 10)
                            {
                                boardInts[i][j] = tempParse;
                            }
                            else
                            {
                                throw new NumberFormatException();
                            }
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        JOptionPane.showMessageDialog(frame, "Inkorrekt input. Rad: "+ (i) + " Col: " + (j));
                        return;
                    }
                }
            }

            aGame.setBoard(boardInts);
            aGame.solve();

            for (int i = 0; i < boardInts.length; i++) 
            {
                for (int j = 0; j < boardInts.length; j++) 
                {
                    boardStrings[i][j].setText(String.valueOf(boardInts[i][j]));
                }
            }

            if (aGame.solve())
            {
                JOptionPane.showMessageDialog(frame, "Det gick att lösa");
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Det gick inte att lösa");
            }
        });

        clearButton.addActionListener(x ->
        { 
            aGame.clear();
            for (int i = 0; i < boardInts.length; i++) 
            {
                for (int j = 0; j < boardInts.length; j++) 
                {
                   boardStrings[i][j].setText("");
                }
            }
        });

        pane.add(aBoard, BorderLayout.CENTER);
        pane.add(viewPanel, BorderLayout.SOUTH);

        viewPanel.add(solveButton);
        viewPanel.add(clearButton);

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createBoard(int[][] aTable)
    {
        int rowCount = aTable.length;
        int colCount = aTable[0].length;
        JPanel aPanel = new JPanel(new GridLayout(rowCount, colCount));

        for (int i = 0; i < rowCount; i++)
        {
            for (int j = 0; j < colCount; j++)
            {
                boardStrings[i][j] = new JTextField(1);
                boardStrings[i][j].setHorizontalAlignment(JTextField.CENTER);
                aPanel.setPreferredSize(new Dimension(1000, 1000));

                if ((i / 3 + j / 3) % 2 == 0)
                {
                    boardStrings[i][j].setBackground(Color.RED);
                } 
                else 
                {
                    boardStrings[i][j].setBackground(Color.WHITE);
                }
                aPanel.add(boardStrings[i][j]);
            }
        }
        return aPanel;
    }
}