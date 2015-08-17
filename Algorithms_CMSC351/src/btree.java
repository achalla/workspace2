import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class btree{

	public static Node root;
	
	
	/* given an array with the pre-order traversal of a binary search tree (BST),
	   this method converts the array to a BST of Nodes, returning the root. */
	static Node preToBST(ArrayList<Long> arr){
		Stack<Node> s = new Stack<Node>();
		Node temp;
		long length = arr.size();
		
		s.push(root);
		
		for(int i = 1; i < length; i++){
			temp = null;
			while(!s.isEmpty() && arr.get(i) > s.peek().datum){
				temp = s.pop();
				if(temp.datum == root.datum) temp = root;
				//System.out.prlongln("Popping: "+temp.datum);
			}
			if(temp != null){
				temp.right = new Node(arr.get(i));
				s.push(temp.right);
			}else{
				s.peek().left = new Node(arr.get(i));
				s.push(s.peek().left);
			}
		}
		return root;
	}
	
	
	/*prlongs the BST by level (top to bottom, left to right)
	  NOTE: this is rudimentary so it doesn't express absent nodes
	  thus the levels can get skewed*/
	static void prlongTreeByLevel(){
		Queue<Node> q = new PriorityQueue<Node>();
		Node temp;
		
		q.add(root);
		System.out.println(root.datum);
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
	
	static long evenLevelAdder(){
		Queue<Node> evens = new PriorityQueue<Node>(), odds = new PriorityQueue<Node>();
		Node temp = null, temp2 = null;
		long sum = 0;
		
		evens.add(root);
		
		while(!evens.isEmpty() || !odds.isEmpty()){
			while(!evens.isEmpty()) {
				temp = evens.remove();
				sum += temp.datum;
				//System.out.prlongln("sum: "+sum);
				if(temp.left != null){
					odds.add(temp.left);
				}
				if(temp.right != null){
					odds.add(temp.right);
				}
			}
			
			while(!odds.isEmpty()){
				temp2 = odds.remove();
				if(temp2.left != null){
					evens.add(temp2.left);
				}
				if(temp2.right != null){
					evens.add(temp2.right);
				}
			}
		}
		
		return sum;
	}
	
	
	static class Node implements Comparable<Node>{

		public Node left = null, right = null, parent = null;
		public long datum;
		
		public Node(long datum){
			this.datum = datum;
		}
		
		/*public Node(Node left, Node right, long datum){
			
			this.datum = datum;
		}*/
		
		public int compareTo(Node n){
			return (int)(this.datum - n.datum);
		}

	}

	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Long> al = new ArrayList<Long>();
		long numElem;
		
		/*data input*/
		numElem = sc.nextInt();
		//System.out.prlongln("numELem: "+numElem);
		for(int i = 0; i < numElem; i++){
			long a = sc.nextInt();
			al.add(a);
		}
		sc.close();
		
		/*constructing the BST from the input*/
		root = new Node(al.get(0));
		preToBST(al);
		
		/*printing the BST by level (top to bottom, left to right)*/
		//prlongTreeByLevel();		
		
		/*calculating sum of even levels*/
		//System.out.prlongln("The sum of the data on the even levels of this Binary Search Tree is: "+evenLevelAdder());
		System.out.println(evenLevelAdder());
	}

}
