import java.util.HashMap;
import java.util.Scanner;


public class FreqLetterFinder {
	
	/*Given a string, this function should find the most
	 * frequent character or letter to appear.
	 * 
	 * If more than one, will return any.
	 */
	
	public static char freqLetterFinder(String input){
		char[] cArr = input.toCharArray();
		char mode;
		int len = cArr.length;
		HashMap<Character,Integer> map = new HashMap<Character,Integer>(27);// = new Map<Character,Integer>();
		for(int i = 0; i < len; i++){
			//System.out.println(map.get(cArr[i]));
			if (map.get(cArr[i]) != null) map.put(cArr[i],map.get(cArr[i]) + 1);
			else map.put(cArr[i],1);
			//System.out.println(map.get(cArr[i]));
		}
		mode = getMode(map);
		return mode;
	}
	
	public static char getMode(HashMap<Character,Integer> hm){
		int most = 0;
		char mode = 0;
		for(Character c: hm.keySet()){
			//(delete first two conditionals if whitespace is okay)
			if (c > 65 && c < 122 && hm.get(c) >= most){
				mode = c;
				most = hm.get(c);
			}
		}
		return mode;
	}
	
	public static void main(String[] args){ 
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter sentence to find most frequent letters: ");
		String input = sc.nextLine();
		char c = freqLetterFinder(input);
		System.out.println("The most frequent letter is: " +c+ " ("+ (int)c + ")");
		sc.close();
	}

}
