
package solutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ThreadedAVLTree4<T extends Comparable<T>> {
	
	public Node<T> root = null, threadStart = null;
	
	public static class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
		public Node<T> left = null, right = null, parent = null, leftThread = null, rightThread = null;
		public int balanceFactor;
		public T datum;
		
		public Node(T datum){
			this.datum = datum;
			balanceFactor = 0;
		}
		
		public int compareTo(Node<T> n){
			return this.datum.compareTo(n.datum);
		}
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 * */

	public void insert(T key) {//"done"
		if(root == null){
			//System.out.println("hii, added "+key+" (root)");
			root = new Node<T>(key);
		}
		else{
			insertHelper(key, root);
		}
		//System.out.println("Finished insert of "+key+":");
		printTreeByLevel();
	}
	
	public void balFactorizer(Node<T> n) {
		Node<T> temp = n;
		int currBF;
		while(temp != null){
			//System.out.println("BalFac temp.datum: "+temp.datum);
			currBF = getBF(temp);
			if(Math.abs(currBF) > 1){ //rotations needed
				
				if(currBF == 2){
					if(getBF(temp.right) > 0){
						rightRightRot(temp,temp.parent);
					}else if(getBF(temp.right) < 0){
						rightLeftRot(temp,temp.parent);
					}
					else{
						//System.out.println("node: "+temp.datum+" bal factor: "+currBF);
						throw new IllegalStateException();
					}
					
				}else if(currBF == -2){
					if(getBF(temp.left) > 0){
						//System.out.println("LEFT RIGHT ROTATION for "+temp.datum);
						leftRightRot(temp,temp.parent);
					}else if(getBF(temp.left) < 0){
						//System.out.println("LEFT LEFT ROTATION for "+temp.datum);
						leftLeftRot(temp,temp.parent);
					}
					else throw new IllegalStateException();
					
				}
				else throw new IllegalStateException();
			}
			else temp.balanceFactor = currBF;
			temp = temp.parent;
		}
	}
	
	public void balFacDown(Node<T> n){
		Queue<Node<T>> q = new PriorityQueue<Node<T>>();
		Node<T> temp;
		
		if(n == null) return;
		q.add(n);
		while (q.peek() != null){
			temp = q.remove();
			//System.out.println("BF for "+temp.datum+" was "+temp.balanceFactor);
			temp.balanceFactor = getBF(temp);
			//System.out.println("balFactorized "+temp.datum+" to bal factor: "+temp.balanceFactor);
			if(temp.left != null) q.add(temp.left);
			if(temp.right != null) q.add(temp.right);
		}
	}
	
	public void rightLeftRot(Node<T> input, Node<T> par) {
		Node<T> temp, newNode, oldNode;
				
		//rotate
		oldNode = input.right;
		newNode = oldNode.left;
		
		temp = newNode.right;
		newNode.right = oldNode;
		
		newNode.parent = oldNode.parent;
		oldNode.parent = newNode;
		input.right = newNode;
		
		oldNode.left = temp;
				
		//System.out.println("RL");
		//System.out.println("making call to RR: ");
		rightRightRot(input,input.parent);
		balFacDown(input.parent);
	}
	
	public void rightRightRot(Node<T> oldRoot, Node<T> par) {//"done" //3 2
		int parentStatus; //-1 if parent's leftChild, 1 if right child, 0 if par is null
		Node<T> temp, newRoot;
		if(par == null){
			//System.out.println("parent of node "+oldRoot.datum+" is null");
			if(oldRoot.datum != root.datum) System.out.println("DANGER WILL ROBINSON");
			parentStatus = 0;
		}else{
			//System.out.println("node: "+oldRoot.datum+" par: "+par.datum);
			if(oldRoot.parent.left.datum.compareTo(oldRoot.datum) == 0){//rot is left child
				parentStatus = -1;
				//System.out.println("rot is left child");
			}else if(oldRoot.parent.right.datum.compareTo(oldRoot.datum) == 0){//temp is right child
				parentStatus = 1;
				//System.out.println("rot is right child");
			}else{
				printParents();
				throw new IllegalStateException();
			}
		}
		//rotate
		newRoot = oldRoot.right;
		
		temp = newRoot.left;
		newRoot.left = oldRoot;
		
		newRoot.parent = oldRoot.parent;
		oldRoot.parent = oldRoot.right;
		
		oldRoot.right = temp;
		
		//update parent's child status:
		if(parentStatus == -1){
			newRoot.parent.left = newRoot;
		}else if(parentStatus == 1){
			newRoot.parent.right = newRoot;
		}else{
			//System.out.println("rotation done to root: (new root) "+newRoot.datum);
			root = newRoot;
		}
		
		//System.out.println("RR");
		balFacDown(newRoot);
	}
	
	public void leftRightRot(Node<T> input, Node<T> par) {
		Node<T> temp, newNode, oldNode;
		
		printTreeByLevel();
		
		//rotate
		oldNode = input.left;
		newNode = oldNode.right;
		
		temp = newNode.left;
		newNode.left = oldNode;
		
		newNode.parent = oldNode.parent;
		oldNode.parent = newNode;
		input.left = newNode;
		
		oldNode.right = temp;
				
		//System.out.println("LR");
		//System.out.println("making call to LL: ");
		leftLeftRot(input,input.parent);
		balFacDown(input.parent);
	}
	
	public void leftLeftRot(Node<T> oldRoot, Node<T> par) {//"done"
		int parentStatus; //-1 if parent's leftChild, 1 if right child, 0 if par is null
		Node<T> temp, newRoot;
		if(par == null){
			//System.out.println("parent of node "+oldRoot.datum+" is null");
			if(oldRoot.datum != root.datum) System.out.println("DANGER WILL ROBINSON");
			parentStatus = 0;
		}else{
			//System.out.println("node: "+oldRoot.datum+" par: "+par.datum);
			if(oldRoot.parent.left.datum.compareTo(oldRoot.datum) == 0){//rot is left child
				parentStatus = -1;
				System.out.println("rot is left child");
			}else if(oldRoot.parent.right.datum.compareTo(oldRoot.datum) == 0){//temp is right child
				parentStatus = 1;
				System.out.println("rot is right child");
			}else{
				throw new IllegalStateException();
			}
		}
		//rotate
		newRoot = oldRoot.left;
		
		temp = newRoot.right;
		newRoot.right = oldRoot;
		
		newRoot.parent = oldRoot.parent;
		oldRoot.parent = newRoot;
		
		oldRoot.left = temp;
		
		//update parent's child status:
		if(parentStatus == -1){
			newRoot.parent.left = newRoot;
		}else if(parentStatus == 1){
			newRoot.parent.right = newRoot;
		}else{
			//System.out.println("rotation done to root: (new root) "+newRoot.datum);
			root = newRoot;
		}
		
		//System.out.println("LL");
		balFacDown(newRoot);
		
	}
	
	public void insertHelper(T curr, Node<T> other) {
		if(curr.compareTo((T) other.datum) > 0){
			if(other.right == null){
				other.right = new Node<T>(curr);
				other.right.parent = other;
				balFactorizer(other.right);
			}else{
				insertHelper(curr, other.right);
			}
		}else if(curr.compareTo(other.datum) < 0){
			if(other.left == null){
				other.left = new Node<T>(curr);
				other.left.parent = other;
				balFactorizer(other.left);
			}else{
				insertHelper(curr,other.left);
			}
		}else{
			//System.out.println("Duplicate");
		}
	}
	
	public void terriblyInefficientThreader(Node<T> input){//threads everything below the input node
		//3 2 1 4 5 6 7 16 15 -1
		//6 2 7 1 4 9 3 5 8 -1
		Queue<Node<T>> q = new PriorityQueue<Node<T>>();
		Node<T> curr = null, lefty = null;
		
		if(input == null) return;
		
		
		inOrderHelper(input,q);
		while(q.peek() != null){
			curr = q.remove();
			curr.leftThread = lefty;
			if(lefty == null) threadStart = curr;
			else lefty.rightThread = curr;
			curr.rightThread = null;
			
			if(lefty != null) System.out.println("lefty: "+lefty.datum);
			else System.out.println("lefty: null");
			System.out.println("curr: "+curr.datum);
			//System.out.println();
			lefty = curr;
		}
	}
	
	public void inOrderHelper(Node<T> n, Queue<Node<T>> q){
		

		if(n.left != null){
			inOrderHelper(n.left,q);
		}
		if(n != null) q.add(n);
		if(n.right != null){
			inOrderHelper(n.right,q);
		}
		
	}
	
	public void checkThreads(){
		Node<T> temp = threadStart;
		while(temp!= null){
			//System.out.println("checkThreads. in order: "+temp.datum);
			temp = temp.rightThread;
		}
	}
	
	
	public void insertBST(T key) {//"done"
		if(root == null){
			//System.out.println("hii, added "+key+" (root)");
			root = new Node<T>(key);
		}
		else{
			insertBSTHelper(key, root);
		}
		//System.out.println("Finished insert of "+key);
	}
	
	public void insertBSTHelper(T curr, Node<T> other){
		if(curr.compareTo((T) other.datum) > 0){
			if(other.right == null){
				other.right = new Node<T>(curr);
				other.right.parent = other;
			}else{
				insertBSTHelper(curr, other.right);
			}
		}else if(curr.compareTo(other.datum) < 0){
			if(other.left == null){
				other.left = new Node<T>(curr);
				other.left.parent = other;
			}else{
				insertBSTHelper(curr,other.left);
			}
		}else{
			System.out.println("Duplicate");
		}
	}
	
	
	public Node<T> contains(T key){
		Node<T> temp;
		temp = root;
		while(temp != null){
			if(key.compareTo(temp.datum) > 0){
				temp = temp.right;
			}else if(key.compareTo(temp.datum) < 0){
				temp = temp.left;
			}else{//found it
				return temp;
			}
		}
		return null;
	}
	
	public T lookup(T key){
		Node<T> temp;
		temp = root;
		while(temp != null){
			if(key.compareTo(temp.datum) > 0){
				temp = temp.right;
			}else if(key.compareTo(temp.datum) < 0){
				temp = temp.left;
			}else{//found it
				return temp.datum;
			}
		}
		return null;
	}
	
	public T getRoot(){
		return root.datum;
	}
	
	
	public void findReplacementNode(){
		
	}
	
	
	
	
	
	

	public int height(){//done
		if(root == null) return -1;
		return getHeight(root);
	}
	
	public int getHeight(Node<? extends Comparable<?>> n){//versatile method, good for overall height of
		//44 17 78 32 50 88 48 62 84 92 80 82 -1
		
		//tree and for finding balanceFactor
		
		//conditionals based on # of children...
		if(n == null) return 0;
		if(n.left == null && n.right == null){ //BASE CASE: none
			return 0;
		}else if(n.left != null && n.right != null){//both
			//System.out.println("n: "+n.datum+" n.left: "+n.left.datum+" n.right: "+n.right.datum);
			return Math.max(getHeight(n.left) + 1,getHeight(n.right) + 1);
		}else if(n.right != null){//right
			//System.out.println("n: "+n.datum+" n.right: "+n.right.datum);
			return getHeight(n.right) + 1;
		}else{//left
			//System.out.println("n: "+n.datum+" n.left: "+n.left.datum);
			return getHeight(n.left) + 1;
		}
	}
	
	
	
	public int getBF(Node<T> n){
		int rightHeight = -1, leftHeight = -1;
		//if(n.right == null && (Integer) n.datum == 16) System.out.println("16 right is null");
		if(n.right == null) rightHeight = 0;
		else rightHeight = getHeight(n.right) + 1;
		
		//if(n.left == null && (Integer) n.datum == 16) System.out.println("16 left is null");
		if(n.left == null) leftHeight = 0;
		else leftHeight = getHeight(n.left) + 1;
		
		return rightHeight - leftHeight;
	}

	
	
	public boolean isEmpty(){//done
		if(root == null) return true;
		else return false;
	}
	
	
	public Iterator<T> inorderTraversal(){
		ArrayList<T> elements = new ArrayList<T>();
		Node<T> temp = threadStart;
		while(temp!= null){
			//System.out.println("checkThreads. in order: "+temp.datum);
			elements.add(temp.datum);
			temp = temp.rightThread;
		}
		return elements.iterator();
	}
	
	public boolean isLChild(Node<T> n){
		if(n == null) throw new IllegalStateException();
		else if(n.parent == null) throw new IllegalStateException();
		else if(n.parent.left != null && n.parent.left.datum.compareTo(n.datum) == 0) return true;
		else return false;
	}
	
	public void delete(T key){
		Node<T> deleteMe = contains(key);
		if(deleteMe == null) return;
		if(root == null) return;
		
		Node<T> toFactorize = deleteNode(deleteMe);
		if(toFactorize != null) balFactorizer(toFactorize);
	}
	
	public Node<T> deleteNode(Node<T> deleteMe){
		Node<T> toFactorize;

		if(deleteMe == null) return null;
		if(root == null) return null;
		
		if(deleteMe.left == null && deleteMe.right == null){//no children
			if(root.datum.compareTo(deleteMe.datum) == 0){//is root
				root = null;
				toFactorize = null;
			}else{//is not root
				if(isLChild(deleteMe)) deleteMe.parent.left = null;
				else deleteMe.parent.right = null;
				toFactorize =  deleteMe.parent;
			}
		}else if(deleteMe.left != null && deleteMe.right == null){//left child only
			if(root.datum.compareTo(deleteMe.datum) == 0){//is root
				root = deleteMe.left;
				toFactorize =  deleteMe.left;
			}else{//is not root
				if(isLChild(deleteMe)) deleteMe.parent.left = deleteMe.left;
				else deleteMe.parent.right = deleteMe.left;
				toFactorize =  deleteMe.parent;
			}
		}else if(deleteMe.right != null && deleteMe.left == null){//right child only
			if(root.datum.compareTo(deleteMe.datum) == 0){//is root
				root = deleteMe.right;
				toFactorize =  deleteMe.right;
			}else{//is not root
				if(isLChild(deleteMe)) deleteMe.parent.left = deleteMe.right;
				else deleteMe.parent.right = deleteMe.right;
				toFactorize =  deleteMe.parent;
			}
		}else{//both children
			Node<T> replacer = findLeftThread(deleteMe.left);//deleteMe.leftThread;
			if(replacer == null) throw new IllegalStateException();
			
			toFactorize = replacer.parent;
			deleteMe.datum = replacer.datum;
			deleteNode(replacer);

		}
		terriblyInefficientThreader(root);
		return toFactorize;
	}
	
	public Node<T> findLeftThread(Node<T> n){
		if(n == null) throw new IllegalStateException();
		Node<T> temp = n;
		while(temp.right != null){
			temp = n.right;
		}
		return temp;
	}
	
	
	
	/*
	 * 
	 * PRINTS
	 * 
	 * */
	
	
	public void printTreeByLevel(){
		Queue<Node<T>> q = new PriorityQueue<Node<T>>();
		Node<T> temp;
		
		if(root == null) return;
		q.add(root);
		System.out.println("root is "+root.datum);
		while (q.peek() != null){
			temp = q.remove();
			if (temp.left != null){
				System.out.println(temp.left.datum);
				q.add(temp.left);
			}
			if (temp.right != null){
				System.out.println(temp.right.datum);
				q.add(temp.right);
			}
		}
	}
	
	
	public void printParents(){//works
		Queue<Node<T>> q = new PriorityQueue<Node<T>>();
		Node<T> temp;
		
		if(root == null) return;
		System.out.println("tree root is "+root.datum);
		q.add(root);
		while (q.peek() != null){
			temp = q.remove();
			if(temp.parent != null )System.out.println(temp.datum+" parent: "+temp.parent.datum);
			if(temp.left != null) q.add(temp.left);
			if(temp.right != null) q.add(temp.right);
		}
	}
	
	public void printComputedBalFactors(){//works
		Queue<Node<T>> q = new PriorityQueue<Node<T>>();
		Node<T> temp;
		
		if(root == null) return;
		q.add((Node<T>) root);
		while (q.peek() != null){
			temp = q.remove();
			System.out.println(temp.datum+" bal factor should be: "+getBF(temp)+" and is: "+temp.balanceFactor);
			if(temp.left != null) q.add(temp.left);
			if(temp.right != null) q.add(temp.right);
		}
	}
	
	public void printBalFactors(){//works
		Queue<Node<T>> q = new PriorityQueue<Node<T>>();
		Node<T> temp;
		
		if(root == null) return;
		q.add((Node<T>) root);
		while (q.peek() != null){
			temp = q.remove();
			System.out.println(temp.datum+" bal factor: "+temp.balanceFactor);
			if(temp.left != null) q.add(temp.left);
			if(temp.right != null) q.add(temp.right);
		}
	}
	
	public void printHeights(){//works
		Queue<Node<T>> q = new PriorityQueue<Node<T>>();
		Node<T> temp;
		
		if(root == null) return;
		q.add((Node<T>) root);
		while (q.peek() != null){
			temp = q.remove();
			System.out.println(temp.datum+" height: "+getHeight(temp));
			if(temp.left != null) q.add(temp.left);
			if(temp.right != null) q.add(temp.right);
		}
	}
	
	
	
	public static void main(String[] args)  {
		//50 25 75 24 35 51 100 23 27 36 52 150 38 125 37 127 126 -1
		//44 17 78 32 50 88 48 62 84 92 80 82 -1
		int input;
		ArrayList<Integer> al = new ArrayList<Integer>();
		ThreadedAVLTree4<Integer> t = new ThreadedAVLTree4<Integer>();
		
		
		Scanner sc = new Scanner(System.in);
		while((input = sc.nextInt()) != -1){
			al.add(input);
		}
		
		sc.close();
		
		//al.add(1);
	/*	al.add(6);
		al.add(3);
		al.add(7);
		al.add(1);*/
		
		/*al.add(5);
		al.add(2);
		al.add(7);
		al.add(1);
		al.add(4);
		al.add(6);
		al.add(8);
		al.add(3);*/
		t.printTreeByLevel();
		t.checkThreads();
		
		for(int i = 0; i < al.size(); i++){
			t.insertBST(al.get(i));
		}
		t.printTreeByLevel();
		System.out.println("a");
		t.terriblyInefficientThreader(t.root);
		System.out.println("b");
		
		t.checkThreads();
		t.delete(78);
		t.balFacDown(t.root);
		//System.out.println("root: "+t.root.datum);
		//System.out.println("root.right: "+t.root.right.datum);
		//System.out.println("root.left: "+t.root.left.datum);
	
		t.printTreeByLevel();
		System.out.println("\n\n");
		//t.printParents();
		//t.printBalFactors();
		
		//System.out.println("Height is "+t.height());
		System.out.println("\n\n");
		System.out.println("FINAL: ");
		t.printTreeByLevel();
		//t.printHeights();
		t.printBalFactors();
		//System.out.println();
		t.printComputedBalFactors();
		t.terriblyInefficientThreader(t.root);
		t.checkThreads();
	}
}

