package tp.p2.control.exceptions;

public class FileContentsException extends CommandExecuteException{

	private static final long serialVersionUID = 6859098972940305795L;
	
	public FileContentsException() { super(); }
	public FileContentsException(String message){ super(message); }
	public FileContentsException(String message, Throwable cause) { super(message, cause); }

}
