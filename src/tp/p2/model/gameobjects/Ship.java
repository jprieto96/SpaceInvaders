package tp.p2.model.gameobjects;

import tp.p2.model.Game;

public abstract class Ship extends GameObject{
	
	public Ship(Game game, int x, int y, int live, String name, String shortcut) {
		super(game, x, y, live, name, shortcut);
	}
	
	public Ship() {
		// TODO Auto-generated constructor stub
	}
	
}
