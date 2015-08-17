package Pairs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//done!

public class Pairs {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int n,k,n2,temp,count = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		n2 = n;
		k = sc.nextInt();
		
		//System.out.println("n: "+n+" k: "+k);
		while(n2-- > 0){
			
			if(!sc.hasNext()) {
				System.out.println("ERROR: FEWER NUMBERS THAN EXPECTED");
				return;
			}
			temp = sc.nextInt();
			arr.add(temp);
		}
		Collections.sort(arr);
		for(int aj: arr){
			int e1 = (k+3*aj)/2;
			int e2 = (-k+3*aj)/2;
			if(arr.contains(e1)){
				count++;
				//System.out.println("("+aj+","+e1+")");
			}
			else if(arr.contains(e2)){
				count++;
				//System.out.println("("+aj+","+e1+")");
			}
		}
		System.out.println(count);
		sc.close();
	}
}
