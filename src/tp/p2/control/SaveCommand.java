package tp.p2.control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tp.p2.control.exceptions.CommandExecuteException;
import tp.p2.model.Game;
import tp.p2.view.GamePrinter;
import tp.p2.view.PrinterTypes;

public class SaveCommand extends Command{
	
	private static final String NAME = "save";
	private static final String SHORTCUT = "v";
	private static final String DETAILS_MSG = "sa[v]e fileName";
	private static final String HELP_MSG = "Save a game in a file.";
	private static final String EXTENSION = ".dat";
	private static final String FAILED_TO_SAVE = "Failed to Save";
	
	private String file;
	
	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		GamePrinter printer = PrinterTypes.SERIALIZER.getObject();
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file + EXTENSION))){
			bw.write(printer.toString(game));
			bw.close();
			System.out.println("Game successfully saved in file " + file + EXTENSION + " -> Use the load command to reload it.");
		} catch (IOException e) {
			throw new CommandExecuteException(FAILED_TO_SAVE, e);
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 2 && matchCommandName(commandWords[0])){
			file = commandWords[1];
			return this;
		}
		else return null;
	}

}
