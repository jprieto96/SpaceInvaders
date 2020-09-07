package tp.p2.control;

import tp.p2.control.exceptions.CommandExecuteException;
import tp.p2.control.exceptions.MoveException;
import tp.p2.control.exceptions.ParametersException;
import tp.p2.model.Game;

public class MoveCommand extends Command {
	
	private static final String NAME = "move";
	private static final String SHORTCUT = "m";
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	private static final String SHORTCUT_LEFT = "l";
	private static final String SHORTCUT_RIGHT = "r";
	private static final String DETAILS_MSG = "[m]ove <[l]eft|[r]ight> <[1]|[2]>";
	private static final String HELP_MSG = "Moves UCM-Ship to the indicated direction.";
	private static final String PARAMETERS_EXCEPTION_MSG = "Incorrect Parameters of Move entered. ";
	private static final String ERROR_MOVE = "Failed to move";
	
	private String dir;
	private int numCols;
	
	public MoveCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	public MoveCommand(String dir, int numCols) {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
		this.dir = dir;
		this.numCols = numCols;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.move(dir, numCols);
			game.update();
			return true;
		}
		catch(MoveException ex) {
			throw new CommandExecuteException(ERROR_MOVE, ex);
		}
	}

	@Override
	public Command parse(String[] commandWords) throws ParametersException{
		Command command = null;
		if (commandWords.length == 3 && matchCommandName(commandWords[0])){
			if(matchCommandDirection(commandWords[1]) && matchCommandPosition(commandWords[2])) {
				if(commandWords[1].equalsIgnoreCase(LEFT) || commandWords[1].equalsIgnoreCase(SHORTCUT_LEFT))
					dir = LEFT;
				else 
					dir = RIGHT;
				if(commandWords[2].equalsIgnoreCase("1"))
					numCols = 1;
				else 
					numCols = 2;
				command = this;
			}
			else throw new ParametersException(PARAMETERS_EXCEPTION_MSG + DETAILS_MSG);
		}
		return command;
	}
	

	private boolean matchCommandDirection(String direction) {
		return direction.equalsIgnoreCase(LEFT) || direction.equalsIgnoreCase(SHORTCUT_LEFT) ||
				direction.equalsIgnoreCase(RIGHT) || direction.equalsIgnoreCase(SHORTCUT_RIGHT);
	}
	
	private boolean matchCommandPosition(String position) {
		return position.equalsIgnoreCase("1") || position.equalsIgnoreCase("2");
	}


}
