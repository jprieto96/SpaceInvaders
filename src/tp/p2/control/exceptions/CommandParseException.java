package tp.p2.control.exceptions;

public class CommandParseException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommandParseException() { super(); }
	public CommandParseException(String message){ super(message); }
	public CommandParseException(String message, Throwable cause) { super(message, cause); }
	
}
