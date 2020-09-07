package tp.p2.control.exceptions;

public class MoveException extends CommandExecuteException{

	private static final long serialVersionUID = 2282078357919184261L;

	public MoveException() { super(); }
	public MoveException(String message){ super(message); }
	public MoveException(String message, Throwable cause) { super(message, cause); }
	
}
