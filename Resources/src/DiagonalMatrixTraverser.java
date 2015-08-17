
public class DiagonalMatrixTraverser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*int x[][] = {{1, 2, 3},
                	 {4, 5, 6},
                	 {7, 8, 9}};*/
		
		int x[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
 int n = 4;
 for (int slice = 0; slice < 2 * n - 1; ++slice) {
 	System.out.print("Slice "+slice+": ");
 	int z = slice < n ? 0 : slice - n + 1;
 	for (int j = z; j <= slice - z; ++j) {
 		System.out.print(x[j][slice - j]+" ");
 	}
 	System.out.println();
 }
 //return 0;
}

}
