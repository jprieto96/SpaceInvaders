package tp.p2.view;

import tp.p2.model.Game;

public class Serializer extends GamePrinter {

	@Override
	public String toString(Game game) {
		return game.serialize();
	}
	
}
