import java.util.Scanner;
import java.util.Stack;


public class MinEdit {
	
	private static int grid[][],numRows,numCols;
	private static String string1, string2, s1,s2;
	private static Stack<Coordinate> stack = new Stack<Coordinate>();
	
	public static int minEdit(String s1, String s2){
		gridInitializer(s1,s2);
		gridPopulater();
		explainer(numRows - 1, numCols - 1);
//		System.out.println(stack);
		stackPrinter();
		return grid[numRows-1][numCols-1];
	}
	
	public static void gridInitializer(String str1, String str2){
		s1 = str1.toLowerCase();
		s2 = str2.toLowerCase();
		numRows = s1.length()+1;
		numCols = s2.length()+1;
		//primitive ints initialize to 0:
		grid = new int[numRows][numCols]; 
	}
	
	public static void gridPopulater(){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				grid[i][j] = gridPopulaterHelper(i,j);
			}
		}
	}
	
	public static int gridPopulaterHelper(int i, int j){
		//set up the borders
		if(j == 0) return i;
		if(i == 0) return j;
		
		//guts and innards
		char c1 = s1.charAt(i-1),c2 = s2.charAt(j-1);
		if(c1 == c2)return grid[i-1][j-1];
		else return (int) Math.min(grid[i-1][j],Math.min(grid[i][j-1], grid[i-1][j-1])) + 1;
	}
	
	public static void explainer(int rowInd, int colInd){
		int i = rowInd, j = colInd, currMin, nextMin, deltaMin;
//		while(i > -1 && j > -1){
			if(i > 0 && j > 0){
				int a,b,c;
				a = grid[i-1][j];//up one
				b = grid[i][j-1];//left one
				c = grid[i-1][j-1];//diagonal left & up
				
				if(a < b){
					if(c <= a){//c smallest
						i-=1;
						j-=1;
					}else{//a smallest
						i-=1;
					}
				}else{// b < a
					if(c <= b){//c smallest
						i-=1;
						j-=1;
					}else{//b smallest
						j-=1;
					}
				}
				
				/*if(a < b){ //go up one
					i-=1;
					if(c < b){//go diagonal
						j-=1;
					}
				}else{
					j-=1;
					if(c < b){
						i-=1;
					}
				}*/
				
			}else if(i > 0){
				i-=1;
			}else if(j > 0){
				j-=1;
			}else if(i==0 && j==0){
				i-=1;
				j-=1;
			}else{
				return;
			}
			System.out.println("("+i+","+j+")");
			currMin = grid[rowInd][colInd];
//			nextMin = grid[i][j];
//			deltaMin = currMin - nextMin;
		/*	if(deltaMin!= 0){
				
			}*/
			if(i >= 0 && j>= 0) stack.push(new Coordinate(i,j));
			explainer(i,j);
	}
	
	private static class Coordinate{
		int x,y;
		
		public Coordinate(int a, int b){
			x = a;
			y = b;
		}
	}
	
	public static void stackPrinter(){
		Coordinate curr;
		
		System.out.println("Printing stack:");
		/*for(Coordinate curr: stack){
			System.out.println("i: "+curr.x+" j: "+curr.y);
		}*/
		
		while(!stack.isEmpty()){
			curr = stack.pop();
			System.out.println("i: "+curr.x+" j: "+curr.y);
		}
		
	}

	public static void gridPrinter(){
		int numCols = grid[0].length, numRows = grid.length;
		for(int i = -1; i < numRows; i++){
			for(int j = -1; j < numCols; j++){	
				if(i == -1){
					if(j == -1){
						System.out.print("   ");
					}else if(j < numCols-1){
						System.out.print(" "+s2.charAt(j));
					}
				}else{
					if(j == -1) {
						if(i == 0){
							System.out.print("  ");
						}else{
							System.out.print(s1.charAt(i-1)+" ");
						}
					}else{
						System.out.print(grid[i][j]+" ");
					}
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		string1 = sc.nextLine();
		string2 = sc.nextLine();
		int min = minEdit("Sunday","Saturday");
		gridPrinter();
		System.out.println("min is: "+min);
		sc.close();
	}
}
