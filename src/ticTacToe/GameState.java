package ticTacToe;

public class GameState {
	// int value 0 = blank
		// int value 1 = "X"
		// int value 2 = "O"
		private int playerX;
		private int playerO;
		
		// keeps track of who's turn it is 
		private int playerTurn;
		
		// Matrix of 0's, 1's, and 2's to keep track of entries on board
		private int[][] gameBoard;
		
		// String version of for X, O and blank space
		private final String  x = "X";
		private final String  o = "O";
		private final String blank = " ";
		
		// For storing the winner of the game
		private String winner;
		
		private boolean isHumanPlayerTurn; 
		
		/**
		 * Constructor for GameState
		 */
		public GameState(int player, int order)
		{
			// Sets player values
			playerX = 1;
			playerO = 2;
			
			// to tell who goes first
			if(order == 1 && player == 1)
			{
				isHumanPlayerTurn = true;
				
				// Sets player X to go to first
				playerTurn = playerX;
				
			}
			// to tell who goes first
			else if(order == 1 && player == 2)
			{
				isHumanPlayerTurn = true;
				
				// Sets player O to go to first
				playerTurn = playerO;
			}
			
			// to tell who goes first
			else if(order == 2 && player == 1)
			{
				isHumanPlayerTurn = false;
				// Sets player X to go to first
				playerTurn = playerO;
			}
			// to tell who goes first
			else
			{
				isHumanPlayerTurn = false;
				// Sets player O to go to first
				playerTurn = playerX;
			}
			
			// Sets winner to default of tie value
			winner = "Tie";
			
			// Initialize board
			gameBoard = new int[3][3];
			
			// Sets all values on board to 0
			for(int row = 0; row < 3; row++)
			{
				for(int col = 0; col < 3; col++)
				{
					gameBoard[row][col] = 0;
				}
			}
		
		}
		
		/**
		 * returns a boolean value to tell is its human players turn
		 * @return True if its human players turn
		 */
		public boolean isHumanPlayerTurn()
		{
			return isHumanPlayerTurn;
		}
		
		/**
		 * Prints current players turn and current board
		 */
		public void printBoard()
		{
			// Print players turn
			System.out.printf("      Player %s's Turn\n", getCurrentPlayer());
			
			// Print board to console
			int i;
			for(i= 0; i < 2; i++)
			{
				System.out.printf("\t %s | %s | %s\n", convertPlayerToString(i,0),convertPlayerToString(i,1),convertPlayerToString(i,2));
				System.out.println("\t-----------");
			}
			System.out.printf("\t %s | %s | %s\n", convertPlayerToString(i,0),convertPlayerToString(i,1),convertPlayerToString(i,2));
		}
		
		/**
		 * Accessor method to retrieve current players turn
		 * @return Current Player
		 */
		public String getCurrentPlayer()
		{
			// Retrieve current player and return back to caller
			String player= "X";
			if(playerTurn == 2)
			{
				player = "O";
			}
			return player;
		}
		
		/**
		 * This function returns the integer value of the player who's turn it is
		 * @return Will return 1 if X and will return 2 if O
		 */
		public int getCurrentPlayerInt()
		{
			// Retrieve current player and return back to caller
			return playerTurn;
		}
		
		/**
		 * This function returns the integer value of the opposite player who's turn it is
		 * @return Will return 1 if X and will return 2 if O
		 */
		public int getOtherPlayerInt()
		{
			// Retrieve current player and return opposite player back to caller
			if(playerTurn == 1)
			{
				return 2;
			}
			else
			{
				return 1;
			}
		}
		
		/**
		 * Used to retrieve X, O or a blank space from a requested row column index
		 * @param row Index value 0-2
		 * @param col Index value 0-2
		 * @return Either X, O or a blank space
		 */
		public String convertPlayerToString(int row, int col)
		{
			// set player to  " "
			String player = blank;
			
			// Check if index of row and column equals X. If so return "X"
			if(gameBoard[row][col] == 1)
			{
				return x;
			}
			
			// Check if index of row and column equals O. If so return "X"
			else if(gameBoard[row][col] == 2)
			{
				return o;
			}
			return player;
			
		}
		
		/**
		 * Used to change current player
		 */
		public void changePlayerTurn()
		{
			// Change to player O
			if(playerTurn == playerX)
			{
				playerTurn = playerO;
			}
			// Change to player X
			else
			{
				playerTurn = playerX;
			}
			changeIsHumanPlayerTurn();
		}
		
