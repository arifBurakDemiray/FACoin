package exception;

public class FileWriteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4186292371935508616L;

	public FileWriteException() {
		super("FileWriteException, file is not wrote");

	}

	public FileWriteException(String message) {
		super(message);
	}

	public FileWriteException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileWriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileWriteException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
