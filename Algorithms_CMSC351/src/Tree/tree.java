package Tree;

import java.util.Scanner;

public class tree {

	public static void prInt(int arr[], int mode){
		String modestr;
		modestr = (mode == 0? "ARR" : "PARENT_VERTICES"); 
		System.out.println("Current "+modestr+" Array: ");
		for(int i: arr){
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	public static int find(int arr[], int parents[], int vertex){
		int temp_vert = vertex, max = arr[vertex - 1], max_vert = vertex;
		
		while(temp_vert > 0){
			if(arr[temp_vert - 1] == max){
				max = arr[temp_vert - 1];
				if(temp_vert > max_vert) max_vert = temp_vert;
			}else if(arr[temp_vert - 1] > max){
				max = arr[temp_vert - 1];
				max_vert = temp_vert;
			}
			temp_vert = parents[temp_vert - 1];
		}
		
		
		
		
		
		
		
		
		
		
		/*while(temp3 > 0){
			if(arr[temp3 -1] >= max){
				max = arr[temp3 -1];
				if(temp3 > max_ind) max_ind = temp3;
			}
			temp3 = parents[temp3 - 1];
		}*/
		
		return max_vert;
	}
	
	/*public static void change(int vertex, int new_val){
		arr[vertex - 1] = new_val;		
	}
	*/
	
	public static void main(String[] args) {
		int n, q, temp;
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		q = sc.nextInt();
		
		//System.out.println("n and q acquired: "+n+" "+q);
		
		int arr[] = new int[n], parents[] = new int[n];
		//parents are the THE VERTICES, NOT the zero-based indices
		 parents[0] = -1;
		
		for(int i = 0; i < n; i++){
			arr[i] = sc.nextInt();
		}
			
		//prInt(arr,0);
		
		for(int i = 0; i < n-1; i++){
			temp = sc.nextInt();
			parents[sc.nextInt() -1] = temp;
		}
		
		//prInt(parents,1);
		
		/*System.out.println(sc.next());
		System.out.println(sc.nextInt());
		System.out.println(sc.next());
		System.out.println(sc.nextInt());
		System.out.println(sc.next());
		System.out.println(sc.nextInt());*/
		
		for(int i = 0; i < q; i++){
			String temp2 = sc.next();
			if(temp2.equals("find")){
				System.out.println(find(arr, parents, sc.nextInt()));
			}else if(temp2.equals("change")){
				//change(sc.nextInt(),sc.nextInt());
				arr[sc.nextInt() - 1] = sc.nextInt();		
			}else{
				//System.out.println("DANGER WILL ROBINSON");
			}
		}
		
	}

}
