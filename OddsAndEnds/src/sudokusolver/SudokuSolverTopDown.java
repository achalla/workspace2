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
	 * I have called each of the 3 groups of 3 horizontal rows a "latitude" (abbreviated "lat")
	 * I have called each of the 3 groups of vertical columns a "longitude" (abbreviated "lon" --
	 * 		 "long" isn't gonna work as a variable name)
	 * 
	 * I have called each of the 3 horizontal or vertical strips of three cells within a 3x3 a "triplet"
	 */
	
//	private 
	int[][] board;
//	private
	int[][][] hints;
	boolean changesMade, changesMadeTotal;
	
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
		System.out.println();
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
		if(row < 0 || row > 8) throw new IllegalArgumentException();
		if(value < 1 || value > 9) throw new IllegalArgumentException();
		System.out.println("containsInRow. row: "+row+" value: "+value);
		for(int i = 0;i<9;i++){
			if(row==1) System.out.print(board[row][i]+" ");
			if(board[row][i] == value) return i;
		}
		if(row==1) System.out.println();
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
	

	
	int findTheOneOfNine(int i, int j){
		for(int k = 1; k<10;k++){
			if(hints[i][j][k] != 0) return k;
		}
		throw new BadAssumptionException();
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
	
	void scanForSolos(){
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
	
	
	void checkLat2(int lat, int value){
		//where does it say that there is an odd row or an odd grid? BAD ASSUMPTION
		int oddRow, oddGrid, oddCol;
		oddRow = findOddRowInLat(lat,value);
		oddGrid = findOddGridInLat(lat,value);
		System.out.println("oddRow: "+oddRow+" oddGrid: "+oddGrid);
		if(oddRow == -1 || oddGrid == -1){
			System.out.println("returning");
			return;
		}
		oddCol = getColFromGrid(oddGrid);
		checkTriplet2(oddRow,oddCol,value);
		
	}

	void checkTriplet2(int rowStart, int colStart, int value){
		int a,b,c, numEmptyCells;
		a = board[rowStart][colStart];
		b = board[rowStart][colStart+1];
		c = board[rowStart][colStart+2];
		numEmptyCells = numNonpositives(a,b,c);
		switch(numEmptyCells){
		case 0:
			throw new BadAssumptionException();
		case 1: //Only one empty -> fill it
			int invalid = findNonpositiveOneOfThree(a,b,c);
			set(rowStart,colStart+invalid,value);
			break;
		case 2: //Two empty -> see if we can eliminate one and fill the other
			findValidOneOfThree(a,b,c);
			int x=1,y=1;
			if(a<=0){//potential col
				x=colStart;
			}
			if(b<=0){//potential col
				if(x==1) x=colStart+1;
				else y=colStart+1;
			}
			if(c<=0){//potential col
				y=colStart+2;
			}
			if(containsInCol(x,value)>0) set(rowStart,y,value);
			else if(containsInCol(y,value)>0) set(rowStart,x,value);
			break;
		case 3:
			int potColA,potColB,potColC;
			potColA = containsInCol(colStart,value);
			potColB = containsInCol(colStart+1,value);
			potColC = containsInCol(colStart+2,value);
			if(numNonpositives(potColA,potColB,potColC)!=1) return;
			int inval = findNegativeOneOfThree(potColA,potColB,potColC);
			set(rowStart,colStart+inval,value);
			break;
		default:
			throw new BadAssumptionException();
		}
	}
	
	void set(int row, int col, int value){
		board[row][col] = value;
		hints[row][col][0] = 0;
		changesMade = true;
		changesMadeTotal = true;
	}
	
	public int findValidOneOfThree(int a, int b, int c){
		if(numNonpositives(a,b,c)!=2) throw new BadAssumptionException();
		return (a>0?0:(b>0?1:2));
	}
	
	public int findNegativeOneOfThree(int a, int b, int c){
		if(numNegatives(a,b,c)!=1) throw new BadAssumptionException();
		return (a<0?0:(b<0?1:2));
	}
	
	public int findNonpositiveOneOfThree(int a, int b, int c){
		if(numNonpositives(a,b,c)!=1) throw new BadAssumptionException();
		return (a<=0?0:(b<=0?1:2));
	}
	
	//this method disgusts me. but...i think it works so...//TODO: fix this 
		void checkTriplet(int rowStart, int colStart,int value){
			int a,b,c;
			a = board[rowStart][colStart];
			b = board[rowStart][colStart+1];
			c = board[rowStart][colStart+2];
			System.out.println("rowStart: "+rowStart+" colStart: "+colStart);
			System.out.println("a: "+a+" b: "+b+" c: "+c);
			if(a!=0){//a isn't empty
				if(b!=0){//b isn't empty
					if(c!=0) throw new BadAssumptionException();//how are they all filled?
					else {
						//only one left
						set(rowStart,colStart+2,value);
					}
					
				}else{ //b is empty
					if(c!=0){
						set(rowStart,colStart+1,value); //c isn't -- only one left
					}
					else{
						//gotta check b and c
						if(containsInCol(colStart+1,value)>0) {
							set(rowStart,colStart+2,value);
						}
						else if(containsInCol(colStart+2,value)>0) {
							set(rowStart,colStart+1,value);
						}
					}
				}
			}else{//a is empty
				if(b!=0){//b isn't
					if(c!=0){
						set(rowStart,colStart,value);//c isn't -- only one left

					}
					else{
						//gotta check a and c
						if(containsInCol(colStart,value)>0) {
							set(rowStart,colStart+2,value);
						}
						else if(containsInCol(colStart+2,value)>0){
							set(rowStart,colStart,value);
						}
					}
				}else{//b is empty
					if(c!=0){
						//gotta check a and b
						if(containsInCol(colStart+1,value)>0){
							set(rowStart,colStart,value);
						}
						else if(containsInCol(colStart,value)>0) {
							set(rowStart,colStart+1,value);
						}
					}
					else{
						int count = 0;
						//gotta check a,b, and c
						int a2,b2,c2;
						a2=containsInCol(colStart,value);
						b2=containsInCol(colStart+1,value);
						c2=containsInCol(colStart+2,value);
								
						if(a2>0) count++;
						if(b2>0) count++;
						if(c2>0) count++;
						if(count==2){
							set(rowStart,colStart+findNegativeOneOfThree(a2,b2,c2),value);
						}
					}
				}
			}
		}
	
	
	
	int findOddRowInLat(int lat, int value){
		int startRow = lat*3;
		int contA,contB,contC, oddOne;

		contA = containsInRow(startRow,value);
		contB = containsInRow(startRow+1,value);
		contC = containsInRow(startRow+2,value);

		System.out.println("contA, contB, contC: "+contA+","+contB+","+contB);
		
		if(numNegatives(contA,contB,contC)!=1) return -1;

		oddOne = findNegativeOneOfThree(contA,contB,contC);
		return startRow+oddOne;
	}
	
	int findOddGridInLat(int lat, int value){
		System.out.println("lat: "+lat+" value: "+value);
		
		int startRow = lat*3, startGrid = lat*3;
		int rowContA,rowContB,rowContC, oddOne;
		int gridContA,gridContB,gridContC;

		//equals column index or -1 if not found
		rowContA = containsInRow(startRow,value);
		rowContB = containsInRow(startRow+1,value);
		rowContC = containsInRow(startRow+2,value);
		
		//2 columns with value in them will be grid #, third will be -1
		gridContA = (rowContA>0)?getGrid(startRow,rowContA):-1;
		gridContB = (rowContB>0)?getGrid(startRow+1,rowContB):-1;
		gridContC = (rowContC>0)?getGrid(startRow+2,rowContC):-1;
		
		if(numNegatives(gridContA,gridContB,gridContC)!=1) return -1;
		oddOne = findNegativeOneOfThree(gridContA,gridContB,gridContC);
		return startGrid+oddOne;
	}
	
	int numNegatives(int a, int b, int c){
		int count = 0;
		if(a<0) count++;
		if(b<0) count++;
		if(c<0) count++;
		return count;
	}
	
	int numNonnegatives(int a, int b, int c){
		int count = 0;
		if(a>=0) count++;
		if(b>=0) count++;
		if(c>=0) count++;
		return count;
	}
	
	int numNonpositives(int a, int b, int c){
		int count = 0;
		if(a<=0) count++;
		if(b<=0) count++;
		if(c<=0) count++;
		return count;
	}
	
	int numPositives(int a, int b, int c){
		int count = 0;
		if(a>0) count++;
		if(b>0) count++;
		if(c>0) count++;
		return count;
	}
	
	//just use checkTriplet
	/*int getTripletFromStartRowAndCol(int row, int grid, int value){
		int a,b;
		//from grid, let's get the cell coordinate
		for(int i = 0; i<3;i++){
			for(int j=0;j<3;j++){
				if(blockMap[i][j]==grid){//this is it!
					a = row;
					b = j*3;
					checkTriplet(a,b,value);
				}
			}
		}
		return -1;
	}*/
	
	
	
/*	void checkLat(int lat, int value){
		//TODO check arguments
		int rowA, rowB, rowC, gridA, gridB, gridC, count=0;
		int contA, contB, contC, contSum;
		int oddGrid, grid, row;
		rowA = lat*3;
		rowB = rowA+1;
		rowC = rowA+2;
		
		System.out.println("value: "+value);
		contA = containsInRow(rowA,value);
		if(contA>0) System.out.println("ugh. rowA: "+rowA+" value: "+value);
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
		
		
		System.out.println("gridA: "+gridA+" gridB: "+gridB+" gridC: "+gridC);
		System.out.println("contA: "+contA+" contB: "+contB+" contC: "+contC);

		
		oddGrid = findInvalidOneOfThree(gridA,gridB,gridC);
		
		grid = lat*3+oddGrid;
		
		int oddRow = findInvalidOneOfThree(contA,contB,contC);
		System.out.println("oddGrid is "+oddGrid);
		System.out.println("oddRow is "+oddRow);
		System.out.println("row A: "+rowA+" rowB: "+rowB+" rowC: "+rowC);
		row = rowA+oddRow;
		grid = rowA+oddGrid;// this is because rowA = lat*3
		System.out.println("oddRow is "+oddRow+" and grid is "+grid);
		System.out.println("row is: "+row);
		
		
		int col = getColFromGrid(grid);
		checkTriplet(row,col,value);
		
		
	}*/
	
	
	int getColFromGrid(int grid){
		return grid%3*3;
	}
	
	/*
	 int checkTripletFromRowAndGrid(int row, int grid, int value){
		int a,b;
		//from grid, let's get the cell coordinate
		for(int i = 0; i<3;i++){//out of all blocks in a board...
			for(int j=0;j<3;j++){
				if(blockMap[i][j]==grid){//...this is it!
					a = row;
					b = j*3;
					checkTriplet(a,b,value);
				}
			}
		}
		return -1;
	}
	 */

	
	

	//want to make this recursive next
	void solve(){
		printBoardBy3x3();
		populateHints();
		printHints();
		do{
			changesMadeTotal=false;
		do{
			changesMade = false;
			scanForSolos();
			printBoardBy3x3();	

		}while(changesMade);

		do{
			changesMade = false;
			for(int val = 1; val<=9;val++){
				for(int lat = 0; lat<3;lat++){
					checkLat2(lat, val);
				}
				printBoardBy3x3();	
			}
		}while(changesMade);
		}while(changesMadeTotal);
	}

	
	public static void main(String[] args) {
		SudokuSolverTopDown ss = new SudokuSolverTopDown();
		ss.loadBoard1();
		
		
		/*ss.printBoardBy3x3();		
		ss.printHints(8,8);
		ss.populateHints();
		ss.printHints(8,8);
		int grid = ss.getGrid(8,8);
		System.out.println(grid);
		System.out.println(ss.containsInGrid(grid,7));
		System.out.println("startRow "+grid/3*3+" startCol:"+grid%3*3);
		*/
		
		
		//ss.printHints();
		ss.printBoardBy3x3();
		ss.scanForSolos();
		ss.printBoardBy3x3();
//		ss.printHints();
//		ss.scanForSolos();
//		ss.printBoardBy3x3();
		
//		ss.checkLat(0,9);
//		ss.printBoardBy3x3();	
		
//		ss.solve();
	}
}