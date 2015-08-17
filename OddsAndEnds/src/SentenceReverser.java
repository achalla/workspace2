import java.util.Scanner;

public class SentenceReverser {
	public static void main(String[] args) {
		String[] outputs = new String[100];
		String input, output = "";
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter sentence/phrase to be reversed: ");
		input = sc.nextLine();
		sc.close();
		
		outputs = input.split("\\s");
		for(String s: outputs) output = s+" "+output;
		
		System.out.println(output);
	}
}
