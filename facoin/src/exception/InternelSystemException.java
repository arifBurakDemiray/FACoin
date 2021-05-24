package exception;

public class InternelSystemException extends Exception {

	private static final long serialVersionUID = -6724619689205923481L;

	public InternelSystemException() {
		super("InternalSystemException, there is an error in the system.");
	}

	public InternelSystemException(String message) {
		super(message);
	}

	public InternelSystemException(Throwable cause) {
		super(cause);
	}

	public InternelSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternelSystemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
