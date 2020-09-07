package tp.p2.control;

import tp.p2.control.exceptions.CommandExecuteException;
import tp.p2.control.exceptions.ParametersException;
import tp.p2.model.Game;

public abstract class Command {
	
	protected final String name;
	protected final String shortcut;
	private final String details;
	private final String help;
	
	public Command(String name, String shortcut, String details, String help){
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	public abstract Command parse(String[] commandWords) throws ParametersException;
	
	protected boolean matchCommandName(String name){
		return this.shortcut.equalsIgnoreCase(name) ||
				this.name.equalsIgnoreCase(name);
	}
	
	public String helpText(){
		return details + " : " + help + "\n";
	}

}
