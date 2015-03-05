public class TicTacToeAI {

	//board values
	private static int[][] Amap = { {0,0,0} , {0,0,0} , {0,0,0}  }; //Available Spots on the board
	private static int[][] Xmap = { {0,0,0} , {0,0,0} , {0,0,0}  }; //location of 'X' Pieces
	private static int[][] Omap = { {0,0,0} , {0,0,0} , {0,0,0}  }; //location of 'O' Pieces

	private int xPos = 0; 
	private int yPos = 0;
	
	public TicTacToeAI(int[][] aMap, int[][] xMap, int[][] oMap) {
		Amap = aMap; 
		xMap = Xmap; 
		oMap = Omap; 		
	}

	public void boardState(int[][] aMap, int[][] xMap, int[][] oMap) {
		Amap = aMap; 
		Xmap = xMap; 
		Omap = oMap; 
		getMove(); 
	}

	public void getMove() {
		// Check if their is a possible win
		if (compWin()) {
			return; 
		} else if (opponentWin()) {
			return; 
		} else if (checkMiddle()) {
			return; 
		} else if (checkCorners()) {
			return; 
		} else if (checkSides()) {
			return; 
		}
	}

	public boolean compWin() {	
		
		// Check to see if there is a two in a row for the computer 
		
		return false; 
	}

	public boolean opponentWin() {
		
		// Check to see if there a two in a row for the player 
		
		
		
		return false; 
	}

	public boolean checkMiddle() {
		if (Amap[1][1] == 0) {
			xPos = 1; 
			yPos = 1; 
			return true; 
		}
		
		return false; 
	}

	public boolean checkCorners() {
		if (Amap[0][0] == 0) {
			xPos = 0; 
			yPos = 0; 
			return true; 
		} else if (Amap[2][0] == 0) {
			xPos = 2; 
			yPos = 0;
			return true; 
		} else if (Amap[0][2] == 0) {
			xPos = 0; 
			yPos = 2; 
			return true; 
		} else if (Amap[2][2] == 0) {
			xPos = 2;
			yPos = 2; 
			return true; 
		} else {
			return false; 	
		}
		
	}

	public boolean checkSides() {
		if (Amap[0][1] == 0) {
			xPos = 0; 
			yPos = 1; 
			return true; 
		} else if (Amap[1][0] == 0) {
			xPos = 1; 
			yPos = 0;
			return true; 
		} else if (Amap[2][0] == 0) {
			xPos = 2; 
			yPos = 0; 
			return true; 
		} else if (Amap[1][2] == 0) {
			xPos = 2;
			yPos = 2; 
			return true; 
		} else {
			return false; 	
		}
	}
	
	public int getNextX() {
		return xPos; 
	}

	public int getNextY() {
		return yPos; 

	}

}
