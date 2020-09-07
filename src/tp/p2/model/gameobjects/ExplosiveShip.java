package tp.p2.model.gameobjects;

import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;
import tp.p2.model.Move;

public class ExplosiveShip extends Alien{

	@SuppressWarnings("unused")
	private int harm;
	
	public static final int HARM_REGULAR_SHIP_EXPLOSIVE = 1;
	public static int LIFE_REGULARSHIP = 2;
	private final static String name = "Explosive Ship";
	private final static String shortcut = "E";

	public ExplosiveShip(Game game, int x, int y, int live, int nextCycleToMoveAlien) {
		super(game, x, y, live, nextCycleToMoveAlien, name, shortcut);
		harm = HARM_REGULAR_SHIP_EXPLOSIVE;
	}
	
	// Constructor used to Load Game
	public ExplosiveShip(Game game, int x, int y, int live, int nextCycleToMoveAlien, int aliensOnBorder, Move move) {
		super(game, x, y, live, nextCycleToMoveAlien, aliensOnBorder, name, shortcut);
		Alien.move = move;
		harm = HARM_REGULAR_SHIP_EXPLOSIVE;
	}

	public ExplosiveShip() {}

	@Override
	public String toString() {
		return "E["+live+"]";
	}
	
	public int getHarm() {
		return harm;
	}

	@Override
	public void onDelete() {
		super.onDelete();
		game.receivePoints(UCMShip.POINTS_KILL_REGULAR_SHIP);
		explode();
	}

	private void explode() {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				game.explosion(x + i, y + j, harm);
			}
		}
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String serialize() {
		return name + ": " + shortcut + ";" + x + "," + y + ";" + live + ";" + nextCycleToMove + ";" + move + ";" + ALIENS_ON_BORDER + "\n";
	}

	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws NumberFormatException{
		GameObject ob = null;
		if(verifier.verifyExplosiveShipString(stringFromFile, game, LIFE_REGULARSHIP)) {
			String[] words = stringFromFile.split(FileContentsVerifier.separator1);
			String[] coords = words[1].split(FileContentsVerifier.separator2);
			ob = new ExplosiveShip(game, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]),
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), Integer.parseInt(words[5]), Move.fromParam(words[4]));
		}
		return ob;
	}

}
