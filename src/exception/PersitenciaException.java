package exception;

public class PersitenciaException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersitenciaException(String message, Throwable e) {
		super(message,e);
	}
	
}
