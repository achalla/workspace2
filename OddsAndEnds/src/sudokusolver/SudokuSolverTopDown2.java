package sudokusolver;

import sudokusolver.SudokuSolverBottomUp.BadAssumptionException;

public class SudokuSolverTopDown2 {
	/*
	 * Some terminology: 
	 * I have called each of the horizontal strips of 1-9 numbers on the board a "row"
	 * I have called each of the vertical strips of 1-9 numbers on the board a "column" (abbreviated "col")
	 * I have called each of the 3 by 3 grids that must contain 1-9 a "block," or a "3x3" 
	 * I have called each of the 81 individual units containing numbers on the board a "cell"
	 * 
	 * I have called each of the 3 groups of 3 horizontal rows a "latitude" (abbreviated "lat")
	 * I have called each of the 3 groups of vertical columns a "longitude" (abbreviated "lon" --
	 * 		 "long" isn't gonna work as a variable name)
	 * 
	 * I have called each of the 3 horizontal or vertical strips of three cells within a 3x3 a "triplet"
	 */
	

	int[][] board;
	int[][][] hints;
	boolean changesMade, changesMadeTotal;
	
	private final static int[][] blockMap = {{0,1,2},{3,4,5},{6,7,8}};
	
	private SudokuSolverTopDown2(){
		board = new int[9][9];
		hints = new int[9][9][10];
		fillHints();
		/*
		 * ^ Why 10? Because I'll use the same index as value (1-9) for ease
		 * Maybe index 0 could keep a count of how many trues are left in array
		 */
	}
	
	void fillHints(){
		for(int i = 0; i<9;i++){
			for(int j=0;j<9;j++){
				for(int k=0;k<10;k++){
					if(k==0) hints[i][j][k] = 9;
					else hints[i][j][k] = 1;
				}
			}
		}
	}
	

	
	public static SudokuSolverTopDown2 getNewBoard(){
		return new SudokuSolverTopDown2();
	}
	
	public void loadBoard(int boardNum){
		switch(boardNum){
		case 1:
			loadBoard1();
			break;
		default:
			throw new IllegalArgumentException();
		}
		fillHints();
		populateHints();
	}
	
	public void loadBoard1(){
		//Board "1-29" on Simply Sudoku
		int[][] grid1 = 
		   {{0,0,0,0,0,0,5,9,1},
			{9,0,0,8,0,0,6,0,0},
			{1,0,0,5,0,3,0,7,8},
			{0,0,7,9,0,0,0,0,5},
			{0,0,0,2,0,5,0,0,0},
			{2,0,0,0,0,8,7,0,0},
			{7,4,0,1,0,2,0,0,3},
			{0,0,8,0,0,6,0,0,9},
			{5,2,6,0,0,0,0,0,0}};
		board = grid1;
		fillHints();
		populateHints();
	}
	
	void printHints(int row, int col){
		for(int k=0;k<10;k++){
			System.out.println("hints("+row+","+col+","+k+") = "+hints[row][col][k]+" ");
		}
	}
	
	public void printBoardBy3x3(){
		for(int i = 0; i<9;i++){
			for(int j = 0;j<9;j++){
				System.out.print(board[i][j]);
				if(j == 8) continue;
				else if(j%3 == 2) System.out.print(" | ");
				else System.out.print(" ");
			}			
			System.out.println();
			if(i%3==2 && i!=8) System.out.println("---------------------");
		}
		System.out.println();
	}
	
	//TODO
	void updateHintsRow(int row, int value){
		for(int j=0;j<9;j++){
			if(hints[row][j][value] != 0){
				hints[row][j][value] = 0;
				hints[row][j][0]--;
			}
		}
	}
	
	//TODO
	void updateHintsCol(int col, int value){
		for(int i=0;i<9;i++){
			if(hints[i][col][value] != 0){
				hints[i][col][value] = 0;
				hints[i][col][0]--;
			}
		}
	}
	
	//TODO
	void updateHintsGrid(int grid, int value){
		int startRow, startCol;
		startRow = grid/3;
		startCol = grid%3;
		for(int i=startRow;i<startRow+3;i++){
			for(int j=startCol;j<startCol+3;j++){
				if(hints[i][j][value] != 0){
					hints[i][j][value] = 0;
					hints[i][j][0]--;
				}
			}
		}
	}
	
