package edu.jsu.mcis.tictactoe;


public class TerminalView {

	public TerminalView() {}

    private String renderBoard(Model m) {
        return (
            "     |     |    \n\r" +
            "  " + m.getMark(0, 0) + "  |  " + m.getMark(0, 1) + "  |  " + m.getMark(0, 2) + "\n\r" +
            " ___ | ___ | ___\n\r" +
            "     |     |    \n\r" +
            "  " + m.getMark(1, 0) + "  |  " + m.getMark(1, 1) + "  |  " + m.getMark(1, 2) + "\n\r" +
            " ___ | ___ | ___\n\r" +
            "     |     |    \n\r" +
            "  " + m.getMark(2, 0) + "  |  " + m.getMark(2, 1) + "  |  " + m.getMark(2, 2) + "\n\r" +
            "     |     |    \n\r"
        );
    }

    public void render(Model m) {
		String winner = m.getWinner();

        System.out.println(renderBoard(m));

		if (winner.equals("TIE")) {
			System.out.println("It is a TIE");
			System.out.println("Play again? y/n");
		} else if (!winner.equals("NONE")) {
			System.out.println("Winner is " + winner);
			System.out.println("Play again? y/n");
		} else {
			System.out.println("Player " + m.getPlayer() + "'s turn, Enter row, col");
		}
    }
}
