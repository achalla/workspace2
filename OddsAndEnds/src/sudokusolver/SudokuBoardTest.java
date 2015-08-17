package sudokusolver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SudokuBoardTest {

	@Before
	public void initialize(){
		SudokuBoard sb = SudokuBoard.getNewBoard();
		sb.loadBoard2();
	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
