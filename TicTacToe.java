/**
 * This class simulates a tic-tac-toe game
 * @author Jiaxin Lu
 *
 */
public class TicTacToe {
	/* Attribute declarations */
	private char[][] gameBoard;     //a game board represented by a 2D array 
	private int size;          //size by size game board
	private int inline;        //how many in-line symbols to win the game
	private int maxLevels;    //maximum level
	
/**
 * Constructor creates a TicTacToe object	
 * @param board_size size of the game board
 * @param inline number of inline symbols to win
 * @param max_levels maximum level
 */
	public TicTacToe (int board_size, int inline, int max_levels){
		size = board_size;
		this.inline = inline;  
	    maxLevels = max_levels;
	    gameBoard = new char[size][size];
	    for (int i=0; i<size; i++){   
	    	for (int j=0; j<size; j++){
	    		gameBoard[i][j]=' ';    //initiate the game board
	    	}
	    }
		
	}
	
	/**
	 * createDictionary method creates an empty dictionary
	 * @return the empty dictionary
	 */
    public Dictionary createDictionary (){
    	Dictionary newDictionary = new Dictionary(4000);   //create a new Dictionary object
    	return newDictionary;
    }
    
    /**
     * repeartedConfig method checks if the string representing the configuration is in dictionary
     * @param configurations the dictionary
     * @return associated score of configuration if is in dictionary; otherwise -1
     */
    public int repeatedConfig (Dictionary configurations){
    	String s = "";
    	for (int i=0; i<size; i++){        //loop through the rows
    		for (int j=0; j<size; j++){     //loop through the columns
    			s = s + gameBoard[i][j];  //the string representing the configuration
    		}
    	}
    	return configurations.find(s);	
    }
    
    /**
     * insertConfig method insert the string configuration and it's associate score to dictionary.
     * @param configurations the dictionary
     * @param score score of configuration
     */
    public void insertConfig (Dictionary configurations, int score){
    	String stringConfig = "";
        for (int i=0; i<size; i++){             //loop through the rows
    		for (int j=0; j<size; j++){          //loop through the columns
    			stringConfig = stringConfig + gameBoard[i][j];      
    		}
    	}
    	DictEntry newEntry = new DictEntry (stringConfig, score);   //a new DictEntry object stores the configuration and score
    	try{
    	configurations.insert(newEntry);        //the insert method that may produce an exception
    	}
    	catch (DictionaryException e){
    		//Error handling code
    		System.out.println (e.getMessage());
    	}
    	
    }
    
    /**
     * storePlay method stores symbol in gameBoard[row][col]
     * @param row row of gameBoard
     * @param col column of gameBoard
     * @param symbol symbol being stored in gameBoard
     */
    public void storePlay (int row, int col, char symbol){
    	gameBoard[row][col] = symbol;
    }
    
    
    /**
     * squareIsEmpty method checks whether gameBoard[row][col] is empty
     * @param row row of gameBoard
     * @param col column of gameBoard
     * @return true if game[row][col] is empty; otherwise false
     */
    public boolean squareIsEmpty (int row, int col){
    	return gameBoard[row][col]==' ';
    }
    
