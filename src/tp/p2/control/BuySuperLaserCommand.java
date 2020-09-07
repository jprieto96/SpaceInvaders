package tp.p2.control;

import tp.p2.control.exceptions.CommandExecuteException;
import tp.p2.control.exceptions.NoPointsToBuySuperLaserException;
import tp.p2.model.Game;

public class BuySuperLaserCommand extends Command {
	
	private static final String NAME = "buySuperlaser";
	private static final String SHORTCUT = "buy";
	private static final String DETAILS_MSG = "[buy]Superlaser";
	private static final String HELP_MSG = "Buy a superlaser.";
	
	private static final int POINTS_TO_BUY_SUPERLASER = 20;
	private static final String BUY_SUPERLASER_ERROR_COMMAND_MSG = "You need to have at least 20 points to buy a superlaser.\n";
	private static final String FAILED_TO_BUY_SUPERLASER = "Failed to buy SuperLaser";
	
	public BuySuperLaserCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			if(game.getPlayerScore() >= POINTS_TO_BUY_SUPERLASER) {
				game.addSuperLaser();
				game.receivePoints(-POINTS_TO_BUY_SUPERLASER);
				game.update();
				return true;
			}
			else throw new NoPointsToBuySuperLaserException(BUY_SUPERLASER_ERROR_COMMAND_MSG);
		}
		catch(NoPointsToBuySuperLaserException ex) {
			throw new CommandExecuteException(FAILED_TO_BUY_SUPERLASER, ex);
		}
	}

	@Override
	public Command parse(String[] commandWords){
		return (commandWords.length == 1 && matchCommandName(commandWords[0])) ? this : null;
	}

}
