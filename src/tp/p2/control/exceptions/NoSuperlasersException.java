package tp.p2.control.exceptions;

public class NoSuperlasersException extends CommandExecuteException {

	private static final long serialVersionUID = 8270761256768481823L;
	
	public NoSuperlasersException() { super(); }
	public NoSuperlasersException(String message){ super(message); }
	public NoSuperlasersException(String message, Throwable cause) { super(message, cause); }
	
}
