
import java.io.*;

public class TicTacToe
{

	//board values
	private static int[][] Amap = { {0,0,0} , {0,0,0} , {0,0,0}  }; //Available Spots on the board
	private static int[][] Xmap = { {0,0,0} , {0,0,0} , {0,0,0}  }; //location of 'X' Pieces
	private static int[][] Omap = { {0,0,0} , {0,0,0} , {0,0,0}  }; //location of 'O' Pieces

	private static boolean compPlayer; 
	private static int curTurn = 1; 

	private static TicTacToeAI ai;

	public static void main(String args[]) throws Exception {
		//debug tools - 'cuz that's how I 
		//CheckMapDebuggerTool(Amap);

		Welcome();
		NewGame();
		NumPlayers();

		ai = new TicTacToeAI(Amap, Xmap, Omap);

		while(true)
		{
			System.out.println("CurTurn: " + curTurn);
			ShowGameBoard();
			if (compPlayer)
				if (curTurn == 2) 
					curTurn = getCompMove(); 
				else 
					curTurn = GetMove();
			
			CheckWin();
		}
	}

	public static void NewGame() throws IOException {

		System.out.println("\nWould you like to play a new game? (y or n)");
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String StartNewGame;
		StartNewGame = dataIn.readLine();
		if (StartNewGame.equals("y") || StartNewGame.equals("Y"))
		{
			System.out.println("\n\nRight on! Let's get to it!\n");
			setUpGame();
		}
		else if (StartNewGame.equals("n") || StartNewGame.equals("N"))
		{
			System.out.println("\nYes. Let's not and say we did...\n\nSmell Ya Later!\n");
			System.exit(0);
		}
		else
		{
			System.out.println("\nSorry I couldn't figure out what you typed, \nSo I'm just going to assume that you're really eager to play another game! \nlet's get going!");
			setUpGame();
		}

	}

	public static void setUpGame() {
		for (int x=0; x < 3; x++) {
			for (int y=0; y < 3; y++) {
				Amap[y][x] = 0;
			}
		}

		for (int x=0; x < 3; x++) {
			for (int y=0; y < 3; y++) {
				Xmap[y][x] = 0;
			}
		}

		for (int x=0; x < 3; x++) {
			for (int y=0; y < 3; y++) {
				Omap[y][x] = 0;
			}
		}
	}

	public static void Welcome() {
		System.out.println("\n  Welcome to Tic-Tac-Toe Not-So-Deluxe v1.1\n\n           by Ken Poirier\n\n");
	}

	public static void NumPlayers() throws Exception { 
		System.out.println("\n\nWould you like to play One player or Two Player? (1/2)\n");

		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String numPlayers = dataIn.readLine();

		if (numPlayers.equals("1")) {
			compPlayer = true; 
			FirstPlayer(); 
		} else {
			compPlayer = false; 
		}
	}

	public static void FirstPlayer() throws IOException {
		System.out.println("\n\nWould you like to go first? (y/n)\n");

		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String numPlayers = dataIn.readLine();

		if (numPlayers.equals("y") || numPlayers.equals("Y")) {
			curTurn = 1; 
		} else {
			curTurn = 2; 
		}

	}

	public static void CheckWin()  throws IOException {

		//check player 1 win
		if (Xmap[0][0] == 1) {
			if (Xmap[0][1] == 1 && Xmap[0][2] == 1) {
				showWinner(1);
			}
			else if (Xmap[1][0] == 1 && Xmap[2][0] == 1) {
				showWinner(1);
			}
			else if (Xmap[1][1] == 1 && Xmap[2][2] == 1) {
				showWinner(1);
			}
		}	

		if (Xmap[1][2] == 1) {
			if (Xmap[2][2] == 1 && Xmap[0][2] == 1) {
				showWinner(1);
			} 	else if (Xmap[1][0] == 1 && Xmap[1][1] == 1) {
				showWinner(1);
			}
		} 

		if (Xmap[2][1] == 1) {
			if (Xmap[2][2] == 1 && Xmap[2][0] == 1) {
				showWinner(1);
			} else if (Xmap[0][1] == 1 && Xmap[1][1] == 1) {
				showWinner(1);
			}
		} 

		if (Xmap[0][2] == 1 && Xmap[1][1] == 1 && Xmap[2][0] == 1) {
			showWinner(1);
		}



		//check player 2 win
		if (Omap[0][0] == 1)
		{
			if (Omap[0][1] == 1 && Omap[0][2] == 1) {
				showWinner(2);
			}
			else if (Omap[1][0] == 1 && Omap[2][0] == 1) {
				showWinner(2);
			}
			else if (Omap[1][1] == 1 && Omap[2][2] == 1) {
				showWinner(2);
			}
		} else if (Omap[1][2] == 1) {
			if (Omap[2][2] == 1 && Omap[0][2] == 1) {
				showWinner(2);
			}
			else if (Omap[1][0] == 1 && Omap[1][1] == 1) {
				showWinner(2);
			}
		} else if (Omap[2][1] == 1) {
			if (Omap[2][2] == 1 && Omap[2][0] == 1) {
				showWinner(2);
			}
			else if (Omap[0][1] == 1 && Omap[1][1] == 1) {
				showWinner(2);
			}
		} else if (Omap[0][2] == 1 && Omap[1][1] == 1 && Omap[2][0] == 1) {
			showWinner(2);
		}

		//Check for -> BOARD FULL!!! NO WINNERS!!!

		int iFull;

		iFull = 0;

		for (int x=0; x < 3; x++)
		{
			for (int y=0; y < 3; y++)
			{
				if (Amap[y][x] == 1)
					iFull++;

			}
		}

		if (iFull == 9)
		{
			showWinner(0);
		}

	}

