package tp.p2.control;

import java.util.Scanner;

import tp.p2.control.exceptions.CommandExecuteException;
import tp.p2.control.exceptions.CommandParseException;
import tp.p2.model.Game;
import tp.p2.view.GamePrinter;
import tp.p2.view.PrinterTypes;

public class Controller {
	
	private static String PROMPT = "Command >";
	private static String ERROR_COMMAND_MSG = "Command Error. Introduce a valid command.\n";

	private Game game;
	private GamePrinter printer;
	Scanner in;

	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
		printer = PrinterTypes.BOARDPRINTER.getObject();
	}

	public void run() {
		
		System.out.println(printer.toString(game));
		
		while(!game.isFinished()) {
			System.out.println(PROMPT);
			String[] commandDiv = in.nextLine().split(" ");
			try {
				Command command = CommandGenerator.parse(commandDiv);
				if (command != null) {
					if (command.execute(game))
						System.out.println(printer.toString(game));
				}
				else 
					System.err.format(ERROR_COMMAND_MSG);
			} 
			catch (CommandParseException | CommandExecuteException ex) {
				System.err.format(ex.getMessage() + "\nCause of Exception:\n  " + ex.getCause() + "\n");
			}
		}
		
		System.out.println(game.getWinnerMessage());
			
	}
}
