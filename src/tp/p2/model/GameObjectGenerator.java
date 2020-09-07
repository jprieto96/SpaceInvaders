package tp.p2.model;

import tp.p2.model.gameobjects.Bomb;
import tp.p2.model.gameobjects.DestroyerShip;
import tp.p2.model.gameobjects.ExplosiveShip;
import tp.p2.model.gameobjects.GameObject;
import tp.p2.model.gameobjects.Ovni;
import tp.p2.model.gameobjects.RegularShip;
import tp.p2.model.gameobjects.ShockWave;
import tp.p2.model.gameobjects.UCMShip;
import tp.p2.model.gameobjects.UCMShipLaser;
import tp.p2.model.gameobjects.UCMShipSuperLaser;

public class GameObjectGenerator {

	private static GameObject[] availableGameObjects = {
			new UCMShip(),
			new Ovni(),
			new RegularShip(),
			new DestroyerShip(),
			new ExplosiveShip(),
			new ShockWave(),
			new Bomb(),
			new UCMShipLaser(),
			new UCMShipSuperLaser()
	};
	
	public static GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)
					throws NumberFormatException{		
		GameObject gameObject = null;
		for (GameObject go: availableGameObjects) {
			gameObject = go.parse(stringFromFile, game, verifier);
			if (gameObject != null) break;

		}
		return gameObject;
	}

}