	void updateHints(int row, int col, int grid, int value){
		updateHintsRow(row,value);
		updateHintsCol(col,value);
		updateHintsGrid(grid,value);
	}


	
	void populateHints(){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				int count = 0;
				for(int k=1;k<10;k++){
					if(board[i][j] != 0) hints[i][j][0] = 0;
					else{
						if(containsInRow(i,k)>-1) hints[i][j][k] = 0;
						else if(containsInCol(j,k)>-1) hints[i][j][k] = 0;
						else if(containsInGrid(getGrid(i,j),k)>-1) hints[i][j][k] = 0;
						else continue;
						count++;
					}
				}
				hints[i][j][0] -= count;
			}
		}
	}
	
	//TODO void updateHints
	//(in row, in col, in grid
	
	void printHints(){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.println("For ("+i+","+j+"):");
				if(hints[i][j][0]==0) {
					System.out.println("["+board[i][j]+"]");
					continue;
				}
				for(int k=1;k<10;k++){
					if(hints[i][j][k] != 0) System.out.print(k+" ");
				}
				System.out.println("("+hints[i][j][0]+")"); //# of possibilities for this cell: 
			}
		}
	}
	
	int findTheOneOfNine(int i, int j){
		for(int k = 1; k<10;k++){
			if(hints[i][j][k] != 0) return k;
		}
		throw new BadAssumptionException();
	}
	
	
	int containsInRow(int row, int value){
		if(row < 0 || row > 8) throw new IllegalArgumentException();
		if(value < 1 || value > 9) throw new IllegalArgumentException();
		for(int i = 0;i<9;i++){
			if(board[row][i] == value) return i;
		}
		return -1;
	}
	
	/*
	 * Returns row index
	 */
	int containsInCol(int col, int value){
		if(col < 0 || col > 9-1) throw new IllegalArgumentException();
		if(value < 1 || value > 9) throw new IllegalArgumentException();
		for(int i = 0;i<9;i++){
			if(board[i][col] == value) return i;
		}
		return -1;
	}
	
	int containsInGrid(int grid, int value){
		int startRow = -1, startCol = -1;
		startRow = grid/3*3;
		startCol = grid%3*3;
		for(int i=startRow;i<startRow+3;i++){
			for(int j = startCol;j<startCol+3;j++){
				if(board[i][j]==value) return 1;
			}
		}
		return -1;
	}
	
	int getGrid(int row, int col){
		return blockMap[getLat(row)][getLon(col)];
	}
	
	private int getLat(int row){
		if(row < 0 || row >= 9) throw new IllegalArgumentException();
		return row/3;
	}
	
	private int getLon(int col){
		if(col < 0 || col >= 9) throw new IllegalArgumentException();
		return col/3;
	}
	
	void fillInSolos(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(hints[i][j][0]==1){
					set(i,j,findTheOneOfNine(i,j));
					System.out.println("set ("+i+","+j+") to "+board[i][j]);
					printBoardBy3x3();
				}
			}
		}
	}
	
	void findUniquesInGrids(){
		for(int gridInd = 0;gridInd<9;gridInd++){//every grid 0-8
			for(int valInd = 1;valInd<=9;valInd++){//every value 1-9
				checkGridForUniques(gridInd,valInd);
			}
		}
	}
	
	void checkGridForUniques(int grid, int value){
		int startRow, startCol, count = 0,x=-1,y=-1;
		startRow = grid/3;
		startCol = grid%3;
		
		for(int i=startRow;i<startRow+3;i++){
			for(int j=startCol;j<startCol+3;j++){
				if(hints[i][j][0] == 0) continue;
				if(hints[i][j][value] == 1){
					count++;
					x=i;
					y=j;
				}
			}
		}

		if(count == 1){
			set(x,y,value);
		}
	}
	
	void set(int row, int col, int value){
		board[row][col] = value;
		hints[row][col][0] = 0;
		changesMade = true;
		changesMadeTotal = true;
		updateHints(row,col,getGrid(row,col),value);
	}
	
	
	//want to make this recursive next
	void solve(){
		printBoardBy3x3();
		populateHints();
		printHints();
		do{
			changesMadeTotal=false;
			do{
				changesMade = false;
				fillInSolos();
			}while(changesMade);

			do{
				changesMade = false;
				findUniquesInGrids();
			}while(changesMade);
		}while(changesMadeTotal);
	}

	
	public static void main(String[] args) {
		SudokuSolverTopDown2 ss = new SudokuSolverTopDown2();
		ss.loadBoard(1);
		ss.printBoardBy3x3();
	/*	ss.printHints(0, 0);
		ss.printHints(3, 3);*/
//		ss.fillInSolos();
		ss.solve();
		ss.printBoardBy3x3();
	}
}