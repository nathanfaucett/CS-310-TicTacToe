package edu.jsu.mcis.tictactoe;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Game {
    private Model _model;
    private JFrame _window;
    private JPanel _grid;
    private JLabel[][] _buttons;

    private JDialog _gameOverWindow;
    private JLabel _gameOverLabel;


    public Game() {
        final Game _this = this;

        _model = new Model();

        _window = new JFrame("Tic Tac Toe");
        _window.setSize(512, 512);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _buttons = new JLabel[][] {
            new JLabel[] {_createButton(0, 0), _createButton(0, 1), _createButton(0, 2)},
            new JLabel[] {_createButton(1, 0), _createButton(1, 1), _createButton(1, 2)},
            new JLabel[] {_createButton(2, 0), _createButton(2, 1), _createButton(2, 2)},
        };
        _grid = new JPanel(new GridLayout(3, 3));

        _gameOverWindow = new JDialog(_window, "Game Over");
        _gameOverWindow.setSize(512, 32);

        _gameOverLabel = new JLabel("", SwingConstants.CENTER);
        _gameOverLabel.setName("OptionPane.label");

        _init();

        _model.addListener(new Listener() {
            public void call() {
                _this._render();
            }
        });
    }

    private void _init() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                _grid.add(_buttons[row][col]);
            }
        }

        _gameOverWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent el) {
                _model.reset();
            }
        });
        _gameOverWindow.add(_gameOverLabel);

        _window.add(_grid);
    }
    private JLabel _createButton(final int row, final int col) {
        JLabel button = new JLabel(" ", SwingConstants.CENTER);

        button.setName("Location" + row + col);
        button.setBorder(BorderFactory.createLineBorder(Color.black));

        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                _model.markLocation(row, col);
            }
        });

        return button;
    }

    private void _render() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                _buttons[row][col].setText(_model.getMark(row, col));
            }
        }

        if (_model.isGameOver()) {
            String winner = _model.getWinner();
            String message;

            _gameOverLabel.setText("The winner is " + winner);
            _gameOverWindow.setVisible(true);
        }
    }

    public void init() {
        _render();
        _window.setVisible(true);
    }

    public static void main(String[] args) {
        final Game game = new Game();

        SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                game.init();
             }
        });
    }
}
