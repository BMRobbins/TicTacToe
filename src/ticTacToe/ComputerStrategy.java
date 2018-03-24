package ticTacToe;

public class ComputerStrategy {
	// scoring matrix used to decide the value of each move
		private int[][]scoreMatrix;
		
		// Used to keep track of best move for computer 
		private Integer[] choice;
		
		/**
		 * Constructor
		 */
		public ComputerStrategy()
		{
			// Initialize  scoreMatrix
			scoreMatrix = new int[3][3];
			// Initialize best move
			choice = new Integer[2];
			
			// Set best move to 0, 0 
			choice[0] = 0;
			choice[1] = 0;
			
			// Set all values in score matrix to 0
			for(int row = 0; row < 3; row++)
			{
				for(int col = 0; col < 3; col++)
				{
					scoreMatrix[row][col] = 0;
				}
			}
		}
		
		/**
		 * Used to decide best move for computer
		 * @param game GameState to keep track of gameboard values
		 * @return Integer array of length 2 that keeps track of computer move
		 */
		public Integer[] makeMove(GameState game)
		{
			// check if human player can win with one turn if so block human
			if(!checkForAutoWin(game))
			{
				// update scoring matrix for all offensive moves
				updateOffensiveMoves(game);
				
				// update scoring matrix for all defensive moves
				updateDefensiveMoves(game);
				
				// get the best move form the scoring matrix
				findBestChoice(game);
				
				// double check human is putting computer in a position to loss later in game
				checkForTrickMove(game);
			}
			
			// return best move
			return choice;
			
		}
		
