package edu.jsu.mcis;


public class TicTacToe {
	private String[][] _board;
	private int _moves;
	private String _player;


	public TicTacToe() {
		_board = new String[][] {
			new String[] {" ", " ", " "},
			new String[] {" ", " ", " "},
			new String[] {" ", " ", " "},
		};
		_moves = 0;
		_player = "X";
	}

	private void _reset() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				_board[row][col] = " ";
			}
		}
		_moves = 0;
		_player = "X";
	}

	public boolean _validRowCol(int row, int col) {
		if (row < 0 || row > 3 || col < 0 || col > 3) {
			return false;
		} else {
			return true;
		}
	}

	public void _switchPlayer() {
		if (_player.equals("X")) {
			_player = "O";
		} else {
			_player = "X";
		}
	}

	public String _checkSection(int row0, int col0, int row1, int col1, int row2, int col2) {
		String winner = _board[row0][col0];

		if (!winner.equals(" ") && winner.equals(_board[row1][col1]) && winner.equals(_board[row2][col2])) {
			return winner;
		} else {
			return null;
		}
	}
	public String _checkVerticall() {
		String winner = null;

		for (int row = 0; row < 3; row++) {
			winner = _checkSection(row, 0, row, 1, row, 2);
			if (winner != null) {
				break;
			}
		}
		return winner;
	}
	public String _checkHorizontal() {
		String winner = null;

		for (int col = 0; col < 3; col++) {
			winner = _checkSection(0, col, 1, col, 2, col);
			if (winner != null) {
				break;
			}
		}
		return winner;
	}
	public String _checkDiagonals() {
		String winner = _checkSection(0, 0, 1, 1, 2, 2);

		if (winner != null) {
			return winner;
		} else {
			return _checkSection(0, 2, 1, 1, 2, 0);
		}
	}
	public String _check() {
		String winner = _checkHorizontal();

		if (winner == null) {
			winner = _checkVerticall();

			if (winner == null) {
				winner = _checkDiagonals();
			}
		}

		return winner;
	}

	public void startNewGame() {
		_reset();
	}

	public void markLocation(int row, int col) {
		if (_moves < 9 && _board[row][col].equals(" ") && _validRowCol(row, col)) {
			_moves++;
			_board[row][col] = _player;
			_check();
			_switchPlayer();
		}
	}

	public void checkLocation(int row, int col, String value) {
		System.out.println(getMark(row, col).equals(value));
	}

	public void printBoard() {
		for (int row = 0; row < 3; row++) {
			System.out.print(" " + _board[row][0]);
			System.out.print(" " + _board[row][1]);
			System.out.println(" " + _board[row][2]);
		}
	}

	public String getMark(int row, int col) {
		if (_validRowCol(row, col)) {
			return _board[row][col];
		} else {
			return " ";
		}
	}

	public String getWinner() {
		String winner = _check();

		if (winner == null && _moves == 9) {
			return "TIE";
		} else {
			return winner;
		}
	}

	public static void main(String[] args) {
		
	}
}
