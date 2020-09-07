package tp.p2.model.gameobjects;

import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;
import tp.p2.model.IExecuteRandomActions;
import tp.p2.model.Move;

public class DestroyerShip extends Alien{

	@SuppressWarnings("unused")
	private int harm;
	protected Bomb bomb;
	
	public static int LIFE_DESTROYERSHIP = 1;
	public static int HARM_DESTROYERSHIP = 1;
	private static String name = "Destroyer Ship";
	private static String shortcut = "D";
	
	public DestroyerShip(Game game, int x, int y, int nextCycleToMove) {
		super(game, x, y, LIFE_DESTROYERSHIP, nextCycleToMove, name, shortcut);
		harm = HARM_DESTROYERSHIP;
	}
	
	// Constructor used to Load Game
	public DestroyerShip(Game game, int x, int y, int live, int nextCycleToMoveAlien, Move move, int aliensOnBorder) {
		super(game, x, y, live, nextCycleToMoveAlien, aliensOnBorder, name, shortcut);
		this.nextCycleToMove = nextCycleToMoveAlien;
		Alien.move = move;
		harm = HARM_DESTROYERSHIP;
	}
	
	public DestroyerShip() {}
	
	@Override
	public String toString() {
		return "D["+live+"]";
	}
	
	public static String getInfo() {
		return "[D]estroyer ship : Points:" + UCMShip.POINTS_KILL_DESTROYER_SHIP + " - Harm: " +
				DestroyerShip.HARM_DESTROYERSHIP + " - Shield: " + DestroyerShip.LIFE_DESTROYERSHIP + "\n";
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		game.receivePoints(UCMShip.POINTS_KILL_DESTROYER_SHIP);
	}

	@Override
	public void computerAction() {
		if((bomb == null || !bomb.isEnable()) && isAlive() && IExecuteRandomActions.canGenerateRandomBomb(game)) {
			bomb = new Bomb(game, x, y + 1, this);
			game.addObject(bomb);
		}
	}

	@Override
	public String serialize() {
		if(bomb == null || !bomb.isEnable()) return name + ": " + shortcut + ";" + x + "," + y + ";" + live + ";" + nextCycleToMove + ";" + move + ";" + ALIENS_ON_BORDER + "\n";
		else return name + ": " + shortcut + ";" + x + "," + y + ";" + live + ";" + nextCycleToMove + ";" + move + ";" + ALIENS_ON_BORDER + ";" +
				bomb.getShortcut() + ";" + bomb.getX() + "," + bomb.getY() + "\n";
	}

	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws NumberFormatException{
		GameObject ob = null;
		if(verifier.verifyDestroyerShipString(stringFromFile, game, LIFE_DESTROYERSHIP)) {
			String[] words = stringFromFile.split(FileContentsVerifier.separator1);
			String[] coords = words[1].split(FileContentsVerifier.separator2);
			ob = new DestroyerShip(game, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]),
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), Move.fromParam(words[4]), Integer.parseInt(words[5]));
			if(words.length == 8) {
				String[] coordsBomb = words[7].split(FileContentsVerifier.separator2);
				((DestroyerShip)ob).bomb = new Bomb(game, Integer.parseInt(coordsBomb[0]), Integer.parseInt(coordsBomb[1]), this);
				game.addObject(((DestroyerShip)ob).bomb);
			}
		}
		return ob;
	}

}
