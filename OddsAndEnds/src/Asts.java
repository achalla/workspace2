//conditionals
//loops

import java.util.Scanner;

public class Asts {
	
	public static void main(String[] args) {
			//conditionals can be expressions or true/false or boolean variables
			//booleans variables can be true/false or expressions
			int a = 3, b = 3;
			String color = "blue";//String color = new String("blue");
			
			System.out.println(color);
			
			//while loops -VERY COMMON
			while(a < 0){
				a--;
				System.out.println("while "+a);
			}
			
			
			//do-while loop - not very common
			do{
				b--; //3->2
				System.out.println("dowhile: " +b); //prints 2
			}while(b < 0); //fails
			
			
			//for loops - VERY COMMON - bounded
			for(int i = 0;  i < 10; i++){
				System.out.println(i);
			}
			
			Scanner scan = new Scanner(System.in);
			scan.close();
	}

}
