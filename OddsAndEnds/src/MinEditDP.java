import java.util.Scanner;
import java.util.Stack;

/*
 * Anu Challa
 * Created 8/1/15
 * 
 * Credit to Harry He for posting the algorithm
 * http://codercareer.blogspot.com/2011/12/no-25-edit-distance.html
 * 
 * My contribution: Created the interpretation of the algorithm/grid
 * (i.e, the pathInterpreter)
 * 
 * */

public class MinEditDP {
	
	private static int grid[][],numRows,numCols, deltaLength, editCount;
	private static String string1, string2, s1,s2;
	private static Stack<Coordinate> stack = new Stack<Coordinate>();
	
	public static int minEdit(String s1, String s2){

		gridInitializer(s1,s2);
		System.out.println(numRows+"x"+numCols);
		gridPopulater();
//		stack.push(new Coordinate(numRows,numCols));
		pathPusher(numRows - 1, numCols - 1);
		pathInterpreter();
		
		return grid[numRows-1][numCols-1];
	}
	
	//may want to change to constructor later
	public static void gridInitializer(String str1, String str2){
		s1 = str1.toLowerCase();
		s2 = str2.toLowerCase();
		numRows = s1.length()+1;
		numCols = s2.length()+1;
		deltaLength = numRows - numCols;
		editCount = deltaLength;
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
	
	public static void pathPusher(int rowInd, int colInd){
		int i = rowInd, j = colInd;
//		System.out.println("rowInd: "+rowInd+" colInd: "+colInd);
		
//		System.out.println("("+i+","+j+")");
		if(i >= 0 && j>= 0) stack.push(new Coordinate(i,j));
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
			}else if(i > 0){
				i-=1;
			}else if(j > 0){
				j-=1;
			}else if(i==0 && j==0){
				i-=1;
				j-=1;
			}else{
//				System.out.println("happened at i = "+i+" j at "+j);
				return;
			}
//			System.out.println("("+i+","+j+")");
//			if(i >= 0 && j>= 0) stack.push(new Coordinate(i,j));
			pathPusher(i,j);
	}
	
	private static class Coordinate{
		int x,y;
		
		public Coordinate(int a, int b){
			x = a;
			y = b;
		}
	}
	
	//i haven't looked up how to print a stack without modifying it
	@Deprecated
	public static void stackPrinter(){
		Coordinate curr;
		
		System.out.println("Printing stack:");
		
		while(!stack.isEmpty()){
			curr = stack.pop();
			System.out.println("i: "+curr.x+" j: "+curr.y);
		}
	}
	
	public static void pathInterpreter(){
		Coordinate curr, prev = null;
		int i, j, delta;
		System.out.println("DIRECTIONS:");
//		System.out.println("stack size is: "+stack.size());
		while(!stack.isEmpty()){
			curr = stack.pop();
			i = curr.x;
			j = curr.y;
			
			if(prev != null){
//				System.out.println("ij: "+grid[i][j]+" xy: "+grid[prev.x][prev.y]+" for i: "+i+" j: "+j);
				delta = grid[i][j] - grid[prev.x][prev.y];
				if(delta != 0){
//					System.out.println("delta: "+delta+" for i: "+i+" j: "+j);
					changer(i,j);
				}
			}
			prev = curr;
		}
	}
	public static void changer(int i, int j){
		if(editCount > 0){ // s1 longer than s2
			System.out.println("Delete "+s1.charAt(i-1)+", character "+(i-1)+" in original string");
			editCount--;
		}else if(editCount < 0){// s2 longer than s1
			System.out.println("Add "+s2.charAt(j-1)+", after character "+(j-1)+" in new string");
			editCount++;
		}else{ // same length
			System.out.println("Change "+s1.charAt(i-1)+" at character "+(i-1)+" to "+s2.charAt(j-1)+"["+(j-1)+"]");
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
		int min;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter String #1: ");
		string1 = sc.nextLine().trim();
		System.out.println("Enter String #2: ");
		string2 = sc.nextLine().trim();
		
		if(string1.equals(string2)){
			System.out.println("They're both the same, silly!");
			sc.close();
			return;
		}
		
		//min = minEdit("Sunday","Saturday");
		//min = minEdit("Sarada","Syamala");
		min = minEdit(string1,string2);
		gridPrinter();
		System.out.println("min is: "+min);
		sc.close();
	}
}
