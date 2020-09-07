package tp.p2.control;

import tp.p2.model.Game;

public class ExitCommand extends Command {
	
	private static final String NAME = "exit";
	private static final String SHORTCUT = "e";
	private static final String DETAILS_MSG = "[e]xit";
	private static final String HELP_MSG = "Terminates the program.";
	
	public ExitCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) {
		game.exit();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (commandWords.length == 1 && matchCommandName(commandWords[0])) ? this : null;
	}

}
