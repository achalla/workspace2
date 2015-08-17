package Quartile;

import java.util.ArrayList;
import java.util.Scanner;

public class MaxHeap {
	
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
		
		System.out.println("index: "+index+" val: "+val);
		maxHeap.add(val);
		maxHeap.set(index,val);
		//printTree();
		//System.out.println("parent val of "+val+" is: "+parentVal(index)+" (index "+index+")");
		
		while(currVal > parentVal(index)){/*swap*/
			System.out.println("in while");
			tempPV = parentVal(index);
			maxHeap.set(parentInd(index),currVal);
			maxHeap.set(index,tempPV);
			index = parentInd(index);
			currVal = maxHeap.get(index);
			//printTree();
		}
		System.out.println("MaxHeap: ");
		printTree();
	}
	
	public static void remove(){
		int swapIndex = 0, tempI, tempVal;
		
		printTree();
		
		System.out.println("totElem: "+totElem);
		if(totElem > 0) maxHeap.set(0,maxHeap.get(--totElem));
		else{
			System.out.println("MAX HEAP EMPTY!");
			return;
		}
		if(totElem == 0){
			System.out.println("[ ]");
			return;
		}
		
		while(true){
			if(leftI(swapIndex) < totElem){
				System.out.println("a");
				System.out.println("swapIndex: "+swapIndex+" val: "+maxHeap.get(swapIndex)+" left: "+leftVal(swapIndex)+" right: "+rightVal(swapIndex));
				if(rightI(swapIndex) < totElem){//both
					System.out.println("b");
					if(rightVal(swapIndex) > leftVal(swapIndex) && rightVal(swapIndex) > maxHeap.get(swapIndex)){
						System.out.println("c");
						tempI = rightI(swapIndex);
						tempVal = maxHeap.get(swapIndex);
						maxHeap.set(swapIndex,rightVal(swapIndex));
						maxHeap.set(tempI,tempVal);
						swapIndex = tempI;
					}else if(leftVal(swapIndex) > rightVal(swapIndex) && leftVal(swapIndex) > maxHeap.get(swapIndex)){
						System.out.println("d");
						tempI = leftI(swapIndex);
						tempVal = maxHeap.get(swapIndex);
						maxHeap.set(swapIndex, leftVal(swapIndex));
						maxHeap.set(tempI,tempVal);
						swapIndex = tempI;
					}else{
						System.out.println("e");
						break;
					}
				}else{//left only
					System.out.println("f");
					if(leftVal(swapIndex) > maxHeap.get(swapIndex)){
						System.out.println("g");
						tempI = leftI(swapIndex);
						tempVal = maxHeap.get(swapIndex);
						maxHeap.set(swapIndex,leftVal(swapIndex));
						maxHeap.set(tempI,tempVal);
						swapIndex = tempI;
					}else{
						System.out.println("h");
						break;
					}
				}
			}else if(rightI(swapIndex) < totElem){ //right only
				System.out.println("i");
				if(rightVal(swapIndex) > maxHeap.get(swapIndex)){
					System.out.println("j");
					tempI = rightI(swapIndex);
					tempVal = maxHeap.get(swapIndex);
					maxHeap.set(swapIndex,rightVal(swapIndex));
					maxHeap.set(tempI,tempVal);
					swapIndex = tempI;
				}else{
					System.out.println("k");
					break;
				}
			}else{//neither
				System.out.println("m");
				break;
			}
		}
	System.out.println("MaxHeap: ");
	printTree();
	}
	
	public static void printTree(){
		for(int i = 0; i < totElem; i++){
			System.out.print(maxHeap.get(i)+" ");
		}
		System.out.println("");
	}
}
