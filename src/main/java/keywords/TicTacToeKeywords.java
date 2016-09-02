package keywords;


import edu.jsu.mcis.tictactoe.*;
import java.awt.Component;


public class TicTacToeKeywords {
	private static Model _m = new Model();


	public void startNewGame() {
		_m.reset();
	}

	public void markLocation(int row, int col) {
		_m.markLocation(row, col);
	}

	public String getMark(int row, int col) {
		return _m.getMark(row, col);
	}

	public String getWinner() {
		return _m.getWinner();
	}
}
