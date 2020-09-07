package tp.p2.model.gameobjects;

import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;
import tp.p2.model.IExecuteRandomActions;
import tp.p2.model.Move;

public class RegularShip extends Alien{

	@SuppressWarnings("unused")
	private int harm;
	
	public static final int HARM_REGULAR_SHIP = 0;
	public static int LIVE_REGULARSHIP = 2;
	private final static String name = "Regular Ship";
	private final static String shortcut = "R";
	
	public RegularShip(Game game, int x, int y, int nextCycleToMove) {
		super(game, x, y, LIVE_REGULARSHIP, nextCycleToMove, name, shortcut);
		harm = HARM_REGULAR_SHIP;
	}
	
	// Constructor used to Load Game
	public RegularShip(Game game, int x, int y, int live, int nextCycleToMoveAlien, int aliensOnBorder,  Move move) {
		super(game, x, y, live, nextCycleToMoveAlien, aliensOnBorder, name, shortcut);
		harm = HARM_REGULAR_SHIP;
	}

	public RegularShip() {}

	@Override
	public String toString() {
		return "R["+live+"]";
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		game.receivePoints(UCMShip.POINTS_KILL_REGULAR_SHIP);
	}
	
	public static String getInfo() {
		return "[R]egular ship : Points:" + UCMShip.POINTS_KILL_REGULAR_SHIP + " - Harm: " + RegularShip.HARM_REGULAR_SHIP +
				" - Shield: " + RegularShip.LIVE_REGULARSHIP + "\n";
	}

	@Override
	public void computerAction() {
		if(IExecuteRandomActions.canConvertToExplosiveShip(game)) convertToExplosiveShip();
	}

	private void convertToExplosiveShip() {
		ExplosiveShip es = new ExplosiveShip(game, x, y, live, nextCycleToMove);
		NUM_ALIENS--;
		game.swapObjectsOnBoard(this, es);
	}
	
	@Override
	public String serialize() {
		return name + ": " + shortcut + ";" + x + "," + y + ";" + live + ";" + nextCycleToMove + ";" + move + ";" + ALIENS_ON_BORDER + "\n";
	}

	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws NumberFormatException{
		GameObject ob = null;
		if(verifier.verifyRegularShipString(stringFromFile, game, LIVE_REGULARSHIP)) {
			String[] words = stringFromFile.split(FileContentsVerifier.separator1);
			String[] coords = words[1].split(FileContentsVerifier.separator2);
			ob = new RegularShip(game, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]),
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), Integer.parseInt(words[5]), Move.fromParam(words[4]));
		}
		return ob;
	}

}
