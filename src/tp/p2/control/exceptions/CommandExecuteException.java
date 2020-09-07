package tp.p2.control.exceptions;

public class CommandExecuteException extends Exception{

	private static final long serialVersionUID = 2212830095934833119L;

	public CommandExecuteException() { super(); }
	public CommandExecuteException(String message){ super(message); }
	public CommandExecuteException(String message, Throwable cause) { super(message, cause); }

}
