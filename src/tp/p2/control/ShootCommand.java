package tp.p2.control;

import tp.p2.control.exceptions.CommandExecuteException;
import tp.p2.control.exceptions.NoSuperlasersException;
import tp.p2.control.exceptions.ParametersException;
import tp.p2.control.exceptions.ShootException;
import tp.p2.model.Game;

public class ShootCommand extends Command {
	
	private boolean superlaser;
	
	private static final String NAME = "shoot";
	private static final String SHORTCUT = "s";
	private static final String SUPERLASER = "superlaser";
	private static final String DETAILS_MSG = "[s]hoot <[s]uperlaser>";
	private static final String HELP_MSG = "UCM-Ship launches a laser or a superlaser.";
	private static final String SHOOT_SUPER_LASER_ERROR_COMMAND_MSG = "You don´t have any superlaser.\n";
	private static final String PARAMETERS_EXCEPTION_MSG = "Incorrect Parameters of Shoot entered. ";
	private static final String FAILED_TO_SHOOT = "Failed to Shoot";
	
	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			if(!superlaser) {
				game.shootLaser();
				game.update();
				return true;
			}
			else {
				if(game.getPlayerSuperLasers() > 0) {
					game.shootSuperLaser();
					game.removeSuperLaser();
					game.update();
					return true;
				}
				else throw new NoSuperlasersException(SHOOT_SUPER_LASER_ERROR_COMMAND_MSG);
			}
		}
		catch(ShootException ex) {
			throw new CommandExecuteException(FAILED_TO_SHOOT, ex);
		}
		catch(NoSuperlasersException ex) {
			throw new CommandExecuteException(FAILED_TO_SHOOT, ex);
		}
		
	}

	@Override
	public Command parse(String[] commandWords) throws ParametersException {
		if (commandWords.length == 1 && matchCommandName(commandWords[0])) {
			superlaser = false;
			return this;
		}
		if (commandWords.length == 2 && matchCommandName(commandWords[0])) {
			if(matchCommandName2(commandWords[1])) {
				superlaser = true;	
				return this;
			}
			else throw new ParametersException(PARAMETERS_EXCEPTION_MSG + DETAILS_MSG);
		}
		return null;
	}

	private boolean matchCommandName2(String string) {
		return string.equalsIgnoreCase(SUPERLASER) || string.equalsIgnoreCase(SHORTCUT);
	}

}
