package Sudoku;

public class SudokuGame implements SudokuSolver
{
	private int [][] sudokuBoard;

	public SudokuGame() 
	{
		sudokuBoard = new int[9][9];
	}

	/**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved. 
     * @param board a board to copy values from
     * @throws IllegalArgumentException if board is invalid, e.g. not 9x9
     */
	@Override
	public void setBoard(int[][] board)
	{
	    this.sudokuBoard = board;
	}

	   /**
     * Get a copy of the sudoku board
     */
	@Override
	public int[][] getBoard()
	{
		int[][] tempBoard = new int [9][9];
		tempBoard = this.sudokuBoard.clone();
		return tempBoard;
	}

	@Override
	public boolean solve() 
	{
	    return recursiveSolve(0, 0);
	}

	private boolean recursiveSolve(int row, int col) 
	{
		  // Base case: if we have gone beyond the last row, we have successfully filled all cells
        if (row == 9)
		{
            return true;
        }

        // Calculate the next row and column
        int nextRow;
        int nextCol;

        if (col == 8)
		{
            nextRow = row + 1;
            nextCol = 0;
        } 
		else 
		{
            nextRow = row;
            nextCol = col + 1;
        }

        // Case 1: If the current cell is not filled
        if (sudokuBoard[row][col] == 0)
		{
            // Try filling the current cell with numbers 1-9
            for (int i = 1; i <= 9; i++)
			{
                if (isLegal(row, col, i))
				{
                    sudokuBoard[row][col] = i; // Place the number

                    // Recursively try to solve the next cell
                    if (recursiveSolve(nextRow, nextCol))
					{
                        return true; // If successful, return true
                    }

                    // Backtrack if the recursive call fails
                    sudokuBoard[row][col] = 0;
                }
            }
            // If no number worked in this position, backtrack
            return false;
        } 
		else
		{
            // Case 2: If the current cell is already filled, check legality and move to the next cell
			if (isLegal(row, col, sudokuBoard[row][col]))
			{
           		return true;
			}
			return recursiveSolve(nextRow, nextCol);
        }
	}

	@Override
	public boolean isLegal(int row, int col, int nbr) 
	{
		for (int i = 0; i < 9; i++)
		{
	        if (sudokuBoard[row][i] == nbr) 
			{
	            return false;
	        }
	    }

	    for (int i = 0; i < 9; i++) 
		{
	        if (sudokuBoard[i][col] == nbr) 
			{
	            return false;
	        }
	    }

	    int kvadrantRad = row - row % 3;
	    int kvadrantKol = col - col % 3;

	    for (int i = 0; i < 3; i++)
		{
	        for (int j = 0; j < 3; j++)
			{
	            if (sudokuBoard[kvadrantRad + i][kvadrantKol + j] == nbr)
				{
	                return false;
	            }
	        }
	    }
	    return true;
	}

	@Override
	public int get(int row, int col) 
	{
		int getValue = sudokuBoard[row] [col];
		
		return getValue;
	}

	@Override
	public void set(int row, int col, int nbr) 
	{
		sudokuBoard [row][col] = nbr;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < sudokuBoard.length; i++) 
		{
			for (int j = 0; j < sudokuBoard.length; j++) 
			{
				sudokuBoard[i][j] = 0;
			}
		}
	}
}