package tp.p2.control;

import tp.p2.model.Game;

public class ListCommand extends Command {
	
	private static final String NAME = "list";
	private static final String SHORTCUT = "l";
	private static final String DETAILS_MSG = "[l]ist";
	private static final String HELP_MSG = "Prints the list of available ships.";
	
	public ListCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(game.listInfoGame());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (commandWords.length == 1 && matchCommandName(commandWords[0])) ? this : null;
	}

}
