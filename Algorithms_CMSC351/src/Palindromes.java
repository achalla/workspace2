import java.util.Scanner;


public class Palindromes {


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
	
	
	public static int substrGenerator(String input){
		String sub;
	      int i, c, a, length, count = 0;
	 
	      
	 
	      length = input.length();   
	 
	      System.out.println("Substrings of \""+input+"\" are :-");
	 
	      for(a = 0; a < length - 1; a++){
		      for( c = a+1 ; c < length ; c++ )
		      {
		         for( i = 1 ; i <= length - c; i++ )
		         {
		        	 sub = input.substring(a,a+1)+input.substring(c, c+i);
		        	 
		        	 if(isPalindrome(sub)){
			        	 System.out.println(sub+" is a palindrome!");
			        	 count++;
		        	 }else{
		        		 System.out.println(sub);
		        	 }
		        	 
		           /* sub = input.substring(0,1)+input.substring(c, c+i);
		            if(isPalindrome(sub)){
		            	System.out.println(sub+" is a palindrome!");
		            	count++;
		            }
		            else System.out.println(sub);*/
		         }
		      }
	      }
	      
	      System.out.println("COUNT: "+count+" + LENGTH: "+length+" = "+(count+length));
	      return count+length;
	}
	
	
	public static void main(String[] args) {
		String input;
		
		Scanner sc = new Scanner(System.in);
	    System.out.println("Enter # queries: ");
	    int queries = sc.nextInt();
	    sc.nextLine();
	    for(int i = 0; i < queries; i++){
	    	System.out.println(i);
	    	System.out.println("Enter a string to print all substrings");
		    input  = sc.nextLine();
		    substrGenerator(input); 
	    }
	      
	      
	      ////
		/*System.out.println("Enter palindrome:");
		input = sc.nextLine();
		if(palFinder(input) == 1) System.out.println("True");
		else System.out.println("False");*/

	}

}
