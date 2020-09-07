package tp.p2.control.exceptions;

public class ParametersException extends CommandParseException{

	private static final long serialVersionUID = -4687171844672097090L;
	
	public ParametersException() { super(); }
	public ParametersException(String message){ super(message); }
	public ParametersException(String message, Throwable cause) { super(message, cause); }
	
}
