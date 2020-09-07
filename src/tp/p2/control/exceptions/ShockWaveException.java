package tp.p2.control.exceptions;

public class ShockWaveException extends CommandExecuteException {

	private static final long serialVersionUID = -219708980330019488L;
	
	public ShockWaveException() { super(); }
	public ShockWaveException(String message){ super(message); }
	public ShockWaveException(String message, Throwable cause) { super(message, cause); }

}
