

public class SpiralMatrixPrinter2 {
	
	public static int currY = 0, currX = 0, startY = 0, startX = 0, endX, endY;
	
	public static void traverse(int arr[][],int n,int m){
		endX = n-1;
		endY = m-1;
		
		
		while(startY <= endY || startX <= endX){
			currY = startY;
			currX = startX;
				if(currX == endX) System.out.print(arr[startY][currX] + " ");
				while(currX < endX){
					System.out.print(arr[startY][currX++] + " ");
				}
				while(currY < endY){
					System.out.print(arr[currY++][endX] + " ");
				}
			currX = endX;
				while(currX > startY){
					System.out.print(arr[currY][currX--] + " ");
				}
			currY = endY;
				while(currY > startX){
					System.out.print(arr[currY--][currX] + " ");
				}
			startY++; startX++;
			endX--; endY--;
			if(startX > endX || startY > endY) break;
		}
		return;
	}	
	
	
	public static void main(String[] args) {
		
		int testArr[][] = new int[][]{{11, 12, 13, 14, 15},
				{21, 22, 23, 24, 25},
				{31, 32, 33, 34, 35},
				{41, 42, 43, 44, 45}};
		
		
		int n = testArr[0].length, m = testArr.length; //assumes this is a matrix (which it should be)
		
		
		traverse(testArr,n,m);
	}
	
	
	
	public static void printCurrVars(){
		System.out.print("currY: "+currY+" currX: "+currX+" endX: "+endX+" endY: "+endY);
	}
	
	public static void printLimitVars(){
		System.out.print("startX: "+startX+" startY: "+startY+" endX: "+endX+" endY: "+endY);
	}
}