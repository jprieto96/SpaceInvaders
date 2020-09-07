package tp.p2.model.gameobjects;

import tp.p2.model.Game;
import tp.p2.model.Move;

public abstract class Alien extends EnemyShip{

	protected static int ALIENS_ON_BORDER = 0;
	public static int NUM_ALIENS;
	private static int aliensMoved;
	private static int finalRowAliens;
	private static boolean alienOnBorder = false;
	protected static Move move = Move.Left;
	protected int nextCycleToMove;

	public Alien(Game game, int x, int y, int live, int nextCycleToMove, String name, String shortcut) {
		super(game, x, y, live, name, shortcut);
		NUM_ALIENS++;
		this.nextCycleToMove = nextCycleToMove;
	}
	
	// Constructor used to Load Game
	public Alien(Game game, int x, int y, int live, int nextCycleToMove, int aliensOnBorder, String name, String shortcut) {
		super(game, x, y, live, name, shortcut);
		NUM_ALIENS++;
		ALIENS_ON_BORDER = aliensOnBorder;
		this.nextCycleToMove = nextCycleToMove;
	}
	
	public Alien() {}
	
	@Override
	public void move() {
		if(isTurnToMoveAlienShips()) {
			if(ALIENS_ON_BORDER == 0) moveLeftOrRight();
			else moveDown();
			checkFinalRow();
		}
	}

	private void moveLeftOrRight() {
		if(move == Move.Right) x++;
		else x--;
		aliensMoved++;
		if(isOnBorder()) alienOnBorder = true;
		incrementTurnsToMoveEnemyShips();
		if(aliensMoved == NUM_ALIENS) {
			if(alienOnBorder) {
				checkMoveAfterDown();
				ALIENS_ON_BORDER = NUM_ALIENS;
			}
			aliensMoved = 0;
		}
	}

	public void setNextCycleToMoveAlien(int nextCycleToMoveAlien) {
		this.nextCycleToMove = nextCycleToMoveAlien;
	}

	private void checkMoveAfterDown() {
		if(move == Move.Right) move = Move.Left;
		else if(move == Move.Left) move = Move.Right;
	}

	private void moveDown() {
		y++;
		ALIENS_ON_BORDER--;
		aliensMoved++;
		incrementTurnsToMoveEnemyShips();
		if(aliensMoved == NUM_ALIENS) {
			alienOnBorder = false;
			aliensMoved = 0;
		}
	}
	
	private void checkFinalRow() {
		if(y > finalRowAliens) finalRowAliens = y;
	}

	public static int getRemainingAliens() {
		return NUM_ALIENS;
	}

	public static boolean allDead() {
		return NUM_ALIENS == 0;
	}
	
	protected boolean isTurnToMoveAlienShips() {
		return game.getCurrentCycle() + 1 == nextCycleToMove;
	}
	
	public void incrementTurnsToMoveEnemyShips() {
		nextCycleToMove += game.getLevel().getNumCyclesToMoveOneCell();
	}

	public static boolean haveLanded() {
		return finalRowAliens == Game.DIM_Y - 1;
	}
	
	@Override
	public void onDelete() {
		NUM_ALIENS--;
		if(ALIENS_ON_BORDER != 0) ALIENS_ON_BORDER--;
	}
	
	@Override
	public boolean receiveShockWaveAttack(int damage) {
		getDamage(damage);
		return true;
	}

	public static void resetStaticVariables() {
		NUM_ALIENS = 0;
		move = Move.Left;
		ALIENS_ON_BORDER = 0;
		alienOnBorder = false;
		aliensMoved = 0;
		finalRowAliens = 0;
	}

}