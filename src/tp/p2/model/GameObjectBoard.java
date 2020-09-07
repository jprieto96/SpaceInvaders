package tp.p2.model;

import tp.p2.model.gameobjects.GameObject;

public class GameObjectBoard {
	
	private GameObject[] objects;
	private int currentObjects;
	
	public GameObjectBoard (int width, int height) {
		currentObjects = 0;
		objects = new GameObject[width * height];
	}

	public void add (GameObject object) {
		objects[currentObjects] = object;
		currentObjects++;
	}
	
	public void addOnFirstPosition (GameObject object) {
		currentObjects++;
		GameObject[] aux = new GameObject[Game.DIM_X * Game.DIM_Y];
		aux[0] = object;
		for (int i = 1; i < currentObjects; i++) {
			aux[i] = objects[i - 1];
		}
		objects = aux;
	}

	public void remove (GameObject object) {
		for (int i = 0; i < currentObjects; i++) {
			if (objects[i] == object) {
				objects[i] = objects[currentObjects - 1];
				objects[currentObjects - 1] = null;
				currentObjects--;
				break;
			}
		}
	}
	
	public void update() {
		for (int i = 0; i < currentObjects; i++) {
			objects[i].move();
			checkAttacks(objects[i]);
		}
		removeDead();
	}
	
	public void checkAttacks(GameObject object) {
		for (int i = 0; i < currentObjects; i++) {
			object.performAttack(objects[i]);
		}
	}
	
	public void computerAction() {
		for (int i = 0; i < currentObjects; i++) {
			objects[i].computerAction();
			checkAttacks(objects[i]);
		}
		removeDead();
	}
	
	public void removeDead() {
		for (int i = 0; i < currentObjects; i++) {
			if(!objects[i].isAlive()) {
				objects[i].onDelete();
				remove(objects[i]);
			}
		}
	}

	public String toString(int x, int y) {
		String object = "";
		for(int i = 0; i < currentObjects; i++) 
			if(objects[i].isOnPosition(x, y)) object = objects[i].toString();
		return object;
	}

	public String serialize() {
		String gameObjectsSerialized = "";
		for(int i = 0; i < currentObjects; i++) gameObjectsSerialized += objects[i].serialize();
		return gameObjectsSerialized;
	}

	public void explosion(int x, int y, int damage) {
		for(int i = 0; i < currentObjects; i++) {
			if(objects[i].isOnPosition(x, y)) objects[i].getDamage(damage);
		}
	}

	public void swap(GameObject ob1, GameObject ob2) {
		for(int i = 0; i < currentObjects; i++)
			if(objects[i] == ob1) objects[i] = ob2;
	}

}
