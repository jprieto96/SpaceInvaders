package tp.p2.control;

import tp.p2.model.Game;
import tp.p2.view.PrinterTypes;

public class ListPrintersCommand extends Command{
	
	private static final String NAME = "listPrinters";
	private static final String SHORTCUT = "p";
	private static final String DETAILS_MSG = "list[P]rinters";
	private static final String HELP_MSG = "Prints types of printers";
	
	public ListPrintersCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(PrinterTypes.printerHelp());
		return false;
	}

	@Override
	public Command parse(String[] commandWords){
		return (commandWords.length == 1 && matchCommandName(commandWords[0])) ? this : null;
	}

}
