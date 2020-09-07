package tp.p2.control;

import tp.p2.control.exceptions.CommandExecuteException;
import tp.p2.control.exceptions.ShockWaveException;
import tp.p2.model.Game;

public class ShockwaveCommand extends Command{
	
	private static final String NAME = "shockwave";
	private static final String SHORTCUT = "w";
	private static final String DETAILS_MSG = "shock[w]ave";
	private static final String HELP_MSG = "UCM-Ship releases a shock wave.";
	private static final String FAILED_SHOCKWAVE = "Failed to apply ShockWave";
	
	public ShockwaveCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.shockWave();
			game.update();
			return true;	
		}
		catch(ShockWaveException ex) {
			throw new CommandExecuteException(FAILED_SHOCKWAVE, ex);
		}
	}

	@Override
	public Command parse(String[] commandWords) {
		return (commandWords.length == 1 && matchCommandName(commandWords[0])) ? this : null;
	}

}
