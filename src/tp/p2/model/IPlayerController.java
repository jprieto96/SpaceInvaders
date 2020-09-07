package tp.p2.model;

import tp.p2.control.exceptions.MoveException;
import tp.p2.control.exceptions.ShockWaveException;
import tp.p2.control.exceptions.ShootException;
import tp.p2.model.gameobjects.GameObject;

public interface IPlayerController {
	
	// PLAYER ACTIONS	
	public void move (String direction, int numCells) throws MoveException;
	public void shootLaser() throws ShootException;
	public void shootSuperLaser() throws ShootException;
	public void shockWave() throws ShockWaveException;
	
	
	// CALLBACKS
	public void receivePoints(int points);
	public void addSuperLaser();
	public void removeSuperLaser();
	public void enableShockWave();
	public void explosion(int x, int y, int damage);
	public void disablePlayerShoot();
	public void setPlayerShoot(GameObject ob);
	public void setPlayer(GameObject ob);
}
