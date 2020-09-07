package tp.p2.model;

public class FileContentsVerifier{
	
	public static final String separator1 = ";";
	public static final String separator2 = ",";
	public static final String labelRefSeparator = " - ";

	private String foundInFileString = "";
	
	private void appendToFoundInFileString(String linePrefix) throws NumberFormatException{
		foundInFileString += linePrefix;
	}

	// Don't catch NumberFormatException.
	public boolean verifyCycleString(String cycleString) throws NumberFormatException{
		String[] words = cycleString.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 2
				|| !verifyCurrentCycle(Integer.parseInt(words[1])))
			return false;
		return true;
	}
	
	public boolean verifyLevelString(String levelString) throws NumberFormatException{
		String[] words = levelString.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 2
				|| !verifyLevel(Level.fromParam(words[1])))
			return false;
		return true;
	}
	
	// Don't catch NumberFormatException.
	public boolean verifyOvniString(String lineFromFile, Game game, int armour) throws NumberFormatException{
		String[] words = lineFromFile.split(separator1);
		String[] name = words[0].split(":");
		appendToFoundInFileString(words[0]);
		if (words.length != 4) return false;
		String[] coords = words[1].split(separator2);
		if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLives(Integer.parseInt(words[2]), armour)
				|| !verifyBool(words[3])
				|| !name[0].equalsIgnoreCase("Ovni")) 
			return false;
		return true;
	}

	// Don't catch NumberFormatException.
	public boolean verifyPlayerString(String lineFromFile, Game game, int armour) throws NumberFormatException{
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 6) return false;
		String[] name = words[0].split(":");
		String[] coords = words[1].split(separator2);
		
		if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLives(Integer.parseInt(words[2]), armour)
				|| !verifyPoints(Integer.parseInt(words[3]))
				|| !verifyBool(words[4])
				|| !verifySuperLasers(Integer.parseInt(words[5]))
				|| !name[0].equalsIgnoreCase("UCMShip (player)"))
			return false;
		return true;
	}
	
	// Don't catch NumberFormatException.
	public boolean verifyExplosiveShipString(String lineFromFile, Game game, int armour) throws NumberFormatException{
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 6) return false;
		String[] name = words[0].split(":");
		String[] coords = words[1].split(separator2);
	
		if ((!verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLives(Integer.parseInt(words[2]), armour)
				|| !verifyCycleToNextAlienMove(Integer.parseInt(words[3]), game.getLevel())
				|| !verifyDir(Move.fromParam(words[4]))
				|| !verifyAliensOnBorder(Integer.parseInt(words[5]))
				|| !name[0].equalsIgnoreCase("Explosive Ship")))
			return false;
		return true;
	}

	// Don't catch NumberFormatException.
	public boolean verifyRegularShipString(String lineFromFile, Game game, int armour) throws NumberFormatException{
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 6) return false;
		String[] name = words[0].split(":");
		String[] coords = words[1].split(separator2);
	
		if ((!verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLives(Integer.parseInt(words[2]), armour)
				|| !verifyCycleToNextAlienMove(Integer.parseInt(words[3]), game.getLevel())
				|| !verifyDir(Move.fromParam(words[4]))
				|| !verifyAliensOnBorder(Integer.parseInt(words[5]))
				|| !name[0].equalsIgnoreCase("Regular Ship")))
			return false;
		return true;
	}
		
	// Don't catch NumberFormatException.
	public boolean verifyDestroyerShipString(String lineFromFile, Game game, int armour) throws NumberFormatException{
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length == 6 || words.length == 8) {
			String[] name = words[0].split(":");
			String[] coords = words[1].split(separator2);
			String[] coordsBomb = null;
			
			if (words.length == 8) coordsBomb = words[7].split(separator2);
		
			if ( words.length == 6 && (!verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
					|| !verifyLives(Integer.parseInt(words[2]), armour)
					|| !verifyCycleToNextAlienMove(Integer.parseInt(words[3]), game.getLevel())
					|| !verifyDir(Move.fromParam(words[4]))
					|| !verifyAliensOnBorder(Integer.parseInt(words[5]))
					|| !name[0].equalsIgnoreCase("Destroyer Ship"))) {
				return false;
			}
			if ( words.length == 8 && (!verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
					|| !verifyLives(Integer.parseInt(words[2]), armour)
					|| !verifyCycleToNextAlienMove(Integer.parseInt(words[3]), game.getLevel())
					|| !verifyDir(Move.fromParam(words[4]))
					|| !verifyAliensOnBorder(Integer.parseInt(words[5]))
					|| !verifyBomb(words[6])
					|| !verifyCoords(Integer.parseInt(coordsBomb[0]), Integer.parseInt(coordsBomb[1]), game)
					|| !name[0].equalsIgnoreCase("Destroyer Ship")))
				return false;
			
			return true;
		}
		else return false;
	}

	// Don't catch NumberFormatException.
	public boolean verifySuperLaserString(String lineFromFile, Game game) throws NumberFormatException{
		String[] words = lineFromFile.split(separator1);
		if (words.length != 2) return false;
		String[] name = words[0].split(":");
		appendToFoundInFileString(words[0]);
		String[] coords = words[1].split(separator2);

		if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game) ||
				!name[0].equalsIgnoreCase("SuperLaser"))
			return false;
		return true;
	}
	
	// Don't catch NumberFormatException.
		public boolean verifyBombString(String lineFromFile, Game game) throws NumberFormatException{
			String[] words = lineFromFile.split(separator1);
			if (words.length != 2) return false;
			String[] name = words[0].split(":");
			appendToFoundInFileString(words[0]);
			String[] coords = words[1].split(separator2);

			if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game) ||
					!name[0].equalsIgnoreCase("Bomb"))
				return false;
			return true;
		}
	
	// Don't catch NumberFormatException.
		public boolean verifyLaserString(String lineFromFile, Game game) throws NumberFormatException{
			String[] words = lineFromFile.split(separator1);
			if (words.length != 2) return false;
			String[] name = words[0].split(":");
			appendToFoundInFileString(words[0]);
			String[] coords = words[1].split(separator2);

			if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game) ||
					!name[0].equalsIgnoreCase("Laser"))
				return false;
			return true;
		}
	
	public boolean verifyRefString(String lineFromFile) throws NumberFormatException{
		String[] words = lineFromFile.split(labelRefSeparator);
		if (words.length != 2 || !verifyLabel(words[1])) return false;
		return true;
	}

	public static boolean verifyLabel(String label) {
		return Integer.parseInt(label) > 0;
	}
	
	public static boolean verifyCoords(int x, int y, Game game) {
		return game.isOnBoard(x, y);
	}
	
	private static boolean verifyAliensOnBorder(int parseInt) {
		return parseInt >= 0;
	}
	
	public static boolean verifyCurrentCycle(int currentCycle) {
		return currentCycle >= 0;
	}
	
	private static boolean verifyBomb(String string) {
		return string.equalsIgnoreCase("b");
	}
	
	public static boolean verifyLevel(Level level) {
		return level != null;
	}
	
	public static boolean verifyDir(Move dir) {
		return dir != null;
	}
	
	public static boolean verifySuperLasers(int numSuperLasers) {
		return numSuperLasers >= 0;
	}
	
	public static boolean verifyLives(int live, int armour) {
		return 0 < live && live <= armour;
	}
	
	public static boolean verifyPoints(int points) {
		return points >= 0;
	}
	
	public static boolean verifyCycleToNextAlienMove(int cycle, Level level) {
		return 0 <= cycle && cycle % level.getNumCyclesToMoveOneCell() == 0;
	}
	
	// parseBoolean converts any string different from "true" to false.
	public static boolean verifyBool(String boolString) {
		return boolString.equals("true") || boolString.equals("false");
	}
}
