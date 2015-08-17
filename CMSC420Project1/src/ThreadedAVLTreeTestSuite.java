import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import solutions.ThreadedAVLTree;


public class ThreadedAVLTreeTestSuite {

	@Test
	public void testInsert() {
		ThreadedAVLTree<Integer> t = new ThreadedAVLTree<Integer>();
		t.insert(5);
		t.insert(7);
		t.insert(3);
		System.out.println(t.root.datum);
		System.out.println(t.root.right.datum);
		System.out.println(t.root.left.datum);
	}
	
	
	@Test
	public void testHeight() {
		int input;
		ArrayList<Integer> al = new ArrayList<Integer>();
		ThreadedAVLTree<Integer> t = new ThreadedAVLTree<Integer>();
		Scanner sc = new Scanner(System.in);
		while((input = sc.nextInt()) != -1){
			al.add(input);
		}
		sc.close();
		for(int i = 0; i < al.size(); i++){
			System.out.println(al.get(i));
			t.insert(al.get(i));
		}
		System.out.println("Height is "+t.height());
		fail("Not yet implemented");
	}
	
	@Test
	public void testGenerics() {
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testThreads() {
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testEmptyTree() {
		
		fail("Not yet implemented");
	}

}
