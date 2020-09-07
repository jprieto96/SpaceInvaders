package tp.p2.control;

import tp.p2.model.Game;
import tp.p2.view.GamePrinter;
import tp.p2.view.PrinterTypes;

public class SerializerCommand extends Command{
	
	GamePrinter printer;
	
	private static final String NAME = "serializer";
	private static final String SHORTCUT = "ser";
	private static final String DETAILS_MSG = "[ser]ializer";
	private static final String HELP_MSG = "Prints the serialized game.";
	
	public SerializerCommand() {
		super(NAME, SHORTCUT, DETAILS_MSG, HELP_MSG);
	}

	@Override
	public boolean execute(Game game) {
		printer = PrinterTypes.SERIALIZER.getObject();
		System.out.println(printer.toString(game));
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (commandWords.length == 1 && matchCommandName(commandWords[0])) ? this : null;
	}

}
