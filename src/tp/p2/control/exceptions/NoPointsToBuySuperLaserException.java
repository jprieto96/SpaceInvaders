package tp.p2.control.exceptions;

public class NoPointsToBuySuperLaserException extends CommandExecuteException {

	private static final long serialVersionUID = 2974467163783348672L;
	
	public NoPointsToBuySuperLaserException() { super(); }
	public NoPointsToBuySuperLaserException(String message){ super(message); }
	public NoPointsToBuySuperLaserException(String message, Throwable cause) { super(message, cause); }

}
