package SudokuTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Sudoku.SudokuGame;

class SudokuGameTester
{
	private SudokuGame sudoku;
	private int [][] sudokuBoard;

	@BeforeEach
	void setUp() throws Exception 
	{
		sudoku = new SudokuGame();
		sudokuBoard = new int [9][9];
	}

	@AfterEach
	void tearDown() throws Exception
	{
		sudoku.clear();
		sudokuBoard= null;
	}

	@Test
	void testKonstruktor() 
	{
		for (int i = 0; i < sudokuBoard.length; i++) 
		{
			for (int j = 0; j < sudokuBoard[0].length; j++) 
			{
				assertEquals(0, sudoku.get(i, j));
			}
		}
	}
	
	@Test
	void testSetBoard()
	{
		sudokuBoard [1][1] = 1;
		sudokuBoard [4][5] = 2;
		sudokuBoard [2][2] = 3;
		
		sudoku.setBoard(sudokuBoard);
		
		for (int i = 0; i < sudokuBoard.length; i++) 
		{
			for (int j = 0; j < sudokuBoard[i].length; j++) 
			{
				assertEquals(sudokuBoard[i][j], sudoku.get(i, j));
				
			}
		}	
	}

	@Test
	void testGetBoard()
	{
		sudokuBoard [1][1] = 1;
		sudokuBoard [4][5] = 2;
		sudokuBoard [2][2] = 3;
		
		sudoku.setBoard(sudokuBoard);
		
		for (int i = 0; i < sudokuBoard.length; i++) 
		{
			for (int j = 0; j < sudokuBoard[i].length; j++) 
			{
				assertEquals(sudokuBoard[i][j], sudoku.get(i, j));
			}
		}	
	}
	
	@Test
	void testSolve() 
	{
		assertTrue(sudoku.solve());
		//sudoku.clear();
		
		sudoku.set(0, 0, 1);
		assertTrue(sudoku.solve());
		
		sudoku.set(0, 1, 1);
		assertFalse(sudoku.solve());
	}
	
	@Test
	void testisLegal() 
	{
		assertTrue(sudoku.isLegal(0, 0, 1));
		sudoku.set(0, 0, 1);
		
		assertFalse(sudoku.isLegal(0, 1, 1)); 
		assertFalse(sudoku.isLegal(2, 0, 1));
		assertFalse(sudoku.isLegal(2, 2, 1));

		assertTrue(sudoku.solve());	
	}
	
	@Test
	void testGet() 
	{
		sudokuBoard[0][1] = 2;
		sudokuBoard[1][2] = 4;
		sudoku.setBoard(sudokuBoard);
		
		for (int i = 0; i < sudokuBoard.length; i++) 
		{
			for (int j = 0; j < sudokuBoard[i].length; j++) 
			{
				assertEquals(sudokuBoard[i][j], sudoku.get(i, j));
			}
		}
	}
	
	@Test
	void test() 
	{
		assertTrue(sudoku.solve());
	}
	
	@Test
	void testLösTom()
	{
		assertTrue(sudoku.solve());
	}

	@Test
	void testOlöslig()
	{
		int[][] board = 
		{
		    {1, 2, 3, 0, 0, 0, 0, 0, 0},
		    {4, 5, 6, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 7, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 0, 0, 0},
		};
		
		sudoku.setBoard(board);
		
		assertFalse(sudoku.solve());
	}

	@Test
	void testFigur1() 
	{
		int[][] board = 
		{
			{0, 0, 8, 0, 0, 9, 0, 6, 2},
		    {0, 0, 0, 0, 0, 0, 0, 0, 5},
		    {1, 0, 2, 5, 0, 0, 0, 0, 0},
		    {0, 0, 0, 2, 1, 0, 0, 9, 0},
		    {0, 5, 0, 0, 0, 0, 6, 0, 0},
		    {6, 0, 0, 0, 0, 0, 0, 2, 8},
		    {4, 1, 0, 6, 0, 8, 0, 0, 0},
		    {8, 6, 0, 0, 3, 0, 1, 0, 0},
		    {0, 0, 0, 0, 0, 0, 4, 0, 0},
		};
		
		sudoku.setBoard(board);
		assertTrue(sudoku.solve());
	}

	@Test
	void testClear() 
	{
		sudoku.set(0, 0, 1);		
		sudoku.set(3, 4, 2);
		sudoku.set(5, 0, 3);

		assertTrue(sudoku.get(0,0) == 1);
		assertTrue(sudoku.get(3,4) == 2);
		assertTrue(sudoku.get(5,0) == 3);
		
		sudoku.clear();
		
		for (int i = 0; i < sudokuBoard.length; i++) 
		{
			for (int j = 0; j < sudokuBoard[i].length; j++) 
			{
				assertEquals(sudoku.get(i, j), 0);
				
			}
		}
	}
}