package tp.p2.model.gameobjects;

import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;

public class UCMShipLaser extends Weapon{

	private int harm;
	
	private static int HARM_UCMSHIPLASER = 1;
	private static String name = "Laser";
	private static String shortcut = "L";
	
	public UCMShipLaser(Game game, int x, int y) {
		super(game, x, y, LIVE_WEAPON, name, shortcut);
		harm = HARM_UCMSHIPLASER;
	}
	
	public UCMShipLaser(Game game, int x, int y, String name, String shortcut) {
		super(game, x, y, LIVE_WEAPON, name, shortcut);
	}
	
	public UCMShipLaser() {
		super();
		harm = HARM_UCMSHIPLASER;
	}

	@Override
	public String toString() {
		return "oo";
	}
	
	@Override
	public boolean receiveBombAttack(int damage) {
		if(!isAlive()) return false;
		getDamage(damage);
		disableLaser();
		return true;
	}
	
	@Override
	public boolean performAttack(GameObject other) {
		if(isAlive() && other.isOnPosition(x, y) && other.receiveLaserAttack(harm)) disableLaser();
		return true;
	}

	@Override
	public void move() {
		if(isAlive()) {
			y--;
			if (y < 0) disableLaser();
		}	
	}

	protected void disableLaser() {
		live = 0;
		game.disablePlayerShoot();
	}
	
	@Override
	public String serialize() {
		return name + ": " + shortcut + ";" + x + "," + y + "\n";
	}

	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws NumberFormatException{
		GameObject ob = null;
		if(verifier.verifyLaserString(stringFromFile, game)) {
			String[] words = stringFromFile.split(FileContentsVerifier.separator1);
			String[] coords = words[1].split(FileContentsVerifier.separator2);
			ob = new UCMShipLaser(game, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
			game.setPlayerShoot(ob);
		}
		return ob;
	}

}
