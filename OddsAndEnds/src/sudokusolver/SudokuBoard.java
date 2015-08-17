package sudokusolver;


class SudokuBoard {
	/*
	 * Some terminology: 
	 * We will call each of the horizontal strips of 1-9 numbers on the board a "row"
	 * We will call each of the vertical strips of 1-9 numbers on the board a "column" (abbreviated "col")
	 * We will call each of the 3 by 3 grids that must contain 1-9 a "square," or a "3x3" 
	 * We will call each of the 81 individual units containing numbers on the board a "cell"
	 * 
	 * We will call each of the 3 groups of horizontal rows a "latitude" (abbreviated "lat")
	 * We will call each of the 3 groups of vertical columns a "longitude" (abbreviated "lon" --
	 * 		 "long" isn't gonna work as a variable name)
	 */
	private static int[][] board;
	private final static int NUMBER_OF_ROWS = 9;
	private final static int NUMBER_OF_COLUMNS = 9;
	private final static int VALUE_MAX = 9;
	private final static int VALUE_MIN = 1;
	private final static int EMPTY_VALUE = 0;
	private final static int SQUARE_SIZE = 3;
	
	public SudokuBoard(){
		board = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
	}
	
	public static SudokuBoard getNewBoard(){
		return new SudokuBoard();
	}
	
	public void loadBoard1(){

		int[][] grid1 = 
	   {{0,4,0,0,8,0,0,0,0},
		{0,7,0,3,0,0,0,4,0},
		{0,9,8,0,7,0,5,0,1},
		{0,0,9,0,3,4,0,0,8},
		{0,0,2,0,0,0,9,0,0},
		{7,0,0,5,9,0,2,0,0},
		{9,0,7,0,2,0,4,3,0},
		{0,6,0,0,0,3,0,9,0},
		{0,0,0,0,6,0,0,8,0}};
		board = grid1;
	}
	
	public void loadBoard2(){
		//1-29 on Simply Sudoku
		int[][] grid2 = 
		   {{0,0,0,0,0,0,5,9,1},
			{9,0,0,8,0,0,6,0,0},
			{1,0,0,5,0,3,0,7,8},
			{0,0,7,9,0,0,0,0,5},
			{0,0,0,2,0,5,0,0,0},
			{2,0,0,0,0,8,7,0,0},
			{7,4,0,1,0,2,0,0,3},
			{0,0,8,0,0,6,0,0,9},
			{5,2,6,0,0,0,0,0,0}};
		board = grid2;
	}
	
	private boolean contain(String request, int location, int value){
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
	
	private boolean containsInRow(int location, int value){
		if(location < 0 || location > NUMBER_OF_ROWS-1) throw new IllegalArgumentException();
		if(value < VALUE_MIN || value > VALUE_MAX) throw new IllegalArgumentException();
		for(int i:board[location]){
			if(i==value) return true;
		}
		return false;
	}
	
	private boolean containsInCol(int location, int value){
		if(location < 0 || location > NUMBER_OF_COLUMNS-1) throw new IllegalArgumentException();
		if(value < VALUE_MIN || value > VALUE_MAX) throw new IllegalArgumentException();
		for(int i = 0;i<NUMBER_OF_ROWS;i++){
			if(board[i][location] == value) return true;
		}
		return false;
	}

	private void checkAll3x3s(){
		
	}
	
	private void check3x3(int lat, int lon, int value){
		if(lat < 0 || lat > SQUARE_SIZE-1) throw new IllegalArgumentException();
		if(lon < 0 || lon > SQUARE_SIZE-1) throw new IllegalArgumentException();
		if(value < VALUE_MIN || value > VALUE_MAX) throw new IllegalArgumentException();
	}

	
	
	
	private void checkAllRows(){
//		for(int i = 0; i < )
	}
	
	private void checkLat(int lat){
		
	}

	private void checkRow(int row, int value){
		if(row < VALUE_MIN || row > NUMBER_OF_ROWS) throw new IllegalArgumentException();
		if(value < VALUE_MIN || value > VALUE_MAX) throw new IllegalArgumentException();

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
	
	private void printBoardBy3x3(){
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

	public static void main(String[] args) {
		SudokuBoard sb = new SudokuBoard();
		sb.loadBoard2();
//		set(4,0,-1);
		sb.printBoardBy3x3();
		System.out.println(sb.containsInRow(0,5));
	}
	
}
