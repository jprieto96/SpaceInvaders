package tp.p2.model.gameobjects;

import tp.p2.model.FileContentsVerifier;
import tp.p2.model.Game;
import tp.p2.model.IAttack;

public abstract class GameObject implements IAttack {
	
	protected int x;
	protected int y;
	protected int live;
	protected Game game;
	protected String name;
	protected String shortcut;
	
	public GameObject(Game game, int x, int y, int live, String name, String shortcut) {	
		this.x = x;
		this.y = y;
		this.game = game;
		this.live = live;
		this.name = name;
		this.shortcut = shortcut;
	}
	
	public GameObject() {
		// TODO Auto-generated constructor stub
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public String getShortcut() {
		return shortcut;
	}

	public boolean isAlive() {
		return this.live > 0;
	}

	public int getLive() {
		return this.live;
	}
	
	public boolean isOnPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	public void getDamage (int damage) {
		this.live = damage >= this.live ? 0 : this.live - damage;
	}
	
	public boolean isOut() {
		return !game.isOnBoard(x, y);
	}
	
	protected boolean isOnBorder() {
		return game.isOnBorder(x);
	}

	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move();
	public abstract String toString();
	public abstract GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws NumberFormatException;
	public abstract String serialize();

}
