package tp.p2;

import java.util.Random;
import java.util.Scanner;

import tp.p2.control.Controller;
import tp.p2.model.Game;
import tp.p2.model.Level;

public class Main {
	
	private static String WELCOME_MSG = "Welcome to Space Invaders!!";
	private static String LEVEL_MSG = "Level: ";
	private static String SEED_MSG = "Seed: ";
	private static String NO_PARAMS_MSG = "Usage: Main <EASY|HARD|INSANE> [seed]";
	private static String SEED_ERROR_MSG = "Usage: Main <EASY|HARD|INSANE> [seed]: the seed must be a number";
	private static String LEVEL_ERROR_MSG = "Usage: Main <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE";

	public static void main(String[] args) {
		
		String levelParam="easy";
		Level level = null;
		int seed = new Random((int) System.currentTimeMillis()).nextInt();
		
		try {
			if(args.length > 0) levelParam = args[0].toLowerCase();
			else System.err.format(NO_PARAMS_MSG +"\n");
			level = Level.fromParam(levelParam);
			if(level == null) {
				System.err.format(LEVEL_ERROR_MSG +"\n");
				level = Level.EASY;
			}
			if(args.length > 1) seed = Integer.parseInt(args[1]);
		}
		catch(NumberFormatException ex) {
			System.err.format(SEED_ERROR_MSG +"\n");
		}	
		
		Random rand = new Random(seed);
		Scanner in = new Scanner(System.in);
		Game game = new Game(level, rand);
		Controller controller = new Controller(game, in);
		System.out.println(WELCOME_MSG);
		System.out.println(LEVEL_MSG + level.name().toUpperCase());
		System.out.println(SEED_MSG + seed + "\n");
			
		controller.run();
	}

}
