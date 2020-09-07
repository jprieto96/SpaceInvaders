package tp.p2.model.gameobjects;

import tp.p2.control.exceptions.MoveException;
import tp.p2.control.exceptions.ShockWaveException;
import tp.p2.control.exceptions.ShootException;
import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;

public class UCMShip extends Ship{
	
	private UCMShipLaser shoot;
	private int numSuperLasers;
	private ShockWave shockWave;
	private int score;
	
	private final static String name = "UCMShip (player)";
	private final static String shortcut = "P";
	private static int HARM_UCMSHIP = 0;
	private static int LIFE_UCMSHIP = 3;
	static final int POINTS_KILL_REGULAR_SHIP = 5;
	static final int POINTS_KILL_DESTROYER_SHIP = 10;
	public static final int POINTS_KILL_OVNI = 25;
	private static final String SHOCKWAVE_ON_MSG = "ON";
	private static final String SHOCKWAVE_OFF_MSG = "OFF";
	private static String LIFE_MSG = "Life: ";
	private static String POINTS_MSG = "Points: ";
	private static String SHOCKWAVE_MSG = "Shockwave: ";
	private static String CURRENT_SUPERLASERS_MSG = "SuperLasers: ";
	private static String MOVE_ERROR_MSG = "Cannot perform move: ship too near border";
	private static final String SHOCKWAVE_EXCEPTION_MSG = "Cannot release shockwave: no shockwave available";
	private static final String SHOOT_EXCEPTION_MSG = "Cannot fire missile: missile already exists on board";
	
	public UCMShip(Game game, int x, int y) {
		super(game, x, y, LIFE_UCMSHIP, name, shortcut);
		shockWave = new ShockWave(game, x, y, 0);
		score = 0;
	}
	
	public UCMShip(Game game, int x, int y, int live, int score, boolean enabled, int numSuperlasers) {
		super(game, x, y, live, name, shortcut);
		shockWave = new ShockWave(game, x, y, 0);
		this.score = score;
		shockWave.setEnable(enabled);
		this.numSuperLasers = numSuperlasers;
	}

	public UCMShip() {
		live = LIFE_UCMSHIP;
		shockWave = new ShockWave(game, x, y, 0);
		score = 0;
	}

	public int getNumSuperLasers() {
		return numSuperLasers;
	}
	
	@Override
	public boolean isAlive() {
		return true;
	}
	
	public boolean isDead() {
		return live <= 0;
	}

	public boolean isShockWave() {
		return shockWave.isEnable();
	}

	public void setScore(int score) {
		this.score += score;
	}
	
	public void move(String dir, int num) throws MoveException {
		if(dir.equalsIgnoreCase("left")) goLeft(num); 
		else goRight(num);
	}

	public void goLeft(int num) throws MoveException{
		x -= num;
		
		if(x < 0) {
			x = 0;
			throw new MoveException(MOVE_ERROR_MSG);
		}	
	}
	
	public void goRight(int num) throws MoveException{
		x += num;
		
		if(x >= Game.DIM_X) {
			x = Game.DIM_X - 1;
			throw new MoveException(MOVE_ERROR_MSG);
		}		
	}
	
	public String getInfo() {
		return "^__^: Harm: " + HARM_UCMSHIP + " - Shield: " + LIFE_UCMSHIP + "\n";
	}

	public void enableShockWave() {
		shockWave.enable();
	}
	
	@Override
	public void move() {
		
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		if(!isDead())
			return "^__^";
		else
			return "!xx!";
	}

	public void shootLaser() throws ShootException{
		if(!isShootOnBoard()) {
			shoot = new UCMShipLaser(game, x, y);
			game.addObjectOnFirstPosition(shoot);
		}
		else throw new ShootException(SHOOT_EXCEPTION_MSG);
	}
	
	@Override
	public boolean receiveBombAttack(int damage) {
		getDamage(damage);
		return true;
	}

	public void shockWave() throws ShockWaveException{
		if(isShockWave()) {
			shockWave.live = Alien.NUM_ALIENS;
			game.addObject(shockWave); 
		}
		else throw new ShockWaveException(SHOCKWAVE_EXCEPTION_MSG);
	}

	public String stateToString() {
		String sWave = (shockWave.isEnable()) ? SHOCKWAVE_ON_MSG : SHOCKWAVE_OFF_MSG;
		return LIFE_MSG + live+"\n"+
		POINTS_MSG + score+"\n"+
		SHOCKWAVE_MSG + sWave + "\n"+
		CURRENT_SUPERLASERS_MSG + numSuperLasers + "\n"; 
	}

	public void disableShoot() {
		shoot = null;
	}

	public void addSuperLaser() {
		numSuperLasers++;
	}

	public int getScore() {
		return score;
	}

	public void shootSuperLaser() throws ShootException{
		if(!isShootOnBoard()) {
			shoot = new UCMShipSuperLaser(game, x, y);
			game.addObjectOnFirstPosition(shoot);
		}
		else throw new ShootException(SHOOT_EXCEPTION_MSG);
	}

	public void removeSuperLaser() {
		numSuperLasers--;
	}

	public boolean isShootOnBoard() {
		return shoot != null;
	}
	
	@Override
	public String serialize() {
		return name + ": " + shortcut + ";" + x + "," + y + ";" + live + ";" + score + ";" + shockWave.isEnable() + ";" + numSuperLasers + "\n";
	}
	
	public void setShoot(GameObject go) {
		shoot = (UCMShipLaser) go;
	}
	
	public void setShockwave(ShockWave shockWave) {
		this.shockWave = shockWave;
	}

	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws NumberFormatException{
		GameObject ob = null;
		if(verifier.verifyPlayerString(stringFromFile, game, LIFE_UCMSHIP)) {
			String[] words = stringFromFile.split(FileContentsVerifier.separator1);
			String[] coords = words[1].split(FileContentsVerifier.separator2);
			ob = new UCMShip(game, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]),
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), Boolean.parseBoolean(words[4]),
					Integer.parseInt(words[5]));
			game.setPlayer(ob);
		}
		return ob;
	}

	public GameObject getShoot() {
		return shoot;
	}

}