		/**
		 * / check if human player can win with one turn if so block human
		 * @param game Representation of current gameBoard
		 * @return A boolean value to  tell weather human can win in one move
		 */
		public boolean checkForAutoWin(GameState game)
		{
			// get human players integer value
			int opponent = game.getOtherPlayerInt();
			
			// Check for human player win from top left to bottom right with a win at 2,2
			if(opponent == game.getIndexValue(0, 0) && opponent == game.getIndexValue(1, 1) && game.getIndexValue(2, 2) == 0)
			{
				choice[0] = 2;
				choice[1] = 2;
				return true;
			}
			
			// Check for human player win from top left to bottom right with a win at 0,0
			else if(opponent == game.getIndexValue(2, 2) && opponent == game.getIndexValue(1, 1) && game.getIndexValue(0, 0) == 0)
			{
				choice[0] = 0;
				choice[1] = 0;
				return true;
			}
			
			
			// Check for human player win from top left to bottom right with a win at 1,1
			if(opponent == game.getIndexValue(0, 0) && opponent == game.getIndexValue(1, 2) && game.getIndexValue(1, 1) == 0)
			{
				choice[0] = 1;
				choice[1] = 1;
				return true;
			}
			
			// Check for human player win from top left to top right with a win at 0,2
			if(opponent == game.getIndexValue(0, 0) && opponent == game.getIndexValue(0, 1) && game.getIndexValue(0, 2) == 0)
			{
				choice[0] = 0;
				choice[1] = 2;
				return true;
			}
			
			// Check for human player win from top left to top right with a win at 0,1
			if(opponent == game.getIndexValue(0, 0) && opponent == game.getIndexValue(0, 2) && game.getIndexValue(0, 1) == 0)
			{
				choice[0] = 0;
				choice[1] = 1;
				return true;
			}
			
			// Check for human player win from top left to top right with a win at 0,0
			if(opponent == game.getIndexValue(0, 1) && opponent == game.getIndexValue(0, 2) && game.getIndexValue(0, 0) == 0)
			{
				choice[0] = 0;
				choice[1] = 0;
				return true;
			}
			
			// Check for human player win from top left to top right with a win at 0,1
			if(opponent == game.getIndexValue(0, 0) && opponent == game.getIndexValue(0, 2) && game.getIndexValue(0, 1) == 0)
			{
				choice[0] = 0;
				choice[1] = 1;
				return true;
			}
			
			// Check for human player win from middle left to middle right with a win at 1,2
			if(opponent == game.getIndexValue(1, 0) && opponent == game.getIndexValue(1, 1) && game.getIndexValue(1, 2) == 0)
			{
				choice[0] = 1;
				choice[1] = 2;
				return true;
			}
			
			// Check for human player win from middle left to middle right with a win at 1,0
			if(opponent == game.getIndexValue(1, 1) && opponent == game.getIndexValue(1, 2) && game.getIndexValue(1, 0) == 0)
			{
				choice[0] = 1;
				choice[1] = 0;
				return true;
			}
			
			// Check for human player win from middle left to middle right with a win at 1,1
			if(opponent == game.getIndexValue(1, 0) && opponent == game.getIndexValue(1, 2) && game.getIndexValue(1, 1) == 0)
			{
				choice[0] = 1;
				choice[1] = 1;
				return true;
			}
			
			// Check for human player win from bottom left to bottom right with a win at 2,2
			if(opponent == game.getIndexValue(2, 0) && opponent == game.getIndexValue(2, 1) && game.getIndexValue(2, 2) == 0)
			{
				choice[0] = 2;
				choice[1] = 2;
				return true;
			}
			
			// Check for human player win from bottom left to bottom right with a win at 2,0
			if(opponent == game.getIndexValue(2, 1) && opponent == game.getIndexValue(2, 2) && game.getIndexValue(2, 0) == 0)
			{
				choice[0] = 2;
				choice[1] = 0;
				return true;
			}
			
			// Check for human player win from bottom left to bottom right with a win at 2,1
			if(opponent == game.getIndexValue(2, 0) && opponent == game.getIndexValue(2, 2) && game.getIndexValue(2, 1) == 0)
			{
				choice[0] = 2;
				choice[1] = 1;
				return true;
			}
			
			// Check for human player win from bottom left to top right with a win at 0,2
			if(opponent == game.getIndexValue(2, 0) && opponent == game.getIndexValue(1, 1) && game.getIndexValue(0, 2) == 0)
			{
				choice[0] = 0;
				choice[1] = 2;
				return true;
			}
			
			// Check for human player win from bottom left to top right with a win at 1,1
			if(opponent == game.getIndexValue(2, 0) && opponent == game.getIndexValue(0, 2) && game.getIndexValue(1, 1) == 0)
			{
				choice[0] = 1;
				choice[1] = 1;
				return true;
			}
			
			// Check for human player win from bottom left to top right with a win at 2,0
			if(opponent == game.getIndexValue(1, 1) && opponent == game.getIndexValue(0, 2) && game.getIndexValue(2, 0) == 0)
			{
				choice[0] = 2;
				choice[1] = 0;
				return true;
			}
			
			// Check for human player win from top left to bottom left with a win at 2,0
			if(opponent == game.getIndexValue(0, 0) && opponent == game.getIndexValue(1, 0) && game.getIndexValue(2, 0) == 0)
			{
				choice[0] = 2;
				choice[1] = 0;
				return true;
			}
			
			// Check for human player win from top left to bottom left with a win at 1,0
			if(opponent == game.getIndexValue(0, 0) && opponent == game.getIndexValue(2, 0) && game.getIndexValue(1, 0) == 0)
			{
				choice[0] = 1;
				choice[1] = 0;
				return true;
			}
			
			// Check for human player win from top left to bottom left with a win at 0,0
			if(opponent == game.getIndexValue(1, 0) && opponent == game.getIndexValue(2, 0) && game.getIndexValue(0, 0) == 0)
			{
				choice[0] = 0;
				choice[1] = 0;
				return true;
			}
			
			// Check for human player win from top middle to bottom middle with a win at 2,1
			if(opponent == game.getIndexValue(0, 1) && opponent == game.getIndexValue(1, 1) && game.getIndexValue(2, 1) == 0)
			{
				choice[0] = 2;
				choice[1] = 1;
				return true;
			}
			
			// Check for human player win from top middle to bottom middle with a win at 1,1
			if(opponent == game.getIndexValue(0, 1) && opponent == game.getIndexValue(2, 1) && game.getIndexValue(1, 1) == 0)
			{
				choice[0] = 1;
				choice[1] = 1;
				return true;
			}
			
			// Check for human player win from top middle to bottom middle with a win at 0,1
			if(opponent == game.getIndexValue(1, 1) && opponent == game.getIndexValue(2, 1) && game.getIndexValue(0, 1) == 0)
			{
				choice[0] = 0;
				choice[1] = 1;
				return true;
			}
			
			// Check for human player win from  top right to bottom right with a win at 2,2
			if(opponent == game.getIndexValue(0, 2) && opponent == game.getIndexValue(1, 2) && game.getIndexValue(2, 2) == 0)
			{
				choice[0] = 2;
				choice[1] = 2;
				return true;
			}
			
			// Check for human player win from  top right to bottom right with a win at 1,2
			if(opponent == game.getIndexValue(0, 2) && opponent == game.getIndexValue(2, 2) && game.getIndexValue(1, 2) == 0)
			{
				choice[0] = 1;
				choice[1] = 2;
				return true;
			}
			
			// Check for human player win from  top right to bottom right with a win at 0,2
			if(opponent == game.getIndexValue(1, 2) && opponent == game.getIndexValue(2, 2) && game.getIndexValue(0, 2) == 0)
			{
				choice[0] = 0;
				choice[1] = 2;
				return true;
			}
			
			// return false win no game winning move for human exist
			return false;
		}
		
