package Quartile;

import java.util.ArrayList;
import java.util.Scanner;

public class Quartile {
	
	/*Provides the first quartile of a stream of numbers from input in sub-O(n) time.
	 *  (reports mid stream)
	 *  TO BEGIN, RUN PROGRAM & TYPE:
	 *  "i x" where x is an int to add to the stream
	 *  "r" to report the current quartile of the stream
	 *  "end" to exit
	 */ 
	
	static int count = 0;
	
	public static void report(){
		int maxTop = 0;
		if(!MaxHeap.maxHeap.isEmpty()) maxTop = MaxHeap.maxHeap.get(0);
		System.out.println(maxTop);
	}	
	
	public static void balancer(int val){
		count++;
		if(MaxHeap.totElem == 0) MaxHeap.insert(val);
		else if(MaxHeap.totElem < ((count+3)/4)){//need to add to MaxHeap
			int maxTop = MaxHeap.maxHeap.get(0); 
			int minTop = MinHeap.minHeap.get(0);
			if(val < maxTop){//easy peasy
				MaxHeap.insert(val);
			}else{//we need to add it to the minheap, and add the curr top of minheap to the max heap
				int temp = minTop;
				MinHeap.remove();
				MinHeap.insert(val);
				MaxHeap.insert(temp);				
			}
		}else{//need to add to MinHeap
			int maxTop = MaxHeap.maxHeap.get(0);
			if(val > maxTop){//easy peasy
				MinHeap.insert(val);
			}else{//we need to add it to the minheap, and add the curr top of minheap to the max heap
				int temp = maxTop;
				MaxHeap.remove();
				MaxHeap.insert(val);
				MinHeap.insert(temp);				
			}
		}
	/*	System.out.println("MAXHEAP: ");
		MaxHeap.printTree();
		System.out.println("MINHEAP: ");
		MinHeap.printTree();*/
			
	}
	
	private static class MaxHeap {
	
	final static int MAX_ELEMENTS = 500000;
	
	static ArrayList<Integer> maxHeap = new ArrayList<Integer>(); /*max*/
	static int totElem = 0;
	
	public static int parentInd(int index){
		if(index == 0) return 0;
		if(index % 2 == 1) return (index/2);
		else return ((index/2) - 1);
	}
	
	public static int parentVal(int index){
		return maxHeap.get(parentInd(index));
	}
	
	public static int leftI(int index){
		return 2*index + 1;
	}
	
	public static int leftVal(int index){
		return  maxHeap.get(leftI(index));
	}
	
	public static int rightI(int index){
		return 2*index + 2;
	}
	
	public static int rightVal(int index){
		return maxHeap.get(rightI(index));
	}
	
	public static void insert(int val){
		int tempPV,index = totElem,currVal;
		currVal = val;
		if(maxHeap.contains(val)) return;
		totElem++;
		
		//System.out.println("index: "+index+" val: "+val);
		maxHeap.add(val);
		maxHeap.set(index,val);
		//printTree();
		//System.out.println("parent val of "+val+" is: "+parentVal(index)+" (index "+index+")");
		
		while(currVal > parentVal(index)){/*swap*/
			//System.out.println("in while");
			tempPV = parentVal(index);
			maxHeap.set(parentInd(index),currVal);
			maxHeap.set(index,tempPV);
			index = parentInd(index);
			currVal = maxHeap.get(index);
			//printTree();
		}
		//System.out.println("MaxHeap: ");
		//printTree();
	}
	
	public static void remove(){
		int swapIndex = 0, tempI, tempVal;
		
		//printTree();
		
		//System.out.println("totElem: "+totElem);
		if(totElem > 0) maxHeap.set(0,maxHeap.get(--totElem));
		else{
			//System.out.println("MAX HEAP EMPTY!");
			return;
		}
		if(totElem == 0){
			//System.out.println("[ ]");
			return;
		}
		
		while(true){
			if(leftI(swapIndex) < totElem){
				//System.out.println("a");
				//System.out.println("swapIndex: "+swapIndex+" val: "+maxHeap.get(swapIndex)+" left: "+leftVal(swapIndex)+" right: "+rightVal(swapIndex));
				if(rightI(swapIndex) < totElem){//both
					//System.out.println("b");
					if(rightVal(swapIndex) > leftVal(swapIndex) && rightVal(swapIndex) > maxHeap.get(swapIndex)){
//						System.out.println("c");
						tempI = rightI(swapIndex);
						tempVal = maxHeap.get(swapIndex);
						maxHeap.set(swapIndex,rightVal(swapIndex));
						maxHeap.set(tempI,tempVal);
						swapIndex = tempI;
					}else if(leftVal(swapIndex) > rightVal(swapIndex) && leftVal(swapIndex) > maxHeap.get(swapIndex)){
//						System.out.println("d");
						tempI = leftI(swapIndex);
						tempVal = maxHeap.get(swapIndex);
						maxHeap.set(swapIndex, leftVal(swapIndex));
						maxHeap.set(tempI,tempVal);
						swapIndex = tempI;
					}else{
//						System.out.println("e");
						break;
					}
				}else{//left only
//					System.out.println("f");
					if(leftVal(swapIndex) > maxHeap.get(swapIndex)){
//						System.out.println("g");
						tempI = leftI(swapIndex);
						tempVal = maxHeap.get(swapIndex);
						maxHeap.set(swapIndex,leftVal(swapIndex));
						maxHeap.set(tempI,tempVal);
						swapIndex = tempI;
					}else{
//						System.out.println("h");
						break;
					}
				}
			}else if(rightI(swapIndex) < totElem){ //right only
//				System.out.println("i");
				if(rightVal(swapIndex) > maxHeap.get(swapIndex)){
//					System.out.println("j");
					tempI = rightI(swapIndex);
					tempVal = maxHeap.get(swapIndex);
					maxHeap.set(swapIndex,rightVal(swapIndex));
					maxHeap.set(tempI,tempVal);
					swapIndex = tempI;
				}else{
//					System.out.println("k");
					break;
				}
			}else{//neither
//				System.out.println("m");
				break;
			}
		}
//	System.out.println("MaxHeap: ");
//	printTree();
	}
	
