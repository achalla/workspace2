import java.util.Scanner;

public class Loops {
	public static void printRightTriangle(int size){
		for(int row = 0; row < size; row++){
			for(int col = (size-1); col >= 0; col--){
				if(col > row){
					System.out.print(" ");
				}else{
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}
	
	public static void stripes(int size){
		
		for(int row = 0; row < size; row++){
			for(int col = 0; col < size; col++){
				if((row+col+1)%4 == 0){
					System.out.print("*");
				}else{
					System.out.print("$");
				}
			}
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) {
		String command;
		int size;
		Scanner scan = new Scanner(System.in);
		
		
		command = scan.nextLine();
		size = scan.nextInt();
		
		if(command.equals("r")){
			printRightTriangle(size);
		}else if(command.equals("s")){
			stripes(size);
		}
		
		
		scan.close();
	}

}
