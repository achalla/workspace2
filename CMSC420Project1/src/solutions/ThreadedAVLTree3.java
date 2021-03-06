package solutions;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ThreadedAVLTree3<T extends Comparable<T>> {
	
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
			System.out.println("hii, added "+key+" (root)");
			root = new Node<T>(key);
		}
		else{
			insertHelper(key, root);
		}
		System.out.println("Finished insert of "+key+":");
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
						System.out.println("node: "+temp.datum+" bal factor: "+currBF);
						throw new IllegalStateException();
					}
					
				}else if(currBF == -2){
					if(getBF(temp.left) > 0){
						System.out.println("LEFT RIGHT ROTATION for "+temp.datum);
						leftRightRot(temp,temp.parent);
					}else if(getBF(temp.left) < 0){
						System.out.println("LEFT LEFT ROTATION for "+temp.datum);
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
			System.out.println("BF for "+temp.datum+" was "+temp.balanceFactor);
			temp.balanceFactor = getBF(temp);
			System.out.println("balFactorized "+temp.datum+" to bal factor: "+temp.balanceFactor);
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
				
		System.out.println("RL");
		System.out.println("making call to RR: ");
		rightRightRot(input,input.parent);
		balFacDown(input.parent);
	}
	
	public void rightRightRot(Node<T> oldRoot, Node<T> par) {//"done" //3 2
		int parentStatus; //-1 if parent's leftChild, 1 if right child, 0 if par is null
		Node<T> temp, newRoot;
		if(par == null){
			System.out.println("parent of node "+oldRoot.datum+" is null");
			if(oldRoot.datum != root.datum) System.out.println("DANGER WILL ROBINSON");
			parentStatus = 0;
		}else{
			System.out.println("node: "+oldRoot.datum+" par: "+par.datum);
			if(oldRoot.parent.left.datum == oldRoot.datum){//rot is left child
				parentStatus = -1;
				System.out.println("rot is left child");
			}else if(oldRoot.parent.right.datum == oldRoot.datum){//temp is right child
				parentStatus = 1;
				System.out.println("rot is right child");
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
			System.out.println("rotation done to root: (new root) "+newRoot.datum);
			root = newRoot;
		}
		
		System.out.println("RR");
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
				
		System.out.println("LR");
		System.out.println("making call to LL: ");
		leftLeftRot(input,input.parent);
		balFacDown(input.parent);
	}
	
	public void leftLeftRot(Node<T> oldRoot, Node<T> par) {//"done"
		int parentStatus; //-1 if parent's leftChild, 1 if right child, 0 if par is null
		Node<T> temp, newRoot;
		if(par == null){
			System.out.println("parent of node "+oldRoot.datum+" is null");
			if(oldRoot.datum != root.datum) System.out.println("DANGER WILL ROBINSON");
			parentStatus = 0;
		}else{
			System.out.println("node: "+oldRoot.datum+" par: "+par.datum);
			if(oldRoot.parent.left.datum == oldRoot.datum){//rot is left child
				parentStatus = -1;
				System.out.println("rot is left child");
			}else if(oldRoot.parent.right.datum == oldRoot.datum){//temp is right child
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
			System.out.println("rotation done to root: (new root) "+newRoot.datum);
			root = newRoot;
		}
		
		System.out.println("LL");
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
		Node<T> curr = null, lefty = null, righty = null;
		
		System.out.println("woof");
		
		inOrderHelper(input,q);
		/*while(!q.isEmpty()){
			System.out.println(q.remove().datum);
		}*/
		
		System.out.println("Q contents (in-order trav):");
		while(q.peek() != null){
			curr = q.remove();
			System.out.println(curr.datum);
			curr.leftThread = lefty;
			if(lefty == null) threadStart = curr;
			else lefty.rightThread = curr;
			curr.rightThread = null;
			
			if(lefty != null) System.out.println("lefty: "+lefty.datum);
			else System.out.println("lefty: null");
			System.out.println("curr: "+curr.datum);
			System.out.println();
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
			System.out.println("checkThreads. in order: "+temp.datum);
			temp = temp.rightThread;
		}
	}
	
	
	public void insertBST(T key) {//"done"
		if(root == null){
			System.out.println("hii, added "+key+" (root)");
			root = new Node<T>(key);
		}
		else{
			insertBSTHelper(key, root);
		}
		System.out.println("Finished insert of "+key);
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
	
	
	public void delete3(T key){
		Node<T> x, z;
		
		x = contains(key);
		
		if(x == null) return;
		if(x.right != null && x.left != null){//both children
			Node<T> y, temp;
			y = x.leftThread;
			z = x.leftThread;
			
			temp = y.parent;
			y.parent = x.parent;
			x.parent = temp; //should be null if equal
			
			temp = y.left;
			y.left = x.left;//should be null if equal
			if(x.left != null) x.left.parent = y;
			x.left = temp;
			
			temp = y.right;
			y.right = x.right; //should be null if equal
			if(x.right != null) x.right.parent = y;
			x.right = temp;
			
			temp = y.leftThread;
			y.leftThread = x.leftThread;//should be null if equal
			x.leftThread = temp;
			
			temp = y.rightThread;
			y.rightThread = x.rightThread;//should be null if equal
			x.rightThread = temp;
		}else{
			z = x;
		}
			if(z.left != null && z.right != null){//both children? => error //WHAT EVEN IS Z RIGHT NOW -- CLARIFY
				throw new IllegalStateException();
			}else if(z.left != null){//left child
				if(z.parent.left.datum.compareTo(z.datum) == 0){
					z.parent.left = z.left;
					z.left.parent = z.parent;
				}
				else{
					z.parent.right = z.left;
					z.left.parent = z.parent;
				}
			}else if(z.right != null){//right child
				if(z.parent.left.datum.compareTo(z.datum) == 0){
					z.parent.left = z.right;
					z.right.parent = z.parent;
				}
				else{
					z.parent.right = z.right;
					z.right.parent = z.parent;
				}
			}else{//no children
				if(z.parent.left.datum.compareTo(z.datum) == 0){
					z.parent.left = null;
				}
				else{
					z.parent.right = null;
				}
			}
			
			//root
			if(z.parent == null) root = z;
			
			z = null;
			
	}
	
	public void delete(T key){//todo: clear redundant code
		Node<T> deleteMe;
		
		Scanner sc = new Scanner(System.in);
		
		
		deleteMe = contains(key);
		
		if(deleteMe == null) return;
		else{
			if(deleteMe.left == null && deleteMe.right == null){//no children
				if(deleteMe.leftThread != null) deleteMe.leftThread.rightThread = deleteMe.rightThread;
				if(deleteMe.rightThread != null) deleteMe.rightThread.leftThread = deleteMe.leftThread;
				if(deleteMe.parent == null) root = null; //no parent, is root
				else{//has parent
					if(deleteMe.parent.left.datum.compareTo(deleteMe.datum) == 0) deleteMe.parent.left = null; //should be null
					else deleteMe.parent.right = null;
				}
				
			}else if(deleteMe.left != null && deleteMe.right == null){//only has left child
				
				Node<T> temp;
				
				if(deleteMe.leftThread != null) deleteMe.leftThread.rightThread = deleteMe.rightThread;
				if(deleteMe.rightThread != null) deleteMe.rightThread.leftThread = deleteMe.leftThread;
				if(deleteMe.parent == null) {} //no parent, is root
				else{//has parent
					if(deleteMe.parent.left != null && deleteMe.parent.left.datum.compareTo(deleteMe.datum) == 0){//make this change to alllllllllllllll
						deleteMe.parent.left = deleteMe.left; //should be null
						if(deleteMe.left != null) deleteMe.left.parent = deleteMe.parent;
					}else{
						deleteMe.parent.right = deleteMe.left;
						if(deleteMe.right != null) deleteMe.right.parent = deleteMe.parent;
					}
				}
				
				
				
			}else if(deleteMe.right != null && deleteMe.left == null){//only has right child
				if(deleteMe.leftThread != null) deleteMe.leftThread.rightThread = deleteMe.rightThread;
				if(deleteMe.rightThread != null) deleteMe.rightThread.leftThread = deleteMe.leftThread;
				if(deleteMe.parent == null) {} //no parent, is root
				else{//has parent
					if(deleteMe.parent.left != null && deleteMe.parent.left.datum.compareTo(deleteMe.datum) == 0){//make this change to alllllllllllllll
						deleteMe.parent.left = deleteMe.right; //should be null
						if(deleteMe.left != null) deleteMe.left.parent = deleteMe.parent;
					}else{
						deleteMe.parent.right = deleteMe.right;
						if(deleteMe.right != null) deleteMe.right.parent = deleteMe.parent;
					}
				}
				
			}else{//BOTH CHILDREN
				System.out.println("hello");
				Node<T> replacer;
				replacer = deleteMe.leftThread;
				if(replacer.parent.left.datum.compareTo(replacer.datum) == 0){//replacer is left child
					System.out.println("something is wrong with my understanding. delete these lines of code.");
				}else{
					replacer.parent.right = replacer.left;
				}
				System.out.println("deleteMe: "+deleteMe.datum+"deleteMe.leftThread: "+deleteMe.leftThread.datum);
				if(deleteMe.leftThread != null) deleteMe.leftThread.rightThread = deleteMe.rightThread;
				if(deleteMe.rightThread != null) deleteMe.rightThread.leftThread = deleteMe.leftThread;
				
				if(replacer == null) System.out.println("uh oh?");
				if(replacer.left != null){//possible weak spot - replacer always have parent? update: i think by def it has to
					if(replacer.parent.left != null && replacer.parent.left.datum.compareTo(replacer.datum) == 0){//replacer is a left child
						replacer.parent.left = replacer.left;
						System.out.println("replacer: "+replacer.datum+" *replacer.left "+replacer.left.datum);
						replacer.left.parent = replacer.parent;
						System.out.println("replacer: "+replacer.datum+" **replacer.right "+replacer.right.datum);
					}else{//replacer is a right child
						System.out.println("replacer: "+replacer.datum+"***replacer.parent.right (replacer): "+replacer.parent.right.datum+" replacer.left (null?): "+replacer.left.datum);
						System.out.println("scanner");
						sc.nextInt();
						sc.close();
						replacer.parent.right = replacer.left;
						replacer.left.parent = replacer.parent;
					}
				}
				
				if(deleteMe.left != null) deleteMe.left.parent = replacer;
				if(deleteMe.right != null) deleteMe.right.parent = replacer;
				replacer.left = deleteMe.left;
				System.out.println("replacer: "+replacer.datum+" replacer.left: "+replacer.left.datum);
				if(replacer.left != null && deleteMe.left != null && replacer.datum.compareTo(deleteMe.left.datum) == 0) replacer.left = null;
				replacer.right = deleteMe.right;
				if(replacer.right != null && deleteMe.right != null && replacer.datum.compareTo(deleteMe.right.datum) == 0) replacer.right = null;
				
				
				if(deleteMe.parent == null) {//no parent => root
					System.out.println("good...good");
					root = replacer;
					replacer.parent = null;
					System.out.println("again, replacer"+replacer.datum+" again, replacer.left: "+replacer.left.datum);
				}else{//has parent
					if(deleteMe.parent.left != null && deleteMe.parent.left.datum.compareTo(deleteMe.datum) == 0){//make this change to alllllllllllllll
						deleteMe.parent.left = replacer; //the .right was experimental
						replacer.parent = deleteMe.parent; //experimental
					}else{
						System.out.println("3rd, replacer"+replacer.datum+" 3rd, replacer.right: "+replacer.right.datum);
						deleteMe.parent.right = replacer; //the .right was experimental
						replacer.parent = deleteMe.parent; //experimental
					}
				}
				System.out.println("end/replacer.left: "+replacer.left.datum);
				System.out.println("end/replacer.parent: "+replacer.parent.datum);
				System.out.println("end/replacer.right: "+replacer.right.datum);
			}
		}
		//44 17 78 32 50 88 48 62 84 92 80 82 -1
		System.out.println("ugh");
		System.out.println("root "+root.datum);
		//System.out.println("root.leftThread "+root.leftThread.datum);
		System.out.println("root.rightThread "+root.rightThread.datum);
		System.out.println("root.left "+root.left);
		System.out.println("root.right "+root.right.datum);
		System.out.println("root.parent "+root.parent);
		System.out.println("root.right.right "+root.right.right);
		System.out.println("root.right.left "+root.right.left);
		balFacDown(root);
		System.out.println("woohoo!");
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
			System.out.println("n: "+n.datum+" n.left: "+n.left.datum+" n.right: "+n.right.datum);
			return Math.max(getHeight(n.left) + 1,getHeight(n.right) + 1);
		}else if(n.right != null){//right
			System.out.println("n: "+n.datum+" n.right: "+n.right.datum);
			return getHeight(n.right) + 1;
		}else{//left
			System.out.println("n: "+n.datum+" n.left: "+n.left.datum);
			return getHeight(n.left) + 1;
		}
	}
	
	
	
	public int getBF(Node<T> n){
		int rightHeight = -1, leftHeight = -1;
		if(n.right == null && (Integer) n.datum == 16) System.out.println("16 right is null");
		if(n.right == null) rightHeight = 0;
		else rightHeight = getHeight(n.right) + 1;
		
		if(n.left == null && (Integer) n.datum == 16) System.out.println("16 left is null");
		if(n.left == null) leftHeight = 0;
		else leftHeight = getHeight(n.left) + 1;
		if((Integer)n.datum == 16){
			System.out.println("left height: "+leftHeight+" right height: "+rightHeight);
		}
		return rightHeight - leftHeight;
	}

	
	
	public boolean isEmpty(){//done
		if(root == null) return true;
		else return false;
	}
	
	
	public Iterator<T> inOrderTraversal(){
		ArrayList<T> elements = new ArrayList<T>();
		Node<T> temp = threadStart;
		while(temp!= null){
			System.out.println("checkThreads. in order: "+temp.datum);
			elements.add(temp.datum);
			temp = temp.rightThread;
		}
		return elements.iterator();
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
		int input;
		ArrayList<Integer> al = new ArrayList<Integer>();
		ThreadedAVLTree<Integer> t = new ThreadedAVLTree<Integer>();
		
		
		Scanner sc = new Scanner(System.in);
		while((input = sc.nextInt()) != -1){
			al.add(input);
		}
		
		sc.close();
		
		//al.add(1);
	/*	al.add(2);
		al.add(1);
		al.add(3);*/
		
	/*	al.add(5);
		al.add(2);
		al.add(7);
		al.add(1);
		al.add(4);
		al.add(6);
		al.add(8);
		al.add(3);*/
		
		t.checkThreads();
		
		for(int i = 0; i < al.size(); i++){
			t.insertBST(al.get(i));
		}
		System.out.println("a");
		t.terriblyInefficientThreader(t.root);
		System.out.println("b");
		
		t.checkThreads();
		t.delete(78);
		t.balFacDown(t.root);
		System.out.println("root: "+t.root.datum);
		System.out.println("root.right: "+t.root.right.datum);
		System.out.println("root.left: "+t.root.left.datum);
	
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


