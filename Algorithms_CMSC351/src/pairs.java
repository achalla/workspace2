import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class pairs {

	public static void main(String[] args) {
		ArrayList<Double> arr = new ArrayList<Double>();
		double n,k,n2,temp;
		int count = 0;
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
		for(double aj: arr){
			double e1 = (k+3*aj)/2;
			double e2 = (-k+3*aj)/2;
			if(Collections.binarySearch(arr,e1) > 0){
				count++;
				//System.out.println("("+aj+","+e1+")");
			}
			if(Collections.binarySearch(arr,e2) > 0){
				count++;
				//System.out.println("("+aj+","+e1+")");
			}
		}
		System.out.println(count);
		sc.close();
	}
}
