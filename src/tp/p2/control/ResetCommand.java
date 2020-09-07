package tp.p2.control;

import tp.p2.model.Game;

public class ResetCommand extends Command {
	
	private static final String NAME = "reset";
	private static final String SHORTCUT = "r";
	private static final String DETAILS_MSG = "[r]eset";
	private static final String HELP_MSG = "Starts a new game.";

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (commandWords.length == 1 && matchCommandName(commandWords[0])) ? this : null;
	}

}
