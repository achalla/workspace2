package sudokusolver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SudokuBoardTest {
	SudokuBoard sb;

	@Before
	public void initialize(){
		sb = SudokuBoard.getNewBoard();
		sb.loadBoard1();
	}
	
	@Test
	public void containsInRowTest() {
		assertTrue(sb.containsInRow(0,5));
		assertFalse(sb.containsInRow(0,2));
		assertTrue(sb.containsInRow(5,8));
		assertFalse(sb.containsInRow(5,1));
	}
	
	@Test
	public void containsInColTest() {
		assertTrue(sb.containsInCol(0,5));
		assertFalse(sb.containsInCol(0,3));
		assertTrue(sb.containsInCol(3,1));
		assertFalse(sb.containsInCol(3,3));
	}
}
