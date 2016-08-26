import edu.jsu.mcis.*;


public class TicTacToeKeywords {
	private static TicTacToe _ttt = new TicTacToe();


	public void startNewGame() {
		_ttt.startNewGame();
	}

	public void markLocation(int row, int col) {
		_ttt.markLocation(row, col);
	}

	public String getMark(int row, int col) {
		return _ttt.getMark(row, col);
	}

	public String getWinner() {
		return _ttt.getWinner();
	}
}
