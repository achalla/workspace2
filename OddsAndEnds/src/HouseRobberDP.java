import java.util.ArrayList;
import java.util.Scanner;

/* When prompted to input numbers, type numbers with spaces as though into an array,
 * where each number represents the value of respective houses on a street. 
 * This algorithm is for the robber, who cannot loot two adjacent houses, to
 * maximize his loot.
 * 
 * ex: 
 * 
 * Input numbers, type any character to stop:
	1 2 4 8 3 5 9 3 2 a
	1 2 5 10 10 15 19 19 21 

	21
 */

public class HouseRobberDP {
	
	
	
	public static int[] maxLoot(ArrayList<Integer> al){
		int size = al.size(), count = 0;
		int houseVals[] = new int[size], lootDP[] = new int[size];
		
		for(Integer i: al){
			houseVals[count++] = i;
		}
		
		for(int i = 0; i < size; i++){
			if(i == 0) lootDP[i] = houseVals[0]; 
			else if(i == 1) lootDP[i] = max(houseVals[0],houseVals[1]); 
			else{
				lootDP[i] = max(houseVals[i]+lootDP[i-2],lootDP[i-1]); 
			}
		}
		
		return lootDP;
		
/*		for(int i: alist){
			System.out.println(i);
		}*/
	}
	
	public static int max(int a, int b){
		if(a > b) return a;
		else return b;
	}
	
	public static void main(String[] args) {
		int[] ret;
		ArrayList<Integer> al = new ArrayList<Integer>();
		System.out.println("Input numbers, type any character to stop:");
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()){
			al.add(sc.nextInt());
		}
		//System.out.println(al.toString());
		ret = maxLoot(al);
		/*for(int i: ret){
			System.out.print(i+" ");
		}*/
		System.out.print("\n");
		System.out.println(ret[al.size()-1]);
		sc.close();
	}

}