		/**
		 * This is a mutator function that changes wheather its the human player turn or computer turn
		 */
		public void changeIsHumanPlayerTurn()
		{
			// If its human player turn change to computers turn
			if(isHumanPlayerTurn)
			{
				isHumanPlayerTurn = false;
			}
			
			// If computers turn change to human players turn
			else
			{
				isHumanPlayerTurn = true;
			}
				
		}
		
		/**
		 * Mutator Method to change a specific piece on the board
		 * @param row Row Index
		 * @param col Col Index
		 * @return A boolean of true if move was valid move
		 */
		public boolean makeMove(int row, int col)
		{
			// Check if spot is blank
			if(isMoveValid(row,col))
			{
				// update place on board
				gameBoard[row][col] = playerTurn;
				return true;
			}
			else
			{
				// Move not valid 
				return false;
			}
		}
		
		/**
		 * Used to check if a specific move is valid
		 * @param row Row index
		 * @param col Column index
		 * @return True if move is valid
		 */
		public boolean isMoveValid(int row, int col)
		{
			// return if move is valid at specific index
			return gameBoard[row][col] == 0;
		}
		
		/**
		 * To check if either player has won the game
		 * @return True if game is over
		 */
		public boolean isGameOver()
		{
			// Check top row horizontally
			if(gameBoard[0][0] == playerTurn && gameBoard[0][1] == playerTurn && gameBoard[0][2] == playerTurn)
			{
				setWinner();
				return true;
			}
			
			// Check middle row horizontally
			else if(gameBoard[1][0] == playerTurn && gameBoard[1][1] == playerTurn && gameBoard[1][2] == playerTurn)
			{
				setWinner();
				return true;
			}
			
			// Check bottom row horizontally
			else if(gameBoard[2][0] == playerTurn && gameBoard[2][1] == playerTurn && gameBoard[2][2] == playerTurn)
			{
				setWinner();
				return true;
			}
			
			// Check left most column vertically
			else if(gameBoard[0][0] == playerTurn && gameBoard[1][0] == playerTurn && gameBoard[2][0] == playerTurn)
			{
				setWinner();
				return true;
			}
					
			// Check middle row vertically
			else if(gameBoard[0][1] == playerTurn && gameBoard[1][1] == playerTurn && gameBoard[2][1] == playerTurn)
			{
				setWinner();
				return true;
			}
			
			// Check right most row vertically
			else if(gameBoard[0][2] == playerTurn && gameBoard[1][2] == playerTurn && gameBoard[2][2] == playerTurn)
			{
				setWinner();
				return true;
			}
			// Check left top to right bottom diagonal
			else if(gameBoard[0][0] == playerTurn && gameBoard[1][1] == playerTurn && gameBoard[2][2] == playerTurn)
			{
				setWinner();
				return true;
			}
							
			// Check right top to left bottom diagonal
			else if(gameBoard[0][2] == playerTurn && gameBoard[1][1] == playerTurn && gameBoard[2][0] == playerTurn)
			{
				setWinner();
				return true;
			}
			
			// returns true if no moves are left
			return checkNoMoreMoves();	
		}
		
		/**
		 * Used to check if no more moves are available
		 * @return True if no moves are left
		 */
		public boolean checkNoMoreMoves()
		{
			// Check if any index is still blank
			for(int i=0; i < 3; i++)
			{
				for(int j=0; j < 3; j++)
				{
					// If index is black return false
					if(gameBoard[i][j] == 0)
					{
						return false;
					}
				}
			}
			// No moves available
			return true;
		}
		/**
		 * returns the index value at row and col index
		 * @param row Integer value from 0 - 2
		 * @param col Integer value from 0 - 2 
		 * @return Integer value at requested row and col index
		 */
		public int getIndexValue(int row, int col)
		{
			return gameBoard[row][col];
		}
		
		/**
		 * Sets the winner variable
		 */
		public void setWinner()
		{
			// If PlayersTurn = X set winner to X
			if(playerTurn == 1)
			{
				winner = "X";
			}
			// If PlayersTurn = O set winner to O
			else
			{
				winner = "O";
			}
			
		}
		
		/**
		 * Get winner of the game
		 * @return A string of O, X or tie
		 */
		public String getWinner()
		{
			return winner;
		}

}
