package edu.jsu.mcis.tictactoe;


import java.util.ArrayList;


public class Model {
	private String[][] _board;
	private int _moves;
	private String _player;
	private ArrayList<Listener> _listeners;


	public Model() {
		_board = new String[][] {
			new String[] {" ", " ", " "},
			new String[] {" ", " ", " "},
			new String[] {" ", " ", " "},
		};
		_moves = 0;
		_player = "X";
		_listeners = new ArrayList<Listener>();
	}

	private boolean _validRowCol(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		} else {
			return true;
		}
	}

	private void _switchPlayer() {
		if (_player.equals("X")) {
			_player = "O";
		} else {
			_player = "X";
		}
	}

	private String _checkSection(int row0, int col0, int row1, int col1, int row2, int col2) {
		String winner = _board[row0][col0];

		if (!winner.equals(" ") && winner.equals(_board[row1][col1]) && winner.equals(_board[row2][col2])) {
			return winner;
		} else {
			return null;
		}
	}
	private String _checkVerticall() {
		String winner = null;

		for (int row = 0; row < 3; row++) {
			winner = _checkSection(row, 0, row, 1, row, 2);
			if (winner != null) {
				break;
			}
		}
		return winner;
	}
	private String _checkHorizontal() {
		String winner = null;

		for (int col = 0; col < 3; col++) {
			winner = _checkSection(0, col, 1, col, 2, col);
			if (winner != null) {
				break;
			}
		}
		return winner;
	}
	private String _checkDiagonals() {
		String winner = _checkSection(0, 0, 1, 1, 2, 2);

		if (winner != null) {
			return winner;
		} else {
			return _checkSection(0, 2, 1, 1, 2, 0);
		}
	}
	private String _check() {
		String winner = _checkHorizontal();

		if (winner == null) {
			winner = _checkVerticall();

			if (winner == null) {
				winner = _checkDiagonals();
			}
		}

		return winner;
	}

	public void reset() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				_board[row][col] = " ";
			}
		}
		_moves = 0;
		_player = "X";
		_emitChange();
	}

	public String getPlayer() {
		return _player;
	}

	public void addListener(Listener listener) {
		_listeners.add(listener);
	}
	private void _emitChange() {
		for (int i = 0, il = _listeners.size(); i < il; i++) {
			Listener listener = _listeners.get(i);
			listener.call();
		}
	}

	public boolean markLocation(int row, int col) {
		if (_validRowCol(row, col)) {
			if (_moves < 9 && _board[row][col].equals(" ")) {
				_moves++;
				_board[row][col] = _player;
				_check();
				_switchPlayer();
				_emitChange();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
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

		if (winner == null) {
			if (_moves == 9) {
				return "TIE";
			} else {
				return "NONE";
			}
		} else {
			return winner;
		}
	}

	public boolean isGameOver() {
		return !getWinner().equals("NONE");
	}
}
