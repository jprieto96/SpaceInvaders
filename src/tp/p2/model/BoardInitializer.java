package tp.p2.model;

import tp.p2.model.gameobjects.Alien;
import tp.p2.model.gameobjects.DestroyerShip;
import tp.p2.model.gameobjects.Ovni;
import tp.p2.model.gameobjects.RegularShip;

public class BoardInitializer {
	
	private Level level;
	private GameObjectBoard board;
	private Game game;
	
	public  GameObjectBoard initialize(Game game, Level level) {
		this.level = level;
		this.game = game;
		board = new GameObjectBoard(Game.DIM_X, Game.DIM_Y);
		Alien.resetStaticVariables();
		
		initializeOvni();
		initializeRegularAliens();
		initializeDestroyerAliens();
		return board;
	}
	
	private void initializeOvni () {
		board.add(new Ovni(game));
	}

	private void initializeRegularAliens () {
		int col = Game.DIM_X / 3;
		int row = level.getRowRegularShips();
		int colAux = 0;
		for (int i = 0; i < level.getNumRegularAliens(); i++) {
			board.add(new RegularShip(game, col, row, game.getLevel().getNumCyclesToMoveOneCell()));
			col++;
			colAux++;
			if(colAux == level.getNumRegularAliensPerRow()) {
				row++;
				colAux = 0;
				col = Game.DIM_X / 3;
			}
		}
	}
	
	private void initializeDestroyerAliens() {
		int col = level.getInitialColDestroyerShips();
		int row = level.getInitialRowDestroyerShips();
		for (int i = 0; i < level.getNumDestroyerAliens(); i++) {
			board.add(new DestroyerShip(game, col, row, game.getLevel().getNumCyclesToMoveOneCell()));
			col++;
		}
	}
}