    /**
     * wins method check if who uses symbol wins
     * @param symbol the symbol to be checked if it wins
     * @return true if there are certain number of adjacent symbols in the same row, column, or diagonal
     */
    public boolean wins (char symbol){
    	int num;   //
    	int a;
    	int b;
    	//check if there are certain number of adjacent symbols in the same row
    	for (int i=0; i<size; i++){                //loop through the rows
    		for (int j=0; j<=size-inline; j++){    //loop through the columns
    			num = 0;
    			//come across the first symbol
    			if (symbol == gameBoard[i][j]){
    				num = 1;                       //count it the first symbol
    				a = j;                        //assign the column value to "a" so that it does not screw up the for loops
    				//check if there are more adjacent symbols
    				while (gameBoard[i][a+1]==symbol){   
    					num += 1;                 //count adjacent symbols
    					a += 1;                  //increment the column because checks the adjacent symbols in row
    					if  (num==inline)        //wins if there are 'inline' number of symbols in-line
    						return true;
    				}
    			}
    		}	
    	}
    	
    	//check if there are certain number of adjacent symbols in the same column
    	for (int j=0; j<size; j++){                         //loop through the rows
    		for (int i=0; i<=size-inline; i++){            //loop through the columns
    			num = 0;
    			//come across the first symbol
    			if (gameBoard[i][j] == symbol){
    				num = 1;                               //count it the first symbol
    				a = i;                                //assign the row to "a" so that it does not screw up the for loops
    				//check if there are more adjacent symbols
    				while (gameBoard[a+1][j]==symbol){
    					num += 1;                        //count adjacent symbols
    					a += 1;                          //increment the row because checks the adjacent symbols in column
    					if  (num==inline)                //wins if there are 'inline' number of symbols in-line
    						return true;
    				}
    				
    		    }
    	     } 
        }
    	
    	//check if there are certain number of adjacent symbols in the decreasing diagonal
    	for (int i=0; i<=size-inline; i++){                  //loop through the rows
    		for (int j=0; j<=size-inline; j++){               //loop through the columns
    			num = 0;
    			if (gameBoard[i][j]==symbol){                 //come across the first symbol
    				num = 1;                                  //count it the first symbol
    				a = i;                                    //assign the row to "a" so that it does not screw up the for loops
    				b = j;                                    //assign the column value to "b" so that it does not screw up the for loops
    				//check if there are more adjacent symbols
    				while (gameBoard[a+1][b+1]==symbol){
    					num += 1;                              //count adjacent symbols
    					a += 1;                      //increment both the row and the column because checks the adjacent symbols in diagonal
    					b += 1;
    					if (num==inline)                    //wins if there are 'inline' number of symbols in-line
        		    		return true;
    				}
    			}
    		}
    	}
    	
    	//check if there are certain number of adjacent symbols in the increasing diagonal
    	for (int i=0; i<=size-inline; i++){                  //loop through the rows
    		for (int j=inline-1; j<size-1; j++){              //loop through the columns
    			num = 0;
    			if (gameBoard[i][j]==symbol){                  //come across the first symbol
    				num = 1;                                  //count it the first symbol
    				a = i;                                    //assign the row to "a" so that it does not screw up the for loops
    				b = j;                                    //assign the column value to "b" so that it does not screw up the for loops
    				//check if there are more adjacent symbols
    				while (gameBoard[i+1][j-1]==symbol){
    					num += 1;                               //count the adjacent symbols
    					a += 1;                            //increment both the row and the column because checks the adjacent symbols in diagonal
    					b += 1;
    					if (num==inline)                   //wins if there are 'inline' number of symbols in-line
        					return true;
    				}	
    			 }
    		 }
    	 }
    	
    	 return false;                //otherwise, return false
    	
    }
    
    /**
     * isDraw method check if it is a draw game
     * @return true no player has won and no empty positions left; otherwise false
     */
    public boolean isDraw(){
    	int emptyNum = 0;
    	for (int i=0; i<size; i++){                //loop through the rows 
    		for (int j=0; j<size; j++){            //loop through the columns
    			if (squareIsEmpty(i,j))             //test empty position
    				emptyNum += 1;                 //count the empty positions
    		}
    	}
    	if (emptyNum==0 && !wins('X') && !wins('O'))     //no player has won and no empty positions left
    		return true;
    	return false;
    }
    
    /**
     * evalBoard method evaluates a value for a configuration
     * @return a number in terms of who has won
     */
    public int evalBoard(){
    	if (wins ('O'))             //computer has won
    		return 3;
    	if (wins ('X'))             //human player has won
    		return 0;
    	if (isDraw())              //a draw game
    		return 2;
    	else
    		return 1;              //undecided game
    }
}
