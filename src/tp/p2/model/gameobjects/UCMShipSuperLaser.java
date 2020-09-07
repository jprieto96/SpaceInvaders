package tp.p2.model.gameobjects;

import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;

public class UCMShipSuperLaser extends UCMShipLaser{
	
	private int harm;

	private static int HARM_UCMSHIPSUPERLASER = 2;
	private final static String name = "SuperLaser";
	private final static String shortcut = "X";
	
	public UCMShipSuperLaser(Game game, int x, int y) {
		super(game, x,  y, name, shortcut);
		harm = HARM_UCMSHIPSUPERLASER;
	}
	
	public UCMShipSuperLaser() {
		super();
		harm = HARM_UCMSHIPSUPERLASER;
	}
	
	@Override
	public boolean performAttack(GameObject other) {
		if(isAlive() && other.isOnPosition(x, y) && other.receiveLaserAttack(harm)) disableLaser();
		return true;
	}

	@Override
	public String toString() {
		return "ττ";
	}
	
	@Override
	public String serialize() {
		return name + ": " + shortcut + ";" + x + "," + y + "\n";
	}
	
	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws NumberFormatException{
		GameObject ob = null;
		if(verifier.verifySuperLaserString(stringFromFile, game)) {
			String[] words = stringFromFile.split(FileContentsVerifier.separator1);
			String[] coords = words[1].split(FileContentsVerifier.separator2);
			ob = new UCMShipSuperLaser(game, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
			game.setPlayerShoot(ob);
		}
		return ob;
	}

}
