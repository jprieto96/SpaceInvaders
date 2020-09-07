package tp.p2.model.gameobjects;

import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;
import tp.p2.model.IExecuteRandomActions;

public class Ovni extends EnemyShip implements IExecuteRandomActions{

	@SuppressWarnings("unused")
	private int harm;
	private boolean enabled;
	
	private static int INITIAL_POSITION_X_OVNI = Game.DIM_X - 1;
	private static int INITIAL_POSITION_Y_OVNI = 0;
	private static int LIVE_OVNI = 1;
	private static int HARM_OVNI = 0;
	private final static String name = "Ovni";
	private final static String shortcut = "O";
	
	public Ovni(Game game) {
		super(game, INITIAL_POSITION_X_OVNI, INITIAL_POSITION_Y_OVNI, LIVE_OVNI, name, shortcut);
		harm = HARM_OVNI;
		enabled = false;
	}
	
	public Ovni(Game game, int x, int y, int live, boolean enabled) {
		super(game, x, y, live, name, shortcut);
		harm = HARM_OVNI;
		this.enabled = enabled;
		enabled = false;
	}
	
	public Ovni() {
		harm = HARM_OVNI;
		enabled = false;
	}

	@Override
	public String toString() {
		return (isAlive() && enabled) ? "O["+live+"]" : "";
	}
	
	public void move() {
		if(isEnabled()) {
			x--;
			if(x < 0) disable();
		}
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public static String getInfo() {
		return "[O]vni: Points:" + UCMShip.POINTS_KILL_OVNI + " - Harm: " + HARM_OVNI + " - Shield: " + LIVE_OVNI +"\n";
	}

	public void disable() {
		enabled = false;
		reset();
	}
	
	@Override
	public boolean receiveLaserAttack(int damage) {
		disable();
		// En el ovni si que activo el shockwave y doy la puntuacion al player
		// desde aqui porque no mato al Ovni para tenerlo siempre en la lista de objetos
		game.enableShockWave();
		game.receivePoints(UCMShip.POINTS_KILL_OVNI);
		return true;
	}

	private void reset() {
		x = INITIAL_POSITION_X_OVNI;
		y = INITIAL_POSITION_Y_OVNI;
		live = LIVE_OVNI;
	}

	@Override
	public void computerAction() {
		if(!enabled && IExecuteRandomActions.canGenerateRandomOvni(game)) enabled = true;
	}
	
	@Override
	public String serialize() {
		return name + ": " + shortcut + ";" + x + "," + y + ";" + live + ";" + enabled + "\n";
	}

	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws NumberFormatException{
		GameObject ob = null;
		if(verifier.verifyOvniString(stringFromFile, game, LIVE_OVNI)) {
			String[] words = stringFromFile.split(FileContentsVerifier.separator1);
			String[] coords = words[1].split(FileContentsVerifier.separator2);
			ob = new Ovni(game, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(words[2]),
					Boolean.parseBoolean(words[3]));
		}
		return ob;
	}

}
