import java.util.Scanner;


public class palindromes2 {


	public static boolean isPalindrome(String s){
		if(s.equals("")) return false;
		for(int i = 0; i < s.length()/2; i++){
			if(s.charAt(i) != s.charAt(s.length()-1-i)) return false;	
		}
		return true;
	}
	
	public static int palFinderInt(String input){
		if(isPalindrome(input)) return 1;
		return 0;
	}
	
	public static void DoubleArrayPrinter(int input[][]){
		int length = input.length;
		for(int i = 0; i < length; i++){
			for(int j = 0; j < length; j++){
				System.out.print(input[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static int palFinderDP(String input){
		int length = input.length();
		int counterDP[][] = new int[length][length];
		for(int i = 0; i< length; i++){
			counterDP[i][i] = 1;
		}
		for(int ind = 1; ind < length; ind++){
			int a = 0;
			for(int ind2 = ind; ind2 < length; ind2++){
				if(input.charAt(a) == input.charAt(ind2)){
			//		System.out.println("char at a: "+input.charAt(a)+" char at ind2: "+input.charAt(ind2));
					counterDP[a][ind2] = counterDP[a+1][ind2] + counterDP[a][ind2-1] + 1;
					a++;
				}
				else{
			//		System.out.println("char at a: "+input.charAt(a)+" char at ind2: "+input.charAt(ind2));
					counterDP[a][ind2] = counterDP[a+1][ind2] + counterDP[a][ind2-1] - counterDP[a+1][ind2-1];
					a++;
				}//System.out.println(counterDP[a++][ind2]);
			}
		}
		
		return counterDP[0][length-1];
		
	}
	public static void main(String[] args) {
		String input;
		int output;
		
		Scanner sc = new Scanner(System.in);
	    //System.out.println("Enter # queries: ");
	    int queries = sc.nextInt();
	    sc.nextLine();
	    for(int i = 0; i < queries; i++){
	    	//System.out.println(i);
	    	//System.out.println("Enter a string to print all substrings");
		    input  = sc.nextLine().trim();
		    output = palFinderDP(input); 
		    System.out.println(output);
	  	}
	      
	}

}
