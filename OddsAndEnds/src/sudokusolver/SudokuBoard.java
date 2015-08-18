package sudokusolver;


class SudokuBoard {
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
	 
	 *
	 *Personal notes: 
	 *Might hardcode in 9x9 constants if this gets too obnoxious
	 *Need to address TODOs
	 *Need to consider another method of solving the board:
	 *whittle down possibilities from each cell 
	 *(eliminating incorrect entries instead of building up correct entries)
	 */
	private static int[][] board;
	private final static int NUMBER_OF_ROWS = 9;
	private final static int NUMBER_OF_COLUMNS = 9;
	private final static int NUMBER_OF_BLOCKS = 9;
	private final static int VALUE_MAX = 9;
	private final static int VALUE_MIN = 1;
	private final static int EMPTY_VALUE = 0;
	private final static int BLOCK_LENGTH = 3;
	private final static int[][] blockMap = {{0,1,2},{3,4,5},{6,7,8}};
	
	private SudokuBoard(){
		board = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
	}
	
	public static SudokuBoard getNewBoard(){
		return new SudokuBoard();
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
	
	public void loadBoard2(){

		int[][] grid2 = 
	   {{0,4,0,0,8,0,0,0,0},
		{0,7,0,3,0,0,0,4,0},
		{0,9,8,0,7,0,5,0,1},
		{0,0,9,0,3,4,0,0,8},
		{0,0,2,0,0,0,9,0,0},
		{7,0,0,5,9,0,2,0,0},
		{9,0,7,0,2,0,4,3,0},
		{0,6,0,0,0,3,0,9,0},
		{0,0,0,0,6,0,0,8,0}};
		board = grid2;
	}
	
	
	
	private int contain(String request, int location, int value){
		switch(request){
		case "row":
			return containsInRow(location,value);
		case "col":
			return containsInCol(location,value);
		case "lat":
		
		case "lon":
			
		case "grid":
			
		default:
			throw new UnsupportedOperationException();
		}
	}
	
	/*
	 * Returns col index
	 */
	int containsInRow(int row, int value){
		if(row < 0 || row > NUMBER_OF_ROWS-1) throw new IllegalArgumentException();
		if(value < VALUE_MIN || value > VALUE_MAX) throw new IllegalArgumentException();
		for(int i = 0;i<NUMBER_OF_COLUMNS;i++){
			if(board[row][i] == value) return i;
		}
		return -1;
	}
	
	/*
	 * Returns row index
	 */
	int containsInCol(int col, int value){
		if(col < 0 || col > NUMBER_OF_COLUMNS-1) throw new IllegalArgumentException();
		if(value < VALUE_MIN || value > VALUE_MAX) throw new IllegalArgumentException();
		for(int i = 0;i<NUMBER_OF_ROWS;i++){
			if(board[i][col] == value) return i;
		}
		return -1;
	}

	private void checkAll3x3s(){
		//1 loop to check every block
		//1 loop to check every number per block
	}
	
	private void check3x3(int lat, int lon, int value){
		if(lat < 0 || lat > BLOCK_LENGTH-1) throw new IllegalArgumentException();
		if(lon < 0 || lon > BLOCK_LENGTH-1) throw new IllegalArgumentException();
		if(value < VALUE_MIN || value > VALUE_MAX) throw new IllegalArgumentException();
	}

	
	
	
	private void checkAllRows(){
		for(int i = 0; i < NUMBER_OF_BLOCKS;i++){
			
		}
	}
	
	/*
	 * This rambling made sense in the moment:
	 * 
	 * At some point I thought it'd be useful to give two known indices of 0-2 and
	 * figure out which one was left out...
	 * But now, I'm pretty sure I want to give three unknown indices and figure out which one would be invalid
	 */
	public int[] whichOfThree(int a, int b){
		//TODO make sure a and b are 0,1,2
		//TODO might want to make sure that they're distinct if(a==b) throw new...
		int[] trio = {-1,-1,-1};
		trio[a] = 1;
		trio[b] = 1;
		
		return trio;	
	}
	
	public int whichOfThreeInt(int a, int b){
		//TODO make sure a and b are 0,1,2
		//TODO might want to make sure that they're distinct if(a==b) throw new...
		int[] trio = whichOfThree(a,b);
		for(int i: trio){
			if(i==-1) return i;
		}
		throw new BadAssumptionException();
	}
	
	public int oddOneOut(int a, int b, int c){
		return (a==-1?0:(b==-1?1:2));
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

	int getGrid(int row, int col){
		return blockMap[getLat(row)][getLon(col)];
	}
	
	int getTripletFromRowAndGrid(int row, int grid, int value){
		int a,b;
		//from grid, let's get the cell coordinate
		for(int i = 0; i<BLOCK_LENGTH;i++){
			for(int j=0;j<BLOCK_LENGTH;j++){
				if(blockMap[i][j]==grid){//this is it!
					a = row;
					b = j*3;
					checkTriplet(a,b,value);
				}
			}
		}
		return -1;
	}
	
	void checkTriplet(int rowStart, int colStart,int value){
		int a,b,c;
		a = board[rowStart][colStart];
		b = board[rowStart][colStart+1];
		c = board[rowStart][colStart+2];
		if(a!=0){//a isn't empty
			if(b!=0){//b isn't empty
				if(c!=0) throw new BadAssumptionException();//how are they all filled?
				else board[rowStart][colStart+2] = value; //only one left
			}else{ //b is empty
				if(c!=0) board[rowStart][colStart+1] = value; //c isn't -- only one left
				else{
					//gotta check b and c
					if(containsInCol(colStart+1,value)>0) board[rowStart][colStart+2] = value;
					else if(containsInCol(colStart+2,value)>0) board[rowStart][colStart+1] = value;
				}
			}
		}else{//a is empty
			if(b!=0){//b isn't
				if(c!=0) board[rowStart][colStart] = value; //c isn't -- only one left
				else{
					//gotta check a and c
					if(containsInCol(colStart,value)>0) board[rowStart][colStart+2] = value;
					else if(containsInCol(colStart+2,value)>0) board[rowStart][colStart] = value;
				}
			}else{//b is empty
				if(c!=0){
					//gotta check a and b
					if(containsInCol(colStart+1,value)>0) board[rowStart][colStart] = value;
					else if(containsInCol(colStart,value)>0) board[rowStart][colStart+1] = value;
				}
				else{
					//gotta check a,b, and c
					//TODO
				}
			}
		}
	}
	
	void checkLat(int lat, int value){
		//TODO check arguments
		int rowA, rowB, rowC, gridA, gridB, gridC, count=0;
		int contA, contB, contC, contSum;
		int oddGrid, grid, row;
		rowA = lat*BLOCK_LENGTH;
		rowB = rowA+1;
		rowC = rowA+2;
		
		contA = containsInRow(rowA,value);
		if(contA>0) gridA = getGrid(rowA,contA);
		else gridA = -1;
		contB = containsInRow(rowB,value);
		if(contB>0) gridB = getGrid(rowB,contB);
		else gridB = -1;
		contC = containsInRow(rowC,value);
		if(contC>0) gridC = getGrid(rowC,contC);
		else gridC = -1;
		
		System.out.println("here");
		
		if(gridA == -1) count++;
		if(gridB == -1) count++;
		if(gridC == -1) count++;
		if(count != 2) return;
		
		System.out.println("here2");

		
		oddGrid = oddOneOut(gridA,gridB,gridC);
		
		grid = lat*BLOCK_LENGTH+oddGrid;
		
		int oddRow = oddOneOut(contA,contB,contC);
		System.out.println("oddGrid is "+oddGrid);
		System.out.println("oddRow is "+oddRow);
		System.out.println("row A: "+rowA+" rowB: "+rowB+" rowC: "+rowC);
		row = rowA+oddRow;
		grid = rowA+oddGrid;// this is because rowA = lat*BLOCK_LENGTH
		System.out.println("oddRow is "+oddRow+" and grid is "+grid);
		System.out.println("row is: "+row);
		
		getTripletFromRowAndGrid(row,grid,value);
		
		
		
	}

	/*
	 * returns grid number if value is there, else -1 if not
	 */
	int checkRow(int row, int value){
		if(row < 0 || row > NUMBER_OF_ROWS-1) throw new IllegalArgumentException();
		if(value < VALUE_MIN || value > VALUE_MAX) throw new IllegalArgumentException();

		return -1;
	}

	
	
	private void checkAllCols(){

	}
	
	private void checkLong(int lon){
		
	}

	private void checkCol(int col, int value){
		if(col < VALUE_MIN || col > NUMBER_OF_COLUMNS) throw new IllegalArgumentException();
		if(value < VALUE_MIN || value > VALUE_MAX) throw new IllegalArgumentException();

	}

	@Deprecated
	private void printBoard(){
		for(int[] i:board){
			for(int j:i){
				System.out.print(j);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	private void set(int row, int col, int value){
		board[row][col] = value;
	}
	
	int get(int row, int col){
		return board[row][col];
	}
	
	void printBoardBy3x3(){
		/*
		 * Intentionally written not to be hard-coded for 9x9 boards
		 */
		int numRows,numCols;
		numRows = board.length;
		numCols = board[0].length;
		for(int i = 0; i<numRows;i++){
			for(int j = 0;j<numCols;j++){
				System.out.print(board[i][j]);
				if(j == numCols-1) continue;
				else if(j%3 == 2) System.out.print(" | ");
				else System.out.print(" ");
			}			
			System.out.println();
			if(i%3==2 && i!=numRows-1) System.out.println("---------------------");
		}
	}
	
	private static class BadAssumptionException extends RuntimeException{
		public BadAssumptionException () {

	    }

	    public BadAssumptionException (String message) {
	        super (message);
	    }

	    public BadAssumptionException (Throwable cause) {
	        super (cause);
	    }

	    public BadAssumptionException (String message, Throwable cause) {
	        super (message, cause);
	    }
	}

	public static void main(String[] args) {
		SudokuBoard sb = new SudokuBoard();
		sb.loadBoard1();
//		set(4,0,-1);
//		sb.printBoardBy3x3();
//		System.out.println(sb.containsInRow(0,5));
		sb.checkLat(0,9);
		sb.printBoardBy3x3();
	}
	
}