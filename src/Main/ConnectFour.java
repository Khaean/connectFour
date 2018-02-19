package Main;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
 
public class ConnectFour {
	
	//Default row and column number
	private final static int _columnCountDefault = 7;
	private final static int _rowCountDefault = 6;
	//Default how many discs need to be align to win
	private final static int _countToWinDefault = 4;
	
	//row and column number used
	private static int _columnCount;
	private static int _rowCount;
	//how many discs need to be align to win
	private static int _countToWin;
	
	private enum Colors{
		RED,
		GREEN;
	};
	
	//initialize colors map
	final static Map<Integer, String> _colors;
	static {
		_colors = new HashMap<Integer, String>();
		_colors.put(new Integer(1), Colors.RED.toString() ); //player 1 color 
		_colors.put(new Integer(2), Colors.GREEN.toString()); //player 2 color
	}
	
	//Initialize the scanner used during the session
	private static Scanner _scan;
	
	//Board used for the game
	private Integer[][] _board;
	
	//Keep full column track
	private Set<Integer> _columnFull;
	
	/**
	 * Default constructor to create a new game 
	 */
	public ConnectFour(Scanner scan)
	{
		InitializeGame(_rowCountDefault,_columnCountDefault,_countToWinDefault,scan);
	}
	
	/**
	 * Custom constructor to create a new game 
	 */
	public ConnectFour(int row, int column, int nbrToWin, Scanner scan)
	{
		InitializeGame(row, column, nbrToWin, scan);
	}
	
	/**
	 * Custom constructor to create a new game 
	 * 
	 * @param row number of row in the board
	 * @param column number of column in the board
	 * @param nbrToWin how many discs need to be align to win
	 */
	private void InitializeGame(int row, int column, int nbrToWin, Scanner scan )
	{
		_rowCount = row;
		_columnCount = column;
		_countToWin = nbrToWin;
		setBoard(new Integer[_rowCount][_columnCount]);
		_columnFull = new HashSet<Integer>();
		_scan = scan;
	}
	
	/**
	 *  Method to print the board game to the console
	 *  @return void
	 */
	private void printBoard()
	{
		for (int i = _rowCount-1; i >= 0; i--) {
			
			//Print upper delimiter
			if (i == _rowCount-1)
			{
				for (int j = 0; j < getBoard()[i].length; j++) 
					System.out.print("--");
				System.out.println("-");
			}
			
			//Print the board
			for (int j = 0; j < getBoard()[i].length; j++) 
			{
				System.out.print("|");
				if ( getBoard()[i][j] == null )
					System.out.print(" ");
				else
					System.out.print(_colors.get(getBoard()[i][j]).charAt(0));
			}
			System.out.print("|");
			System.out.println();
			
			//Print lower delimiter
			if (i == 0)
			{
				for (int j = 0; j < getBoard()[i].length; j++) 
					System.out.print("--");
				System.out.println("-");
			}
			
		}
	}
	
	/**
	 * Method to request user the column number
	 * 
	 * @param int player player's number, should be 1 or 2
	 * @return int the column number
	 */
	public int requestInput(int player) 
	{
		int column = 0;
 		
 		//Keep asking player to enter a value as long as the value is not an integer or is not the board range
 		//Also check if the column is already full
 		do {
 			System.out.print("Player " + player + " ["+ _colors.get(player)+"] - choose column (1-" + _columnCount + "): ");
 			
 			//Check if the input is an integer
			while (!_scan.hasNextInt()) 
			{
				System.out.println("Please enter a number between 1 and " + _columnCount);
				_scan.next();
			}
			
			//Get the column input by user
		    column = _scan.nextInt() -1;
 		}
		while (!TestInput(column));
 		
 		return column;
	}
	
	/**
	 * Method to test the column number
	 * 
	 * @param int  column Column to be tested
	 * @return boolean return if disc can be dropped in this column
	 */
	public boolean TestInput(int column) 
	{
		//Check if the column index is in the range
		if (column < 0 || column > _columnCount-1)
			return false;
		// Check if column is full
		else if(_columnFull.contains(column))
	    {
	    	System.out.println("Column is Full, please choose another column.");
	    	return false;
	    }
	    
	    return true;
	}
	
