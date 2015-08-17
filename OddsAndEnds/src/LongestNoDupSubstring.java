import java.util.Scanner;


public class LongestNoDupSubstring {

	/**
	 * @param args
	 */
	
	public static int findDist(String input, char letter){
		int count = 0, maxCount = 0;
		char[] carr = input.toCharArray();
		for(char c: carr){
			if(c == letter){
				//System.out.println("hi"+(char)letter);
				if(count > maxCount) maxCount = count;
				count = 0;
			}
			else{
				//System.out.println("bye"+(char)letter);
				count++;
			}
		}
		System.out.println("count for "+letter+": "+maxCount);
		return maxCount;
	}
	
	public static void findSubstr(String input){
		int letter_count = 0, max = 0;
		char ret = 0;
		int[] lengths = new int[26];
		
		for(int i = 0; i < 26; i++){
			lengths[letter_count] = findDist(input,(char)(letter_count+97));
			letter_count++;
		}
		
		for(int i = 0; i <26; i++){
			if(lengths[i] > max){
				System.out.println("max i: "+i+", val: "+lengths[i]);
				max = lengths[i];
				ret = (char)(i+97);
			}
		}
		System.out.println("FINAL: "+ret);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		System.out.println(input);
		findSubstr(input);
		sc.close();
	}

}
