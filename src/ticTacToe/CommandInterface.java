package ticTacToe;

import java.util.Scanner;

public class CommandInterface {
	/**
	 * Constructor
	 */
	public CommandInterface()
	{
		
	}
	
	/**
	 * Used to prompt user if they would life to be X or O
	 * @return Integer value 1 or 2
	 */
	public int choosePlayerChar()
	{
		int Player = 0;
		String Input = " ";
		Scanner in = new Scanner(System.in);
		
		// Prompt user for input to until valid input is given
		while(Player == 0)
		{
			
			try
			{
				// Prompt user for input
				System.out.print("Would you like to be X or O: ");
				Input = in.next();
				
				// Check if user selected X 
				if(Input.equals("X") || Input.equals("x"))
				{
					Player = 1;
				}
				
				// Check if user selected O
				else if(Input.equals("O") || Input.equals("o"))
				{
					Player = 2;
				}
				
				// Invalid input
				else 
				{
					System.out.println("Invalid input");
				}
			}
			
			// Ask again if exception thrown
			catch(Exception e)
			{
				in.nextLine();
				System.out.println("Invalid input");
				continue;
			}
		}
		return Player;
	}
	
	/**
	 * Prompt user if they want to go first or second
	 * @return Integer value of 1 or 2
	 */
	public int chooseOrder()
	{
		int Order = 0;
		int Input = 0;
		Scanner in = new Scanner(System.in);
		
		// Prompt user for input till value input is valid
		while(Order == 0)
		{
			try
			{
				//Prompt user if they would like to go first or second
				System.out.print("Would you like to be 1st or 2nd(1 or 2): ");
				Input = in.nextInt();
				
				// Check if user wanted to go first
				if(Input == 1)
				{
					Order = 1;
				}
				
				// Check if user wanted to go second
				else if(Input == 2)
				{
					Order = 2;
				}
				
				// invalid input from user
				else 
				{
					System.out.println("Invalid input");
				}
			}
			
			// Ask again if exception thrown
			catch(Exception e)
			{
				in.nextLine();
				System.out.println("Invalid input");
				continue;
			}
		}
		return Order;
	}

	/**
	 * Handles Human players turn
	 * @param game Keeps track of the current game state
	 */
	public void handleHumanPlayer(GameState game)
	{
		int row = -1;
		int col = -1;
		
		// Request row and column until valid arguments are given
		while(true)
		{
			//Prompt user for row and col
			row = promptForRow();
			col = promptForCol();
			// check if row and column are acceptable values and to make sure its a valid move
			if(!checkForAcceptableValues(game, row, col))
			{
				continue;
			}

			break;
		}
		
		// Make the move with the valid move
		game.makeMove(row, col);
		
	}
	
	/**
	 * Checks if move is with in the range of the board and has not been previously played
	 * @param game Keeps track of the game state
	 * @param row User input for row
	 * @param col User input for column
	 * @return A boolean value that represents the validity of the move
	 */
	public boolean checkForAcceptableValues(GameState game, int row, int col)
	{
		
		// check if row and column are acceptable values
		if(row < 0 || row > 2 || col < 0 || col > 2)
		{
			System.out.println("INVALID INPUT:\ncol and row values must be 0-2");
			return false;
		}
					
		// Check that the move is valid
		if(!game.isMoveValid(row, col))
		{
			System.out.println("That space is taken already");
			return false;
		}
		return true;
	}
	
	/**
	 * Used to prompt the user for row value
	 * @return Integer displaying the row value 
	 */
	public int promptForRow()
	{
		int row = -1;
		Scanner in = new Scanner(System.in);
		
		// Prompt user for row
		System.out.print("\nPlease select row(0-2): ");
		while(true)			
		{	
			// Get input from user if integer
			try
			{
				row = in.nextInt();
			}
			
			// Ask again if exception thrown
			catch(Exception e)
			{
				in.nextLine();
				System.out.println("Invalid input");
				continue;
			}
			break;
		}
		return row;
	}
	
	/**
	 * Used to prompt the user for a column
	 * @return Integer displaying the column value
	 */
	public int promptForCol()
	{
		int col = -1;
		Scanner in = new Scanner(System.in);
		
		// Prompt user for column
		System.out.print("Please select col(0-2): ");
		while(true)			
		{	
			// Get input from user if integer
			try
			{
				col = in.nextInt();
			}
			
			// Ask again if exception thrown
			catch(Exception e)
			{
				in.nextLine();
				System.out.println("Invalid input");
				continue;
			}
			break;
		}
		return col;
	}
	
	/**
	 * Prints game board and displays winner of game
	 * @param game Keeps track of the current game state
	 */
	public void handleEndGame(GameState game)
	{
		// Prints the board
		System.out.println();
		game.printBoard();
		
		// Prints game over
		System.out.println("\n\t GameOver");
		
		// Displays winner
		System.out.println("\tWinner = " + game.getWinner());
	}

	/**
	 * Handle choosing computer move based off current game state
	 * @param game Keeps track of the current game state
	 */
	public Integer[] handleComputerMove(GameState game)
	{
		// Computer move
		Integer[] computerChoice = new Integer[2];
		
		// ComputerStrategy class for deciding move
		ComputerStrategy  com = new ComputerStrategy();
		
		// Make move using game state and ComputerStrategy
		computerChoice = com.makeMove(game);
		
		// Make the move for computer on game state
		game.makeMove(computerChoice[0], computerChoice[1]);
		
		return computerChoice;
		
	}
}
