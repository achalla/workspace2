package sudokusolver;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SudokuSolverTopDownTest {
	SudokuSolverTopDown sb;

	@Before
	public void initialize(){
		sb = SudokuSolverTopDown.getNewBoard();
		sb.loadBoard1();
	}
	
	@Test
	public void fillHintsTest(){
		int[] temp = {9,1,1,1,1,1,1,1,1,1};
		assert(Arrays.equals(sb.hints[0][0],temp));
		assert(Arrays.equals(sb.hints[3][6],temp));
		assert(Arrays.equals(sb.hints[8][8],temp));
	}
	
	@Test
	public void populateHintsTest(){
		int[] temp = {-1,1,1,1,1,1,1,1,1,1};
		assert(Arrays.equals(sb.hints[0][0],temp));
		int[] temp2 = {-1,1,1,1,1,1,1,1,1,1};
		assert(Arrays.equals(sb.hints[3][6],temp));
		int[] temp3 = {2,0,0,0,1,0,0,1,0,0};
		assert(Arrays.equals(sb.hints[8][8],temp3));
	}
	
	
	
	/*@Test
	public void containsInRowTest() {
		assertEquals(sb.containsInRow(0,5),6);
		assertEquals(sb.containsInRow(0,2),-1);
		assertEquals(sb.containsInRow(5,8),5);
		assertEquals(sb.containsInRow(5,1),-1);
	}
	
	@Test
	public void containsInColTest() {
		assertEquals(sb.containsInCol(0,5),8);
		assertEquals(sb.containsInCol(0,3),-1);
		assertEquals(sb.containsInCol(3,1),6);
		assertEquals(sb.containsInCol(3,3),-1);
	}
	
	@Test
	public void getGridTest(){
		//lon 0
		assertEquals(sb.getGrid(0,0),0);
		assertEquals(sb.getGrid(3,1),3);
		assertEquals(sb.getGrid(8,2),6);
		//lon 1
		assertEquals(sb.getGrid(0,3),1);
		assertEquals(sb.getGrid(3,4),4);
		assertEquals(sb.getGrid(8,5),7);
		//lon 2
		assertEquals(sb.getGrid(0,6),2);
		assertEquals(sb.getGrid(3,7),5);
		assertEquals(sb.getGrid(8,8),8);
	}
	
	@Test
	public void basicCheckLatTest() {
		sb.checkLat(0,9);
		assertEquals(sb.get(2,4),9);
	}*/
}
