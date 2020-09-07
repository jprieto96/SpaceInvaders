package tp.p2.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

import tp.p2.control.exceptions.FileContentsException;
import tp.p2.control.exceptions.MoveException;
import tp.p2.control.exceptions.ShockWaveException;
import tp.p2.control.exceptions.ShootException;
import tp.p2.model.gameobjects.Alien;
import tp.p2.model.gameobjects.DestroyerShip;
import tp.p2.model.gameobjects.GameObject;
import tp.p2.model.gameobjects.Ovni;
import tp.p2.model.gameobjects.RegularShip;
import tp.p2.model.gameobjects.UCMShip;

public class Game implements IPlayerController, Cloneable{
	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;
	public final static String ERROR_FILE = "Invalid file, unrecognised line prefix";

	private int currentCycle;
	private Random rand;
	private Level level;

	GameObjectBoard board;

	private UCMShip player;
	
	private boolean doExit;
	private BoardInitializer initializer;
	
	public Game (Level level, Random random){
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
	}
	
	public void initGame () {
		currentCycle = 0;
		board = initializer.initialize(this, level);
		player = new UCMShip(this, DIM_X / 2, DIM_Y - 1);
		board.add(player);
	}
	
	public void resetBoard () {
		board = new GameObjectBoard(Game.DIM_X, Game.DIM_Y);
	}

	public GameObjectBoard getBoard() {
		return board;
	}

	public int getCurrentCycle() {
		return currentCycle;
	}

	public Random getRandom() {
		return rand;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void reset() {
		initGame();
	}

	public void addObject(GameObject object) {
		board.add(object);
	}
	
	public void addObjectOnFirstPosition(GameObject go) {
		board.addOnFirstPosition(go);
	}
	
	public void removeObject(GameObject object) {
		board.remove(object);
	}
	
	public String getPositionToString(int x, int y) {
		return board.toString(x, y);
	}

	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}
	
	public String listInfoGame() {
		return player.getInfo() + RegularShip.getInfo() + DestroyerShip.getInfo() + Ovni.getInfo();
	}
	
	public boolean aliensWin() {
		return player.isDead() || Alien.haveLanded();
	}
	
	private boolean playerWin () {
		return Alien.allDead();
	}
	
	public void update() {
		board.update();
		board.computerAction();
		currentCycle++;
	}
	
	public boolean isOnBoard(int x, int y) {
		return x >= 0 && y >= 0 && x < DIM_X && y < DIM_Y;
	}
	
	public void exit() {
		doExit = true;
	}
	
	public int getPlayerScore() {
		return player.getScore();
	}
	
	public String infoToString() {
		return "Cycles: " + currentCycle + "\n" +
			player.stateToString() +
			"Remaining aliens: " + Alien.getRemainingAliens() + "\n"; 
	}
	
	public UCMShip getPlayer() {
		return player;
	}

	public boolean isOnBorder(int x) {
		return x == DIM_X - 1 || x == 0;
	}
	
	public int getPlayerSuperLasers() {
		return player.getNumSuperLasers();
	}
	
	public String getWinnerMessage () {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else if (doExit) return "Player exits the game";
		else return "This should not happen";
	}
	
	public void swapObjectsOnBoard(GameObject ob1, GameObject ob2) {
		board.swap(ob1, ob2);
	}
	
	public String serialize() {
		String gameSerialized = "";
		gameSerialized += "— Space Invaders v2.0 — \n\n";
		gameSerialized += "Game: G;" + currentCycle + "\n";
		gameSerialized += "Level: L;" + level + "\n";
		gameSerialized += board.serialize();
		return gameSerialized;
	}
	
	public void load(BufferedReader br) throws FileContentsException, IOException, CloneNotSupportedException {
		// Primero salvamos el juego por si falla cualquier cosa durante la carga
		Game gameCopy = (Game) this.clone();
		resetBoard();
		Alien.resetStaticVariables();
		FileContentsVerifier verifier = new FileContentsVerifier();
		try {
			// Leemos el game y el level
			String gameLine = br.readLine().trim();
			if(verifier.verifyCycleString(gameLine)) {
				String[] words = gameLine.split(FileContentsVerifier.separator1);
				currentCycle = Integer.parseInt(words[1]);
			}
			else throw new FileContentsException(ERROR_FILE);
			
			String levelLine = br.readLine().trim();
			if(verifier.verifyLevelString(levelLine)) {
				String[] words = levelLine.split(FileContentsVerifier.separator1);
				level = Level.fromParam(words[1]);
			}
			else throw new FileContentsException(ERROR_FILE);
			
			// Ahora leemos los objetos del juego
			String line = br.readLine().trim();
			while( line != null && !line.isEmpty() ) {
				GameObject gameObject = GameObjectGenerator.parse(line, this, verifier);
				if (gameObject == null) throw new FileContentsException(Game.ERROR_FILE);
				board.add(gameObject);
				try {
					line = br.readLine().trim();
				}
				catch(Exception e) {
					line = null;
				}
			}
		}
		catch(NumberFormatException ex) {
			restoreGame(gameCopy);
			throw new FileContentsException(Game.ERROR_FILE, ex);
		}
	}
	
	private void restoreGame(Game gameCopy) {
		this.board = gameCopy.board;
		this.player = gameCopy.player;
		this.currentCycle = gameCopy.currentCycle;
		this.level = gameCopy.level;
	}

	// Metodos para el funcionamiento de PLAYER
	
	@Override
	public void move(String direction, int numCells) throws MoveException{
		player.move(direction, numCells);
	}

	@Override
	public void shootLaser() throws ShootException{
		player.shootLaser();
	}

	@Override
	public void shockWave() throws ShockWaveException{
		player.shockWave();
	}

	@Override
	public void receivePoints(int points) {
		player.setScore(points);
	}

	@Override
	public void enableShockWave() {
		player.enableShockWave();
	}

	@Override
	public void addSuperLaser() {
		player.addSuperLaser();
	}

	@Override
	public void shootSuperLaser() throws ShootException{
		player.shootSuperLaser();
	}

	@Override
	public void removeSuperLaser() {
		player.removeSuperLaser();
	}

	@Override
	public void explosion(int x, int y, int damage) {
		board.explosion(x, y, damage);
	}

	@Override
	public void disablePlayerShoot() {
		player.disableShoot();
	}

	// Los metodos de abajo solo son usados para el LOAD
	@Override
	public void setPlayerShoot(GameObject ob) {
		player.setShoot(ob);
	}

	@Override
	public void setPlayer(GameObject ob) {
		((UCMShip) ob).setShoot(player.getShoot());
		player = (UCMShip) ob;
	}
	
}
