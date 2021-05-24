package exception;

public class HttpRequestException extends Exception {

	private static final long serialVersionUID = 1381698827813128190L;

	public HttpRequestException() {
		super("HttpRequestException, there is a problem with the http context while parsing.");
	}

	public HttpRequestException(String message) {
		super(message);
	}

	public HttpRequestException(Throwable cause) {
		super(cause);
	}

	public HttpRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
