package tp.p2.model.gameobjects;

import tp.p2.model.Game;

public abstract class EnemyShip extends Ship{

	public EnemyShip(Game game, int x, int y, int live, String name, String shortcut) {
		super(game, x, y, live, name, shortcut);
	}

	@Override
	public boolean receiveLaserAttack(int damage) {
		if(!isAlive()) return false;
		getDamage(damage);
		return true;
	}
	
	public EnemyShip() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

}
