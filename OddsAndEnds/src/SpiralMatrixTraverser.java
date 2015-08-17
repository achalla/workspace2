import java.util.Scanner;


public class SpiralMatrixTraverser {
	
	public static int currY = 0, currX = 0, startY = 0, startX = 0, endX, endY;
	
	public static void traverse(boolean arr[][],int n,int m){
		endX = n-1;
		endY = m-1;
		
		drawMatrix(arr,n,m);
		
		while(startY <= endY || startX <= endX){
			currY = startY;
			currX = startX;
				print("");
				if(currX == endX) arr[startY][currX] = true;
				while(currX < endX){
					printCurrVars();
					arr[startY][currX++] = true;
					drawMatrix(arr,n,m);
				}
				while(currY < endY){
					arr[currY++][endX] = true;
					drawMatrix(arr,n,m);
				}
			currX = endX;
				while(currX > startY){
					arr[currY][currX--] = true;
					drawMatrix(arr,n,m);
				}
			currY = endY;
				while(currY > startX){
					arr[currY--][currX] = true;
					drawMatrix(arr,n,m);
				}
			startY++; startX++;
			endX--; endY--;
			if(startX > endX || startY > endY) break;
		}
		print("Final:");
		drawMatrix(arr,n,m);
		return;
	}	
	
	
	public static void main(String[] args) {
		boolean testArr[][];
		int x,y;
		print("Enter dimensions of matrix to traverse counterclockwise: ");
		Scanner sc = new Scanner(System.in);
		print("How many rows? ");
		x = sc.nextInt();
		print("How many columns?");
		y = sc.nextInt();		
		testArr= new boolean[x][y];
		
		
		int n = testArr[0].length, m = testArr.length; //assumes this is a matrix (which it should be)
		print("n: "+n+" m: "+m);
		
		
		traverse(testArr,n,m);
		//drawMatrix(output,n,m);
		sc.close();
	}
	
	public static void drawMatrix(boolean output[][],int n, int m){
		//TEST: if "traverse" works, then all the X's should be O's
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(output[i][j]) System.out.print("O ");
				else System.out.print("X ");
			}
			System.out.print("\n");
		}
		print("");
	}
	
	
	
	public static void printCurrVars(){
		System.out.println("currY: "+currY+" currX: "+currX+" endX: "+endX+" endY: "+endY);
	}
	
	public static void printLimitVars(){
		System.out.println("startX: "+startX+" startY: "+startY+" endX: "+endX+" endY: "+endY);
	}
	
	public static void print(String x){
		System.out.println(x);
	}
}
