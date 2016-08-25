package edu.jsu.mcis;


public class TicTacToe {
	private static String[][] _board = new String[][] {
		new String[] {" ", " ", " "},
		new String[] {" ", " ", " "},
		new String[] {" ", " ", " "},
	};
	private static String _player = "X";
	private static String _winner = null;



	private static void _reset() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				_board[row][col] = " ";
			}
		}
		_player = "X";
	}
	public static boolean _validRowCol(int row, int col) {
		if (row < 0 || row > 3 || col < 0 || col > 3) {
			return false;
		} else {
			return true;
		}
	}
	public static void _switchPlayer() {
		_player = _player == "X" ? "O" : "X";
	}

	public static String _checkSection(int row0, int col0, int row1, int col1, int row2, int col2) {
		String winner = _board[row0][col0];

		if (winner == _board[row1][col1] && winner == _board[row2][col2]) {
			return winner;
		} else {
			return null;
		}
	}
	public static String _checkHorizontal() {
		String winner = null;

		for (int row = 0; row < 3; row++) {
			winner = _checkSection(row, 0, row, 1, row, 2);
			if (winner != null) {
				break;
			}
		}
		return winner;
	}
	public static String _checkVerticall() {
		String winner = null;

		for (int col = 0; col < 3; col++) {
			winner = _checkSection(0, col, 1, col, 2, col);
			if (winner != null) {
				break;
			}
		}
		return winner;
	}
	public static String _checkDiagonals() {
		String winner = _checkSection(0, 0, 1, 1, 2, 2);

		if (winner != null) {
			return winner;
		} else {
			return _checkSection(0, 2, 1, 1, 2, 0);
		}
	}
	public static boolean _check() {
		String winner = _checkHorizontal();

		if (winner == null) {
			winner = _checkVerticall();

			if (winner == null) {
				winner = _checkDiagonals();
			}
		}

		_winner = winner;

		return _winner != null;
	}

	public static void startNewGame() {
		_reset();
	}

	public static void markLocation(int row, int col) {
		if (_validRowCol(row, col)) {
			_board[row][col] = _player;
			_check();
			_switchPlayer();
		}
	}

	public static String getMark(int row, int col) {
		if (_validRowCol(row, col)) {
			return _board[row][col];
		} else {
			return " ";
		}
	}

	public static String getWinner() {
		return _winner;
	}


	public static void main(String[] args) {
		System.out.println("TicTacToe");
		startNewGame();
	}
}