	public static void showWinner(int winner) throws IOException {
		DispWinner(winner);
		ShowGameBoard();
		DispWinner(winner);
		NewGame();	
	}

	public static void DispWinner(int player)
	{
		String winner;
		if (player == 1)
			winner = "eXes";
		else if (player == 2)
			winner = "Ohes";
		else if (player == 0)
			winner = "BOARD FULL! STALEMATE - NO ";

		else
			winner = "Bad Programming";

		System.out.println("\n\n********************************************\n      "
				+ winner + " WIN!!! \n********************************************\n\n");
	}


	public static int NextTurn(int curTurn)
	{
		ai.getMove();
		if (curTurn == 1)
			return 2;
		else if (curTurn == 2)
			return 1;
		else
			System.out.println("Error: Board value out of bounds on Next turn ");

		return -1;
	}
	
	public static int getCompMove() {
		ai.boardState(Amap, Xmap, Omap);
		
		int x = ai.getNextX();
		int y = ai.getNextY();
		
		if (Amap[x][y] == 1) {
			System.out.println();
			System.out.println("The computer made a mistake. I am sorry. Let me debug");
			System.out.println();
			return 2;
		} 
		
		if (Omap[x][y] == 0)
		{
			Omap[x][y] = 1; 
			Amap[x][y] = 1; 			
		}
		
		return 1; 
	}

	public static int GetMove() throws IOException
	{	
		String SpotOn = "";

		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));

		//say who's turn it is
		if (curTurn == 1)
			System.out.println("It is X's Turn. \nPlace a piece by typing a letter then a Number (example: b2) and pressing enter:");
		else if (curTurn == 2)
			System.out.println("It is O's Turn. \nPlace a piece by typing a letter then a Number (example: b2) and pressing enter:");
		else
			System.out.println("Turn Value Error");

		SpotOn = dataIn.readLine(); //read in input from player

		//row 1
		if (SpotOn.equals("a1")) {
			return markBoard(0,0);
		}

		else if (SpotOn.equals("a2"))  {
			return markBoard(0,1);
		}

		else if (SpotOn.equals("a3")) {
			return markBoard(0,2);	
		}

		//row 2
		else if (SpotOn.equals("b1")) {
			return markBoard(1,0);	
		}

		else if (SpotOn.equals("b2")) {
			return markBoard(1,1);
		}

		else if (SpotOn.equals("b3")) {
			return markBoard(1,2);
		}

		//row 3
		else if (SpotOn.equals("c1")) {
			return markBoard(2,0);
		}

		else if (SpotOn.equals("c2")) {
			return markBoard(2,1);
		}

		else if (SpotOn.equals("c3")) {
			return markBoard(2,2);
		}

		//easter egg
		else if (SpotOn.equals("cheater")) {
			System.out.println("\nThere has been a report that one of the players has been cheating!"
					+"\nThis match is considered faulted and if you wish to continue, \nyou must start over...\n");

			NewGame();
		}

		else {
			System.out.println("\nInvalid input. You typed : " + SpotOn + "\n\nTry typing a lowercase letter (a,b,c) followed by"
					+ " a number (1,2,3) with no space in between.\n");
			return curTurn;
		}
		System.out.println("error: if you can read this then something has gone horribly wrong!");
		return -1;
	}

	public static void ShowGameBoard() {

		String[][] s = new String[3][3];

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (Amap[i][j] == 0)
					s[i][j] = " ";
				else
				{
					s[i][j] = markSpot(i,j);
				}				
			}

		//Draw Board

		System.out.println();
		System.out.println("  1 2 3 ");
		System.out.println(" -------");
		System.out.println("a|" + s[0][0] + "|" + s[0][1] + "|" + s[0][2] + "|");
		System.out.println(" |-+-+-|");
		System.out.println("b|" + s[1][0] + "|" + s[1][1] + "|" + s[1][2] + "|");
		System.out.println(" |-+-+-|");
		System.out.println("c|" + s[2][0] + "|" + s[2][1] + "|" + s[2][2] + "|");
		System.out.println(" -------");
		System.out.println();
		System.out.println();
	} 


	public static String markSpot(int x, int y) {
		String sA = "";
		if (Xmap[x][y] == 1)
			sA = "X";
		else if (Omap[x][y] == 1)
			sA = "O";
		else
			sA = "?"; //indicates a logic flow error

		return sA; 
	}

	public static int markBoard(int x, int y) {
		if (Amap[x][y] == 1) {
			System.out.println();
			System.out.println("The Spot is taken, Try another");
			System.out.println();
			return curTurn;
		} else if (Amap[x][y] == 0)  {
			Amap[x][y] = 1;
			if (curTurn == 1)
			{
				Xmap[x][y] = 1;
				return NextTurn(curTurn);
			} else if (curTurn == 2) {
				Omap[x][y] = 1;
				return NextTurn(curTurn);
			} else
				System.out.println("Turn out of bounds");

		} else 
			System.out.println("Error: Board value out of bounds");

		return -1;	
	}

	public static void CheckMapDebuggerTool(int[][] map) {

		System.out.println();
		System.out.println("  1 2 3 ");
		System.out.println(" -------");
		System.out.println("a|" + map[0][0] + "|" + map[0][1] + "|" + map[0][2] + "|");
		System.out.println(" |-+-+-|");
		System.out.println("b|" + map[1][0] + "|" + map[1][1] + "|" + map[1][2] + "|");
		System.out.println(" |-+-+-|");
		System.out.println("c|" + map[2][0] + "|" + map[2][1] + "|" + map[2][2] + "|");
		System.out.println(" -------");
		System.out.println();
		System.out.println();
	}
}