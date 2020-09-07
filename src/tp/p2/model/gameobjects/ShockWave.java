package tp.p2.model.gameobjects;

import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;

public class ShockWave extends Weapon{
	
	private boolean enable;
	private int harm;
	private static int HARM_SHOCKWAVE = 1;
	private final static String name = "ShockWave";
	private final static String shortcut = "S";
	
	public ShockWave(Game game, int x, int y, int live) {
		super(game, x, y, live, name, shortcut);
		enable = false;
		harm = HARM_SHOCKWAVE;
	}
	
	public ShockWave() {
		super();
		enable = false;
		harm = HARM_SHOCKWAVE;
	}

	public void enable() {
		enable = true;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean performAttack(GameObject other) {
		if(enable && other.receiveShockWaveAttack(harm)) live--;
		if(!isAlive()) enable = false;
		return true;
	}
	
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEnable() {
		return enable;
	}
	
	@Override
	public String serialize() {
		return "";
	}
	
	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)
			throws NumberFormatException {
		// TODO Auto-generated method stub
		return null;
	}

}
