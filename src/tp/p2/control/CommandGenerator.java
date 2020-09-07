package tp.p2.control;

import tp.p2.control.exceptions.CommandParseException;
import tp.p2.control.exceptions.ParametersException;

public class CommandGenerator {
	
	private static final String ERROR_PARSE = "Failed to parse command";
	
	private static Command[] availableCommands = {
			new MoveCommand(),
			new BuySuperLaserCommand(),
			new ShootCommand(),
			new ShockwaveCommand(),
			new ListCommand(),
			new ResetCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new NoneCommand(),
			new ListPrintersCommand(),
			new SerializerCommand(), 
			new SaveCommand(), 
			new LoadCommand()
	};

	public static Command parse(String[] commandDiv) throws CommandParseException{
		Command command = null;
		for(Command c : availableCommands) {
			try {
				command = c.parse(commandDiv);
				if(command != null) break;
			}
			catch(ParametersException ex) {
				throw new CommandParseException(ERROR_PARSE, ex);
			}	
		}
		return command;
	}
	
	public static String commandHelp() {
		String help = "";
		for(Command c : availableCommands) help += c.helpText();
		return help; 
	}

}
