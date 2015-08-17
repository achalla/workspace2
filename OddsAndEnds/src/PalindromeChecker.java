import java.util.Scanner;


public class PalindromeChecker {
	public static void main(String[] args) {
		String input;
		boolean pal;
		int i;
		
		try(Scanner sc = new Scanner(System.in)){
			for(i = 0; i < 5; i++){
				System.out.println("Enter a palindrome: ");
				input = sc.nextLine();
				
				pal = palChecker(input);
				if (pal){
					System.out.println("Nice! You did it!");
					return;
				}
				else System.out.println("THAT'S NOT A PALINDROME. TRY AGAIN.\n");
				if(i > 0){
					System.out.println("You have " + (4-i) + " attempts remaining.");
					if (i < 4) 
						System.out.println("Psst! A palindrome is a word that reads the same backwards and forwards. Like \"racecar\" or \"a.\"");
				}
			}
			System.out.println("...why?");
		}
	}
	
	public static boolean palChecker(String input){
		char charInput[];
		int length, halfLength, i;
		
		charInput = input.toCharArray();
		
		length = input.length();
		halfLength = length/2;
		
		for(i = 0; i <= halfLength/2; i++){
			if(charInput[i] != charInput[length - i - 1]) return false;
		}
		return true;
	}

}
