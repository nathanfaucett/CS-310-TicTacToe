package edu.jsu.mcis;


import org.junit.*;
import static org.junit.Assert.*;


public class TicTacToeTest {

	@Test
	public void testInitialBoardIsEmpty() {
		TicTacToe ttt = new TicTacToe();

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				assertTrue(ttt.getMark(row, col).equals(" "));
			}
		}
	}

	@Test
	public void testMarkXInUpperRightCorner() {
		TicTacToe ttt = new TicTacToe();
		ttt.markLocation(0, 2);
		assertTrue(ttt.getMark(0, 2).equals("X"));
	}

	@Test
	public void testMarkOInBottomLeftCorner() {
		TicTacToe ttt = new TicTacToe();
		ttt.markLocation(2, 0);
		assertTrue(ttt.getMark(2, 0).equals("X"));
	}

	@Test
	public void testUnableToMarkOverExistingMark() {
		TicTacToe ttt = new TicTacToe();
		ttt.markLocation(0, 0);
		ttt.markLocation(0, 0);
		assertTrue(ttt.getMark(0, 0).equals("X"));
	}


	@Test
	public void testGameIsNotOverAfterTheFirstMark() {
		TicTacToe ttt = new TicTacToe();
		ttt.markLocation(0, 0);
		assertTrue(ttt.getWinner() == null);
	}

	@Test
	public void testGameIsWonByXHorizontallyAcrossTopRow() {
		TicTacToe ttt = new TicTacToe();

		ttt.markLocation(0, 0); // X top left
		ttt.markLocation(1, 0); // O left middle

		ttt.markLocation(0, 1); // X top middle
		ttt.markLocation(1, 1); // O top right

		ttt.markLocation(0, 2); // X top right

		assertTrue(ttt.getWinner().equals("X"));
	}

	@Test
	public void testGameIsOverByTieIfAllLocationsAreFilled() {
		TicTacToe ttt = new TicTacToe();

		ttt.markLocation(0, 0); // X
		ttt.markLocation(1, 0); // O
		ttt.markLocation(2, 0); // X

		ttt.markLocation(1, 1); // X
		ttt.markLocation(0, 1); // O
		ttt.markLocation(2, 1); // X

		ttt.markLocation(1, 2); // X
		ttt.markLocation(0, 2); // O
		ttt.markLocation(2, 2); // X

		assertTrue(ttt.getWinner().equals("TIE"));
	}
}
