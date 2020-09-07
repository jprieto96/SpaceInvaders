package tp.p2.view;

import tp.p2.model.Game;
import tp.p2.util.MyStringUtils;

public class BoardPrinter extends GamePrinter{
	
	private String[][] board;
	private final String space = " ";
	
	public BoardPrinter() {
		super(Game.DIM_Y, Game.DIM_X);
	}
	
	private void encodeGame(Game game) {
		board = new String[rows][cols];
		for(int i=0; i < rows; i++) {
			for(int j=0; j < cols; j++) {
				board[i][j] = game.getPositionToString(j, i);
			}
		}
	}

	@Override
	public String toString(Game game) {
		encodeGame(game);
		int cellSize = 7;
	    int marginSize = 2;
	    String vDelimiter = "|";
	    String hDelimiter = "-";
	    String rowDelimiter = MyStringUtils.repeat(hDelimiter, (cols * (cellSize + 1)) - 1);
	    String margin = MyStringUtils.repeat(space, marginSize);
	    String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

	    StringBuilder str = new StringBuilder();
	    
	    str.append(lineDelimiter);
	    
	    for(int i=0; i<rows; i++) {
	        str.append(margin).append(vDelimiter);
	        for (int j=0; j<cols; j++) {
	          str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
	        }
	        str.append(lineDelimiter);
	    }
	    return game.infoToString() + "\n" + str.toString();
	}
}
