package tp.p2.control;

import tp.p2.model.Game;

public class NoneCommand extends Command {
	
	private static final String NAME = "none";
	private static final String SHORTCUT = "n";
	private static final String DETAILS_MSG = "[n]one";
	private static final String HELP_MSG = "Skips one cycle.";
	
	public NoneCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (commandWords.length == 1 && (matchCommandName(commandWords[0]) || commandWords[0].equalsIgnoreCase(""))) ? this : null;
	}

}