		/**
		 * Update scoring matrix for each index's offensive moves
		 * @param game
		 */
		public void updateOffensiveMoves(GameState game)
		{
			// Iterate through each space on the score matrix
			for(int row = 0; row < 3; row++)
			{		
				for(int col = 0; col < 3; col++)
				{
					// Update for row col index
					checkOffenseMove(game, row, col);
				}
			}
		}
		
		/**
		 * Update scoring matrix for each index's defensive moves
		 * @param game 
		 */
		public void updateDefensiveMoves(GameState game)
		{
			// Iterate through each space on the score matrix
			for(int row = 0; row < 3; row++)
			{		
				for(int col = 0; col < 3; col++)
				{
					// Update for row col index
					checkDefensiveMove(game, row, col);
				}
			}
		}
		
		/**
		 * Check for offensive moves for specific index on gameBoard
		 * @param game Representation of gameboard
		 * @param row Integer value for row 0-2
		 * @param col Integer value for col 0-2
		 */
		public void checkOffenseMove(GameState game, int row, int col)
		{
			// Update scoring matrix at 0,0
			if(row == 0 && col == 0)
			{
				scoreMatrix[0][0] = evalIndex00(game, game.getCurrentPlayerInt());
			}
			
			// Update scoring matrix at 0,1
			else if(row == 0 && col == 1)
			{
				scoreMatrix[0][1] =evalIndex01(game, game.getCurrentPlayerInt());
			}
			
			// Update scoring matrix at 0,2
			else if(row == 0 && col == 2)
			{
				scoreMatrix[0][2] =evalIndex02(game, game.getCurrentPlayerInt());
			}
			
			// Update scoring matrix at 1,0
			else if(row == 1 && col == 0)
			{
				scoreMatrix[1][0] =evalIndex10(game, game.getCurrentPlayerInt());
			}
			
			// Update scoring matrix at 1,1
			else if(row == 1 && col == 1)
			{
				scoreMatrix[1][1] =evalIndex11(game, game.getCurrentPlayerInt());
			}
			
			// Update scoring matrix at 1,2
			else if(row == 1 && col == 2)
			{
				scoreMatrix[1][2] =evalIndex12(game, game.getCurrentPlayerInt());
			}
			
			// Update scoring matrix at 2,0
			else if(row == 2 && col == 0)
			{
				scoreMatrix[2][0] =evalIndex20(game, game.getCurrentPlayerInt());
			}
			
			// Update scoring matrix at 2,1
			else if(row == 2 && col == 1)
			{
				scoreMatrix[2][1] =evalIndex21(game, game.getCurrentPlayerInt());
			}
			
			// Update scoring matrix at 2,2
			else if(row == 2 && col == 2)
			{
				scoreMatrix[2][2] =evalIndex22(game, game.getCurrentPlayerInt());
			}
		}
		
