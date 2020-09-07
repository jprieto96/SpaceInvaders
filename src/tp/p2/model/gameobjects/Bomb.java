package tp.p2.model.gameobjects;

import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;

public class Bomb extends Weapon{
	
	private DestroyerShip destroyerShip;

	private int harm;
	private boolean enable;
	private static String name = "Bomb";
	private static String shortcut = "B";
	
	private static int HARM_BOMB = 1;
	
	public Bomb(Game game, int x, int y, DestroyerShip destroyerShip) {
		super(game, x, y, LIVE_WEAPON, name, shortcut);
		enable = true;
		harm = HARM_BOMB;
		this.destroyerShip = destroyerShip;
	}
	
	public Bomb() {
		super();
		enable = true;
		harm = HARM_BOMB;
	}

	public void move() {
		y++;
		if(y >= Game.DIM_Y) {
			disableBomb();
		}
	}

	private void disableBomb() {
		live = 0;
		enable = false;
	}

	@Override
	public String toString() {
		return ".";
	}
	
	@Override
	public boolean receiveLaserAttack(int damage) {
		if(!isAlive()) return false;
		getDamage(damage);
		disableBomb();
		return true;
	}
	
	@Override
	public boolean performAttack(GameObject other) {
		if(isAlive() && other.isOnPosition(x, y) && other.receiveBombAttack(harm)) disableBomb();
		return true;
	}

	public boolean isEnable() {
		return enable;
	}

	@Override
	public String serialize() {
		if(!destroyerShip.isAlive() && enable)
			return name + ": " + shortcut + ";" + x + "," + y + "\n";
		else return "";
	}

	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws NumberFormatException{
		GameObject ob = null;
		if(verifier.verifyBombString(stringFromFile, game)) {
			String[] words = stringFromFile.split(FileContentsVerifier.separator1);
			String[] coords = words[1].split(FileContentsVerifier.separator2);
			ob = new Bomb(game, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), null);
		}
		return ob;
	}

}