	/**
	 * Method to drop a disc inside the board
	 * 
	 * @param int player player's number, should be 1 or 2
	 * @param int column Column where should the disc be drop
	 * @return void
	 */
	public void dropDisc(int player, int column) 
	{
		//Find which row to drop the disc
		for (int row = 0; row < _rowCount; row++) 
		{
			if (getBoard()[row][column] == null)
			{
				//Add the disc to the row
				getBoard()[row][column] = player;
				
				//If disc is added to the last row, column became full
				if (row == _rowCount -1)
					_columnFull.add(column);
				
				break;
			}
		}		
	} 
	
	/**
	 * Check if one player won the game	
	 * 
	 * @return Integer player's number who won the game
	 */
	public Integer checkWinner() {
		
		//Check if 4 straight horizontal
		for(int row = 0; row < _rowCount; row++)
		{
			for(int column = 0; column < _columnCount-3; column++)
			{
				int i = 1;
				while (getBoard()[row][column] != null && getBoard()[row][column] == getBoard()[row][column+i])
					i++;
				
				if( i > _countToWin - 1 )
					return getBoard()[row][column];
			}
		}
		
		//Check if 4 straight vertical or diagonal
		for(int row = 0; row < _rowCount-3; row++)
		{
			//Check if 4 straight vertical 
			for(int column = 0; column < _columnCount; column++)
			{
				int i = 1;
				while (getBoard()[row][column] != null && getBoard()[row][column] == getBoard()[row+i][column])
					i++;
				
				if( i > _countToWin - 1 )
					return getBoard()[row][column];
			}
			
			//Check if 4 straight diagonal 
			for(int column = 0; column < _columnCount-3; column++)
			{
				int i = 1;
				while (getBoard()[row][column] != null && getBoard()[row][column] == getBoard()[row+i][column+i])
					i++;
				
				if( i > _countToWin - 1 )
					return getBoard()[row][column];
			}
			//Check if 4 straight diagonal
			for(int column = 3; column < _columnCount; column++)
			{
				int i = 1;
				while (getBoard()[row][column] != null && getBoard()[row][column] == getBoard()[row+i][column-i])
					i++;
				
				if( i > _countToWin - 1 )
					return getBoard()[row][column];
			}
		}
		 
		return new Integer(0);
	}
	
	public boolean boardIsFull()
	{
		return _columnFull.size()==_columnCount;
	}
	
	
	/**
	 * Get the board
	 * 
	 * @return Integer[][] board.
	 */	
	public Integer[][] getBoard() {
		return _board;
	}
	
	/**
	 * Set the board
	 * 
	 * @param Integer[][] board to be set
	 * @return nothing
	 */	
	public void setBoard(Integer[][] _board) {
		this._board = _board;
	}
	
	public boolean runGame() {
		
		int turn = 0;
		boolean moreGame = false;		
		Integer winner = new Integer(0);
				
		printBoard();
		
		//Check if there is any winner or if board is full
		//If not continue the game
		while(!boardIsFull() && winner==0)
		{
			int column = requestInput((turn % 2) + 1);
			
			//Drop the next disc
			dropDisc( (turn % 2) + 1, column );
			
			//Print the current board state to the console
			printBoard();
			
			//Check if there is any winner
			winner = checkWinner();
			
			//If any winner, print the result on console
			if (winner!=0) 
				System.out.println("Player "+ winner +" ["+ _colors.get( winner )+"] wins!");
			
			turn++;
		}
		
		//If no winner, it's a DRAW
		if(winner==0)
			System.out.println("No player won! DRAW");
		
		//Ask players if they want to play more
		System.out.print("One more game (Y/N)? ");
		
		//Check only if player choose Y or y
		if (_scan.next().equalsIgnoreCase("y") )
			moreGame = true;
		
		return moreGame;
	}
	
	/**
	 * Main Method to start the game
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@SuppressWarnings("unused")
	public static void main (String[] args) 
	{
		boolean moreGame = true;
		
		//As long as player want to play, process is running
		while(moreGame)
		{
			//Create new game
			Scanner scan = new Scanner(System.in);
			ConnectFour game = new ConnectFour(scan);
			
			//Check board size
			if(_rowCount < 4 || _columnCount < 4)
			{
				System.out.println("Board is too small!");
				break;
			}
			
			moreGame = game.runGame();
		}
		_scan.close();
	}
}