package tp.p2.control.exceptions;

public class ShootException extends CommandExecuteException{

	private static final long serialVersionUID = -1166902862917646112L;
	
	public ShootException() { super(); }
	public ShootException(String message){ super(message); }
	public ShootException(String message, Throwable cause) { super(message, cause); }
	
}
