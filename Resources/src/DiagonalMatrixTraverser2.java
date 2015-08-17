//MADE MYSELF 12/14/14
//TRAVERSES TOP HALF (EXCLUDING I=J) OF A MATRIX, TOP LEFT TO BOTTOM RIGHT DIAGONALLY
//USED FOR (DYNAMIC PROGRAMMING) COMPREHENSIVE PALINDROME SUBSTRING FINDER
//NAMED "palindromes2.java" IN THE MOST MUNDANE WAY POSSIBLE -- TO BE RENAMED...
public class DiagonalMatrixTraverser2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*int x[][] = {{1, 2, 3},
                	 {4, 5, 6},
                	 {7, 8, 9}};*/
		
		int x[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		//int x[][] = new int[4][4];
 int n = 4;
for(int ind = 1; ind < n; ind++){
	int a = 0;
	for(int ind2 = ind; ind2 < n; ind2++){
		System.out.println(x[a++][ind2]);
	}
}
 //return 0;
}

}
