package tp.p2.model;

import tp.p2.model.gameobjects.GameObject;

public interface IAttack {
	default boolean performAttack(GameObject other) {return false;};

	default boolean receiveLaserAttack(int  damage) {return false;};
	default boolean receiveBombAttack(int damage) {return false;};
	default boolean receiveShockWaveAttack(int damage) {return false;};
}
