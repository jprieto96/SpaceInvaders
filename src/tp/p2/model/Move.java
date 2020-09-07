package tp.p2.model;

public enum Move {
	
	Left, Right;
	
	public static Move fromParam(String param){
		Move m = null;
		for (Move move : Move.values()) {
			if (move.name().equalsIgnoreCase(param)) {
				m = move;
				break;
			}
		}
		return m;	
	}

}
