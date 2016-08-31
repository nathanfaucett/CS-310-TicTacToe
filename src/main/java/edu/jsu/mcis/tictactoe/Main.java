package edu.jsu.mcis.tictactoe;


public class Main {

	public static void main(String[] args) {
        final Model model = new Model();
        final TerminalView view = new TerminalView();
        final TerminalController controller = new TerminalController(model);

        model.addListener(new Listener() {
            public void call() {
                view.render(model);
            }
        });

        view.render(model);

		controller.start();
	}
}
