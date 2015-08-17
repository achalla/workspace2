import java.util.Scanner;


public class StringReverser {


	public static String frontBack(String str) {
		char[] c = str.toCharArray();
		int length = c.length;
		for(int i = 0; i < length/2; i++){
			char temp = c[i];
			c[i] = c[length-i-1];
			c[length-i-1] = temp;  
		}
		return new String(c);
	}
	
	public static String backAround(String str) {
		  char c[] = new char[str.length()+2], lastChar = str.charAt(str.length()-1);
		  str.getChars(0,
		            str.length(),
		            c,
		            1);
		  c[0] = lastChar;
		  c[c.length-1] = lastChar;
		  return new String(c);
		}

	public static void main(String[] args){
//		Scanner sc = new Scanner(System.in);
//		String input = sc.nextLine();
//		System.out.println(frontBack(input));
//		System.out.println(backAround(input));
	}
}
