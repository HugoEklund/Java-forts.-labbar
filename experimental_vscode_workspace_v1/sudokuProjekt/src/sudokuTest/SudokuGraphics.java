package SudokuTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Sudoku.SudokuGame;

public class SudokuGraphics
{   
    private SudokuGame aGame;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new SudokuGraphics().createWindow ("Sudoku Game", 500, 500));
    }

    public SudokuGraphics() 
    {
        aGame = new SudokuGame();
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

        solveButton.addActionListener(x -> { aGame.solve(); });
        clearButton.addActionListener(x -> { aGame.clear(); });

        pane.add(aBoard, BorderLayout.CENTER);
        pane.add(viewPanel, BorderLayout.SOUTH);

        viewPanel.add(solveButton);
        viewPanel.add(clearButton);

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createBoard(int[][] aTable)
    {
        int rows = aTable.length;
        int columns = aTable[0].length;

        JPanel aPanel = new JPanel(new GridLayout(rows, columns));
        JTextField[][] someFields = new JTextField[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++) 
            {
                JTextField textField = new JTextField(1);
                textField.setHorizontalAlignment(JTextField.CENTER);
                aPanel.add(textField);
                someFields[i][j] = textField;
            }
        }
        return aPanel;
    }
}