		/**
		 * Check for defensive moves for specific index on gameBoard
		 * @param game Representation of gameboard
		 * @param row Integer value for row 0-2
		 * @param col Integer value for col 0-2
		 */
		public void checkDefensiveMove(GameState game, int row, int col)
		{
			// Update scoring matrix at 0,0
			if(row == 0 && col == 0)
			{
				scoreMatrix[0][0] += evalIndex00(game, game.getOtherPlayerInt());
			}
			
			// Update scoring matrix at 0,1
			else if(row == 0 && col == 1)
			{
				scoreMatrix[0][1] += evalIndex01(game, game.getOtherPlayerInt());
			}
			
			// Update scoring matrix at 0,2
			else if(row == 0 && col == 2)
			{
				scoreMatrix[0][2] += evalIndex02(game, game.getOtherPlayerInt());
			}
			
			// Update scoring matrix at 1,0
			else if(row == 1 && col == 0)
			{
				scoreMatrix[1][0] += evalIndex10(game, game.getOtherPlayerInt());
			}
			
			// Update scoring matrix at 1,1
			else if(row == 1 && col == 1)
			{
				scoreMatrix[1][1] += evalIndex11(game, game.getOtherPlayerInt());
			}
			
			// Update scoring matrix at 1,2
			else if(row == 1 && col == 2)
			{
				scoreMatrix[1][2] += evalIndex12(game, game.getOtherPlayerInt());
			}
			
			// Update scoring matrix at 2,0
			else if(row == 2 && col == 0)
			{
				scoreMatrix[2][0] += evalIndex20(game, game.getOtherPlayerInt());
			}
			
			// Update scoring matrix at 2,1
			else if(row == 2 && col == 1)
			{
				scoreMatrix[2][1] += evalIndex21(game, game.getOtherPlayerInt());
			}
			
			// Update scoring matrix at 2,2
			else if(row == 2 && col == 2)
			{
				scoreMatrix[2][2] += evalIndex22(game, game.getOtherPlayerInt());
			}
		}
		
		
		/**
		 * Used to get the score for index 0,0 for offense or defense
		 * @param game Representation of game board
		 * @param player Either 1,2 for X or O
		 * @return Integer value for specified index
		 */
		public int evalIndex00(GameState game, int player)
		{
			int counter = 0;
			
			// Check for play at requested index
			if(game.getIndexValue(0, 0) !=  0)
			{
				return -1;
			}
			
			// Check for possible score increment from top left to top right
			if(game.getIndexValue(0, 1) == 0 || game.getIndexValue(0, 1) == player)
			{
				if(game.getIndexValue(0, 2) == 0 || game.getIndexValue(0, 2) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top left to bottom left
			if(game.getIndexValue(1, 0) == 0 || game.getIndexValue(1, 0) == player)
			{
				if(game.getIndexValue(2, 0) == 0 || game.getIndexValue(2, 0) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top left to bottom right
			if(game.getIndexValue(1, 1) == 0 || game.getIndexValue(1, 1) == player)
			{
				if(game.getIndexValue(2, 2) == 0 || game.getIndexValue(2, 2) == player)
				{
					counter++;
				}
			}
			return counter;
			
		}
		
		/**
		 * Used to get the score for index 0,1 for offense or defense
		 * @param game Representation of game board
		 * @param player Either 1,2 for X or O
		 * @return Integer value for specified index
		 */
		public int evalIndex01(GameState game, int player)
		{
			int counter = 0;
			
			// Check for play at requested index
			if(game.getIndexValue(0, 1) !=  0)
			{
				return -1;
			}
			
			// Check for possible score increment from top left to top right
			if(game.getIndexValue(0, 0) == 0 || game.getIndexValue(0, 0) == player)
			{
				if(game.getIndexValue(0, 2) == 0 || game.getIndexValue(0, 2) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top middle to bottom middle
			if(game.getIndexValue(1, 1) == 0 || game.getIndexValue(1, 1) == player)
			{
				if(game.getIndexValue(2, 1) == 0 || game.getIndexValue(2, 1) == player)
				{
					counter++;
				}
			}
			return counter;
		}
		
		
		/**
		 * Used to get the score for index 0,2 for offense or defense
		 * @param game Representation of game board
		 * @param player Either 1,2 for X or O
		 * @return Integer value for specified index
		 */
		public int evalIndex02(GameState game, int player)
		{
			int counter = 0;
			
			// Check for play at requested index
			if(game.getIndexValue(0, 2) !=  0)
			{
				return -1;
			}
			
			// Check for possible score increment from top left to top right
			if(game.getIndexValue(0, 0) == 0 || game.getIndexValue(0, 0) == player)
			{
				if(game.getIndexValue(0, 1) == 0 || game.getIndexValue(0, 1) == player)
				{
					counter++;
				}
			}
			
			
			// Check for possible score increment from top right to bottom left
			if(game.getIndexValue(1, 1) == 0 || game.getIndexValue(1, 1) == player)
			{
				if(game.getIndexValue(2, 0) == 0 || game.getIndexValue(2, 0) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top right to bottom right
			if(game.getIndexValue(1, 2) == 0 || game.getIndexValue(1, 2) == player)
			{
				if(game.getIndexValue(2, 2) == 0 || game.getIndexValue(2, 2) == player)
				{
					counter++;
				}
			}
			return counter;
		}
		
		/**
		 * Used to get the score for index 1,0 for offense or defense
		 * @param game Representation of game board
		 * @param player Either 1,2 for X or O
		 * @return Integer value for specified index
		 */
		public int evalIndex10(GameState game, int player)
		{
			int counter = 0;
			
			// Check for play at requested index
			if(game.getIndexValue(1, 0) !=  0)
			{
				return -1;
			}
			
			// Check for possible score increment from middle left to middle right
			if(game.getIndexValue(0, 0) == 0 || game.getIndexValue(0, 0) == player)
			{
				if(game.getIndexValue(2, 0) == 0 || game.getIndexValue(2, 0) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top left to bottom left
			if(game.getIndexValue(1, 1) == 0 || game.getIndexValue(1, 1) == player)
			{
				if(game.getIndexValue(1, 2) == 0 || game.getIndexValue(1, 2) == player)
				{
					counter++;
				}
			}
			return counter;
		}
		
		/**
		 * Used to get the score for index 1,1 for offense or defense
		 * @param game Representation of game board
		 * @param player Either 1,2 for X or O
		 * @return Integer value for specified index
		 */
		public int evalIndex11(GameState game, int player)
		{
			int counter = 0;
			
			// Check for play at requested index
			if(game.getIndexValue(1, 1) !=  0)
			{
				return -1;
			}
			
			// Check for possible score increment from top left to bottom right
			if(game.getIndexValue(0, 0) == 0 || game.getIndexValue(0, 0) == player)
			{
				if(game.getIndexValue(2, 2) == 0 || game.getIndexValue(2, 2) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top middle to bottom middle
			if(game.getIndexValue(0, 1) == 0 || game.getIndexValue(0, 1) == player)
			{
				if(game.getIndexValue(2, 1) == 0 || game.getIndexValue(2, 1) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top right to bottom left
			if(game.getIndexValue(0, 2) == 0 || game.getIndexValue(0, 2) == player)
			{
				if(game.getIndexValue(2, 0) == 0 || game.getIndexValue(2, 0) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from middle left to middle right
			if(game.getIndexValue(1, 0) == 0 || game.getIndexValue(1, 0) == player)
			{
				if(game.getIndexValue(1, 2) == 0 || game.getIndexValue(1, 2) == player)
				{
					counter++;
				}
			}
			return counter;
		}
		
		/**
		 * Used to get the score for index 1,2 for offense or defense
		 * @param game Representation of game board
		 * @param player Either 1,2 for X or O
		 * @return Integer value for specified index
		 */
		public int evalIndex12(GameState game, int player)
		{
			int counter = 0;
			
			// Check for play at requested index
			if(game.getIndexValue(1, 2) !=  0)
			{
				return -1;
			}
			
			// Check for possible score increment from middle left to middle right
			if(game.getIndexValue(1, 0) == 0 || game.getIndexValue(1, 0) == player)
			{
				if(game.getIndexValue(1, 1) == 0 || game.getIndexValue(1, 1) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top right to bottom right
			if(game.getIndexValue(0, 2) == 0 || game.getIndexValue(0, 2) == player)
			{
				if(game.getIndexValue(2, 2) == 0 || game.getIndexValue(2, 2) == player)
				{
					counter++;
				}
			}
			
			return counter;
		}
		
		/**
		 * Used to get the score for index 2,0 for offense or defense
		 * @param game Representation of game board
		 * @param player Either 1,2 for X or O
		 * @return Integer value for specified index
		 */
		public int evalIndex20(GameState game, int player)
		{
			int counter = 0;
			
			// Check for play at requested index
			if(game.getIndexValue(2, 0) !=  0)
			{
				return -1;
			}
			
			// Check for possible score increment from top left to bottom left
			if(game.getIndexValue(0, 0) == 0 || game.getIndexValue(0, 0) == player)
			{
				if(game.getIndexValue(1, 0) == 0 || game.getIndexValue(1, 0) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top right to bottom left
			if(game.getIndexValue(1, 1) == 0 || game.getIndexValue(1, 1) == player)
			{
				if(game.getIndexValue(0, 2) == 0 || game.getIndexValue(0, 2) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from bottom left to bottom right
			if(game.getIndexValue(2, 1) == 0 || game.getIndexValue(2, 1) == player)
			{
				if(game.getIndexValue(2, 2) == 0 || game.getIndexValue(2, 2) == player)
				{
					counter++;
				}
			}
			return counter;
		}
		
		/**
		 * Used to get the score for index 2,1 for offense or defense
		 * @param game Representation of game board
		 * @param player Either 1,2 for X or O
		 * @return Integer value for specified index
		 */
		public int evalIndex21(GameState game, int player)
		{
			int counter = 0;
			
			// Check for play at requested index
			if(game.getIndexValue(2, 1) !=  0)
			{
				return -1;
			}
			
			// Check for possible score increment from top middle to bottom middle
			if(game.getIndexValue(0, 1) == 0 || game.getIndexValue(0, 1) == player)
			{
				if(game.getIndexValue(1, 1) == 0 || game.getIndexValue(1, 1) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from bottom left to bottom right
			if(game.getIndexValue(2, 0) == 0 || game.getIndexValue(2, 0) == player)
			{
				if(game.getIndexValue(2, 2) == 0 || game.getIndexValue(2, 2) == player)
				{
					counter++;
				}
			}
			return counter;
		}
		
		/**
		 * Used to get the score for index 2,2 for offense or defense
		 * @param game Representation of game board
		 * @param player Either 1,2 for X or O
		 * @return Integer value for specified index
		 */
		public int evalIndex22(GameState game, int player)
		{
			int counter = 0;
			
			// Check for play at requested index
			if(game.getIndexValue(2, 2) !=  0)
			{
				return -1;
			}
			
			// Check for possible score increment from top right to bottom right
			if(game.getIndexValue(0, 0) == 0 || game.getIndexValue(0, 0) == player)
			{
				if(game.getIndexValue(1, 1) == 0 || game.getIndexValue(1, 1) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from bottom left to bottom right
			if(game.getIndexValue(0, 2) == 0 || game.getIndexValue(0, 2) == player)
			{
				if(game.getIndexValue(1, 2) == 0 || game.getIndexValue(1, 2) == player)
				{
					counter++;
				}
			}
			
			// Check for possible score increment from top left to bottom right
			if(game.getIndexValue(2, 0) == 0 || game.getIndexValue(2, 0) == player)
			{
				if(game.getIndexValue(2, 1) == 0 || game.getIndexValue(2, 1) == player)
				{
					counter++;
				}
			}
			return counter;
		}
		
		/**
		 * Iterates though score matrix and chooses choice with the highest score value
		 * @param game Keeps track of game state
		 */
		public void findBestChoice(GameState game)
		{
			int max = -1;
			
			// Iterate through each space on the score matrix
			for(int row = 0; row < 3; row++)
			{
				for(int col = 0; col < 3; col++)
				{
					// Check if move is not taken and if it has the highest score value
					// If so make this  the new max and change the computers choice
					if(game.isMoveValid(row, col) && scoreMatrix[row][col] > max)
					{
						max = scoreMatrix[row][col];
						choice[0] = row;
						choice[1] = col;
	 				}
				}
			}
		}
		
		
		/**
		 * Used to make sure player is not making a move that would guarantee a win for him
		 * @param game Used to keep track of game state
		 */
		public void checkForTrickMove(GameState game)
		{
			// Check if user is attempting to make a trick move on computer
			if(game.convertPlayerToString(2, 0) == "X")
			{
				bottomLeftTrickMove(game);
			}
			
			// Check if user is attempting to make a trick move on computer
			else if(game.convertPlayerToString(0, 0) == "X")
			{
				topLeftTrickMove(game);
			}
			// Check if user is attempting to make a trick move on computer
			else if(game.convertPlayerToString(2,2) == "X")
				bottomRightTrickMove(game);
			
			// Check if user is attempting to make a trick move on computer
			else if(game.convertPlayerToString(0, 2) == "X")
			{
				topRightTrickMove(game);
			}
		}
		
		

		/**
		 * Used to make a counter move against a specific trick move by player
		 * @param game Keeps track of game state
		 */
		public void bottomLeftTrickMove(GameState game)
		{
			// Used to counter specific trick move
			if(game.convertPlayerToString(0,2) == "X" && game.isMoveValid(2, 1))
			{
				choice[0]= 2;
				choice[1]= 1;
			}
			
			// Used to counter specific trick move
			else if(game.isMoveValid(1, 1))
			{
				choice[0]= 1;
				choice[1]= 1;
			}
		}
		
		/**
		 * Used to make a counter move against a specific trick move by player
		 * @param game Keeps track of game state
		 */
		public void topLeftTrickMove(GameState game)
		{
			// Used to counter specific trick move
			if(game.convertPlayerToString(2,2) == "X" && game.isMoveValid(0, 1))
			{
				choice[0]= 0;
				choice[1]= 1;
			}
			
			// Used to counter specific trick move
			else if(game.isMoveValid(1, 1))
			{
				choice[0]= 1;
				choice[1]= 1;
			}
		}
		
		/**
		 * Used to make a counter move against a specific trick move by player
		 * @param game Keeps track of game state
		 */
		public void bottomRightTrickMove(GameState game)
		{
			// Used to counter specific trick move
			if(game.convertPlayerToString(0,0) == "X" && game.isMoveValid(2, 1))
			{
				choice[0]= 2;
				choice[1]= 1;
			}
			
			// Used to counter specific trick move
			else if(game.isMoveValid(1, 1))
			{
				choice[0]= 1;
				choice[1]= 1;
			}
		}
		
		/**
		 * Used to make a counter move against a specific trick move by player
		 * @param game Keeps track of game state
		 */
		public void topRightTrickMove(GameState game)
		{
			// Used to counter specific trick move
			if(game.convertPlayerToString(2,0) == "X" && game.isMoveValid(0, 1))
			{
				choice[0]= 0;
				choice[1]= 1;
			}
			
			// Used to counter specific trick move
			else if(game.isMoveValid(1, 1))
			{
				choice[0]= 1;
				choice[1]= 1;
			}	
		}

}
