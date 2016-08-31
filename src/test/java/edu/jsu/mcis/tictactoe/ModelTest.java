package edu.jsu.mcis.tictactoe;


import org.junit.*;
import static org.junit.Assert.*;


public class ModelTest {

	@Test
	public void testInitialBoardIsEmpty() {
		Model m = new Model();

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				assertEquals(" ", m.getMark(row, col));
			}
		}
	}

	@Test
	public void testMarkXInUpperRightCorner() {
		Model m = new Model();
		m.markLocation(0, 2);
		assertEquals("X", m.getMark(0, 2));
	}

	@Test
	public void testMarkOInBottomLeftCorner() {
		Model m = new Model();
		m.markLocation(2, 0);
		assertEquals("X", m.getMark(2, 0));
	}

	@Test
	public void testUnableToMarkOverExistingMark() {
		Model m = new Model();
		m.markLocation(0, 0);
		m.markLocation(0, 0);
		assertEquals("X", m.getMark(0, 0));
	}


	@Test
	public void testGameIsNotOverAfterTheFirstMark() {
		Model m = new Model();
		m.markLocation(0, 0);
		assertEquals("NONE", m.getWinner());
	}

	@Test
	public void testGameIsWonByXHorizontallyAcrossTopRow() {
		Model m = new Model();

		m.markLocation(0, 0); // X top left
		m.markLocation(1, 0); // O left middle

		m.markLocation(0, 1); // X top middle
		m.markLocation(1, 1); // O top right

		m.markLocation(0, 2); // X top right

		assertEquals("X", m.getWinner());
	}

	@Test
	public void testGameIsOverByTieIfAllLocationsAreFilled() {
		Model m = new Model();

		m.markLocation(0, 0); // X
		m.markLocation(1, 0); // O
		m.markLocation(2, 0); // X

		m.markLocation(1, 1); // X
		m.markLocation(0, 1); // O
		m.markLocation(2, 1); // X

		m.markLocation(1, 2); // X
		m.markLocation(0, 2); // O
		m.markLocation(2, 2); // X

		assertEquals("TIE", m.getWinner());
	}
}
