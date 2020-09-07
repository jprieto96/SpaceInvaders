package tp.p2.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import tp.p2.control.exceptions.CommandExecuteException;
import tp.p2.control.exceptions.FileContentsException;
import tp.p2.model.Game;

public class LoadCommand extends Command{
	
	private static final String NAME = "load";
	private static final String SHORTCUT = "a";
	private static final String DETAILS_MSG = "lo[a]d filename";
	private static final String HELP_MSG = "Load a saved game.";
	private static final String EXTENSION = ".dat";
	private static final String FAILED_ON_LOAD = "Failed to Load";
	
	private String file;
	
	public LoadCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try(BufferedReader br = new BufferedReader(new FileReader(file + EXTENSION))){
			br.readLine();
			br.readLine();
			game.load(br);
			System.out.println("Game successfully loaded from file " + file + EXTENSION);
		} catch (FileNotFoundException e) {
			throw new CommandExecuteException(FAILED_ON_LOAD, e);
		} catch (IOException e) {
			throw new CommandExecuteException(FAILED_ON_LOAD, e);
		} catch (FileContentsException e) {
			throw new CommandExecuteException(FAILED_ON_LOAD, e);
		} catch (CloneNotSupportedException e) {
			throw new CommandExecuteException(FAILED_ON_LOAD, e);
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords.length == 2 && matchCommandName(commandWords[0])) {
			file = commandWords[1];
			return this;
		}
		else return null;
	}

}