	public static void printTree(){
		for(int i = 0; i < totElem; i++){
			System.out.print(maxHeap.get(i)+" ");
		}
		System.out.println("");
	}
}

	private static class MinHeap {
		
		final static int MAX_ELEMENTS = 500000;
		
		static ArrayList<Integer> minHeap = new ArrayList<Integer>(); /*min*/
		//static int[] minHeap = new int[MAX_ELEMENTS];
		static int totElem = 0;
		
		public static int parentInd(int index){
			if(index == 0) return 0;
			if(index % 2 == 1) return (index/2);
			else return ((index/2) - 1);
		}
		
		public static int parentVal(int index){
			return minHeap.get(parentInd(index));
		}
		
		public static int leftI(int index){
			return 2*index + 1;
		}
		
		public static int leftVal(int index){
			return minHeap.get(leftI(index));
		}
		
		public static int rightI(int index){
			return 2*index + 2;
		}
		
		public static int rightVal(int index){
			return minHeap.get(rightI(index));
		}
		
		public static void insert(int val){
			int tempPV,index = totElem,currVal;
			currVal = val;
			if(minHeap.contains(val)) return;
			totElem++;
			
			
			minHeap.add(val);
			minHeap.set(index,val);
			//printTree();
			//System.out.println("parent val of "+val+" is: "+parentVal(index)+" (index "+index+")");
			
			while(currVal < parentVal(index)){/*swap*/
//				System.out.println("in while");
				tempPV = parentVal(index);
				minHeap.set(parentInd(index),currVal);
				minHeap.set(index,tempPV);
				index = parentInd(index);
				currVal = minHeap.get(index);
				//printTree();
			}
//			System.out.println("MinHeap: ");
//			printTree();
		}
		
		public static void remove(){
			int swapIndex = 0, tempI, tempVal;
			
//			printTree();
			
//			System.out.println("totElem: "+totElem);
			if(totElem > 0) minHeap.set(0,minHeap.get(--totElem));
			else{
//				System.out.println("MIN HEAP EMPTY!");
				return;
			}
			if(totElem == 0){
//				System.out.println("[ ]");
				return;
			}
			
			while(true){
				if(leftI(swapIndex) < totElem){
//					System.out.println("a");
//					System.out.println("swapIndex: "+swapIndex+" val: "+minHeap.get(swapIndex)+" left: "+leftVal(swapIndex)+" right: "+rightVal(swapIndex));
					if(rightI(swapIndex) < totElem){//both
//						System.out.println("b");
						if(rightVal(swapIndex) < leftVal(swapIndex) && rightVal(swapIndex) < minHeap.get(swapIndex)){
//							System.out.println("c");
							tempI = rightI(swapIndex);
							tempVal = minHeap.get(swapIndex);
							minHeap.set(swapIndex,rightVal(swapIndex));
							minHeap.set(tempI,tempVal);
							swapIndex = tempI;
						}else if(leftVal(swapIndex) < rightVal(swapIndex) && leftVal(swapIndex) < minHeap.get(swapIndex)){
//							System.out.println("d");
							tempI = leftI(swapIndex);
							tempVal = minHeap.get(swapIndex);
							minHeap.set(swapIndex,leftVal(swapIndex));
							minHeap.set(tempI,tempVal);
							swapIndex = tempI;
						}else{
//							System.out.println("e");
							break;
						}
					}else{//left only
//						System.out.println("f");
						if(leftVal(swapIndex) < minHeap.get(swapIndex)){
//							System.out.println("g");
							tempI = leftI(swapIndex);
							tempVal = minHeap.get(swapIndex);
							minHeap.set(swapIndex,leftVal(swapIndex));
							minHeap.set(tempI,tempVal);
							swapIndex = tempI;
						}else{
//							System.out.println("h");
							break;
						}
					}
				}else if(rightI(swapIndex) < totElem){ //right only
//					System.out.println("i");
					if(rightVal(swapIndex) < minHeap.get(swapIndex)){
//						System.out.println("j");
						tempI = rightI(swapIndex);
						tempVal = minHeap.get(swapIndex);
						minHeap.set(swapIndex,rightVal(swapIndex));
						minHeap.set(tempI,tempVal);
						swapIndex = tempI;
					}else{
//						System.out.println("k");
						break;
					}
				}else{//neither
//					System.out.println("m");
					break;
				}
			}
		
//			System.out.println("MinHeap: ");
//			printTree();
		}
		
		public static void printTree(){
			for(int i = 0; i < totElem; i++){
				System.out.print(minHeap.get(i)+" ");
			}
			System.out.println("");
		}
		
	}

	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String cmd;
		
		while(true){
			cmd = sc.next();
			if(cmd.equals("i")) {
				balancer(sc.nextInt());
			}
			if(cmd.equals("r")) report();
			if(cmd.equals("remove")) MinHeap.remove();
			if(cmd.equals("end")) break;
		}
		sc.close();

	}

}
