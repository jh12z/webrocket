package webrocket;

/**
 * Web exception.
 */
public class WebHandlerException extends RuntimeException {

	public WebHandlerException() {
	}

	public WebHandlerException(String message) {
		super(message);
	}

	public WebHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebHandlerException(Throwable cause) {
		super(cause);
	}
}
