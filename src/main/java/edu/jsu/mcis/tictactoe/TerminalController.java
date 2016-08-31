package edu.jsu.mcis.tictactoe;


import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class TerminalController {
    private static Pattern _patternDigits = Pattern.compile(".*([0-9]+).*([0-9]+).*");

    private Model _model;
    private Scanner _scanner;
    private boolean _playing;

    private class Input {
        public int row;
        public int col;

        public Input(int r, int c) {
            row = r;
            col = c;
        }
    }

	public TerminalController(Model model) {
        _model = model;
        _playing = true;
        _scanner = null;
    }

    private Input _parseLocation(String line) {
        Matcher m = _patternDigits.matcher(line);

        if (m.matches()) {
            String row = m.group(1);
            String col = m.group(2);

            return new Input(new Integer(row), new Integer(col));
        } else {
            return null;
        }
    }

    public void start() {
        _scanner = new Scanner(System.in);

        while (_playing) {
            if (_scanner.hasNextLine()) {
                String line = _scanner.nextLine();

                if (_model.isGameOver()) {
                    if (line.equals("y")) {
                        System.out.println("Starting New Game");
                        _model.reset();
                    } else {
                        System.out.println("Goodbye");
                        _playing = false;
                    }
                } else if (line.equals("q") || line.equals("quit")) {
                    _playing = false;
                } else {
                    Input input = _parseLocation(line);

                    if (input == null) {
                        System.out.println("Invalid input: " + line);
                    } else {
                        if (!_model.markLocation(input.row, input.col)) {
                            System.out.println("Can not place there");
                        }
                    }
                }
            }
        }
    }
}
