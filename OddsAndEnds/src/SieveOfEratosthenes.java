//Anu Challa 9/25/14

import java.util.Scanner;


public class SieveOfEratosthenes {
	//The "Sieve of Eratosthenes" is a simple algorithm for finding 
	//all the primes up to some specified limit. 
	//http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max;
		System.out.println("Enter a max for the prime list: ");
		max = sc.nextInt();
		strain(max);
		sc.close();
	}
	
	public static int strain (int max){
		boolean arr[] = new boolean[max+1];//+1 so we can pretend it's 1-based indexing
		int i, ind = 0, count = 0;
		i = 2;
		while(i < arr.length){
			if(!arr[i]){
				System.out.println(i);
				count++;
				for(ind = 2; ind*i < arr.length; ind++){
					if(!arr[ind*i]) arr[ind*i] = true;
				}
			}
			i++;
		}
		System.out.println("Number of primes in list: "+count);
		return 0;
	}
}
