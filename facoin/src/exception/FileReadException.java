package exception;

public class FileReadException extends Exception {

	private static final long serialVersionUID = 2540916260158001137L;

	public FileReadException() {
		super("FileReadException, file could not be read.");

	}

	public FileReadException(String message) {
		super(message);
	}

	public FileReadException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileReadException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
