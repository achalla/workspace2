package sudokusolver;

import sudokusolver.SudokuSolverBottomUp.BadAssumptionException;

public class SudokuSolverTopDown {
	/*
	 * Some terminology: 
	 * I have called each of the horizontal strips of 1-9 numbers on the board a "row"
	 * I have called each of the vertical strips of 1-9 numbers on the board a "column" (abbreviated "col")
	 * I have called each of the 3 by 3 grids that must contain 1-9 a "block," or a "3x3" 
	 * I have called each of the 81 individual units containing numbers on the board a "cell"
	 * 
	 * I have called each of the 3 groups of horizontal rows a "latitude" (abbreviated "lat")
	 * I have called each of the 3 groups of vertical columns a "longitude" (abbreviated "lon" --
	 * 		 "long" isn't gonna work as a variable name)
	 * 
	 * I have called each of the horizontal or vertical strip of three cells within a 3x3 a "triplet"
	 */
	
//	private 
	int[][] board;
//	private
	int[][][] hints;
	
	private final static int[][] blockMap = {{0,1,2},{3,4,5},{6,7,8}};
	
	private SudokuSolverTopDown(){
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
	
	void printHints(int row, int col){
		for(int k=0;k<10;k++){
			System.out.println("hints("+row+","+col+","+k+") = "+hints[row][col][k]+" ");
		}
	}
	
	public static SudokuSolverTopDown getNewBoard(){
		return new SudokuSolverTopDown();
	}
	
	public void loadBoard1(){
		//1-29 on Simply Sudoku
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
	}
	
/*	public void solve(){
		checkLat();
	}
	
	void checkLat(){//make return boolean changesMade
		
	}*/
	
	/*
	 * Returns col index
	 */
	int containsInRow(int row, int value){
		if(row < 0 || row > 9-1) throw new IllegalArgumentException();
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
				System.out.println();
			}
		}
	}
	
	void printHintsTemplate(){
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
				System.out.println();
			}
		}
	}
	
//	void scanFor
	
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
		if(row >= 0 && row < 3) return 0;
		if(row >= 3 && row < 6) return 1;
		if(row >= 6 && row < 9) return 2;
		throw new BadAssumptionException();
	}
	
	private int getLon(int col){
		if(col < 0 || col >= 9) throw new IllegalArgumentException();
		if(col >= 0 && col < 3) return 0;
		if(col >= 3 && col < 6) return 1;
		if(col >= 6 && col < 9) return 2;
		throw new BadAssumptionException();
	}
	
	public static void main(String[] args) {
		SudokuSolverTopDown ss = new SudokuSolverTopDown();
		ss.loadBoard1();
		ss.printBoardBy3x3();		
		ss.printHints(8,8);
		ss.populateHints();
		ss.printHints(8,8);
		int grid = ss.getGrid(8,8);
		System.out.println(grid);
		System.out.println(ss.containsInGrid(grid,7));
		System.out.println("startRow "+grid/3*3+" startCol:"+grid%3*3);
		ss.printHints();
	}
}
