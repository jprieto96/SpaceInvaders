package tp.p2.model.gameobjects;

import tp.p2.model.Game;

public abstract class Weapon extends GameObject{

	protected static int LIVE_WEAPON = 1;
	
	public Weapon(Game game, int x, int y, int live, String name, String shortcut) {
		super(game, x, y, live, name, shortcut);
	}
	
	public Weapon() {
		live = LIVE_WEAPON;
	}
	
	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

}
