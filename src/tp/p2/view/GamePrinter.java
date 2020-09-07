package tp.p2.view;

import tp.p2.model.Game;

public abstract class GamePrinter {
	
	protected int rows;
	protected int cols;
	
	public GamePrinter(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}
	
	public GamePrinter() {}
	
	public abstract String toString(Game game);

}
