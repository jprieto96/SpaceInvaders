package tp.p2.model;

public enum Level {
 
	EASY(4, 2, 0.1, 3, 0.5, 1, 1, 2, 4), 
	HARD(8, 2, 0.3, 2, 0.2, 2, 1, 3, 4),
	INSANE(8, 4, 0.5, 1, 0.1, 2, 1, 3, 3);

	private int numRegularAliens;
	private int numDestroyerAliens;
	private int numCyclesToMoveOneCell;
	private double ovniFrequency;
	private double shootFrequency;
	private int numRowsOfRegularAliens;
	private int initialRowRegularShips;
	private int initialRowDestroyerShips;
	private int initialColDestroyerShips;

	private Level(
			int numRegularAliens, 
			int numDestroyerAliens, 
			double shootFrequency, 
			int numCyclesToMoveOneCell, 
			double ovniFrequency, 
			int numRowsOfRegularAliens,
			int initialRowRegularShips,
			int initialRowDestroyerShips,
			int initialColDestroyerShips)
	{
		this.numRegularAliens = numRegularAliens;
		this.numDestroyerAliens = numDestroyerAliens;
		this.shootFrequency = shootFrequency;
		this.numCyclesToMoveOneCell = numCyclesToMoveOneCell;
		this.ovniFrequency = ovniFrequency;
		this.numRowsOfRegularAliens = numRowsOfRegularAliens;
		this.initialRowRegularShips = initialRowRegularShips;
		this.initialRowDestroyerShips = initialRowDestroyerShips;
		this.initialColDestroyerShips = initialColDestroyerShips;
	}
	

	public int getNumRegularAliens() {
		return numRegularAliens;
	}
	
	public int getNumDestroyerAliens() {
		return numDestroyerAliens;
	}

	public Double getShootFrequency() {
		return shootFrequency;
	}
	
	public int getNumCyclesToMoveOneCell() {
		return numCyclesToMoveOneCell;
	}

	public Double getOvniFrequency() { 
		return ovniFrequency;
	}
	public int getNumRowsOfRegularAliens() {
		return numRowsOfRegularAliens;
	}
	
	public int getNumRegularAliensPerRow() {
		return numRegularAliens / numRowsOfRegularAliens;
	}
	
	public int  getNumDestroyerAliensPerRow() {
		return getNumDestroyerAliens();
	}
	
	public static Level fromParam(String param) {
		Level l = null;
		for (Level level : Level.values())
			if (level.name().equalsIgnoreCase(param)) l = level;
		return l;
	}

	public Double getTurnExplodeFreq(){
		return 0.05;
	}

	public int getRowRegularShips() {
		return initialRowRegularShips;
	}

	public int getInitialRowDestroyerShips() {
		return initialRowDestroyerShips;
	}

	public int getInitialColDestroyerShips() {
		return initialColDestroyerShips;
	}
}